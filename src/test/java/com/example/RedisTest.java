package com.example;

import com.alibaba.fastjson.JSON;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tianfeng on 2017/7/1.
 */
public class RedisTest {

    private static String URL = "localhost";

    private Jedis jedis;

    @Before
    public void init() {
        jedis = new Jedis(URL, 6379);
        System.out.println("The init method has been invoked ... ");
    }

    @Test
    public void testCommonUsage01() {
        //record the start time
        long start = System.currentTimeMillis();
        //set 100k data in a loop
        for (int i = 0; i < 1; i++) {
            String result = jedis.set("n" + i, "n" + i);
            System.out.println("The result from redis server is " + result);
        }
        //record the end time
        long end = System.currentTimeMillis();
        System.out.println("Simple SET: " + ((end - start) / 1000.0) + " seconds");
        jedis.disconnect();
    }

    /**
     * the transaction in redis is very simple
     * 一个client发起的事务中的命令可以连续的执行，而中间不会插入其他client的命令。
     * <p>
     * 我们调用jedis.watch(…)方法来监控key，如果调用后key值发生变化，则整个事务会执
     * 行失败。另外，事务中某个操作失败，并不会回滚其他操作。这一点需要注意。还有，我们
     * 可以使用discard()方法来取消事务。
     */
    @Test
    public void testTxInRedis() throws IOException {
        long start = System.currentTimeMillis();
        Transaction tx = jedis.multi();
        for (int i = 0; i < 100; i++) {
            tx.set("tx" + i, "tx" + i);
        }
        List<Object> results = tx.exec();
        long end = System.currentTimeMillis();
        System.err.println("Transaction set: " + ((end - start) / 1000.0) + "seconds .");
        System.err.println("The results from redis server is " + JSON.toJSONString(results));
        tx.close();
        jedis.disconnect();
    }

    /**
     * 有时，我们需要采用异步方式，一次发送多个指令，不同步等待其返回结果。
     * 这样可以取得非常好的执行效率
     */
    @Test
    public void testPipelineOfRedis() {
        //创建一个管道，执行多个命令
        Pipeline pipeline = jedis.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            pipeline.set("p" + i, "p" + i);
        }
        List<Object> results = pipeline.syncAndReturnAll();
        System.err.println("The results from redis server is " + JSON.toJSONString(results));
        long end = System.currentTimeMillis();
        System.out.println("Pipelined SET: " + ((end - start) / 1000.0) + " seconds");
        jedis.disconnect();
    }

    /**
     * 管道当中使用事务
     */
    @Test
    public void testTxInPipeline() {
        long start = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        pipeline.multi();
        for (int i = 0; i < 100000; i++) {
            pipeline.set("" + i, "" + i);
        }
        pipeline.exec();
        List<Object> results = pipeline.syncAndReturnAll();
        System.err.println("The results from redis server is " + JSON.toJSONString(results));
        long end = System.currentTimeMillis();
        System.out.println("Pipelined transaction: " + ((end - start) / 1000.0) + " seconds");
        jedis.disconnect();
    }

    /**
     * 分布式直连同步调用
     */
    @Test
    public void testInShardSyncMode() {
        List<JedisShardInfo> shards = Arrays.asList(
                new JedisShardInfo("localhost", 6379),
                new JedisShardInfo("localhost", 6380));
        ShardedJedis sharding = new ShardedJedis(shards);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String result = sharding.set("sn" + i, "n" + i);
            System.out.println("The result from redis is " + result);
        }
        long end = System.currentTimeMillis();
        System.out.println("Simple@Sharing SET: " + ((end - start) / 1000.0) + " seconds");
        sharding.disconnect();
    }

    /**
     * 分布式直连异步调用
     */
    @Test
    public void testInShardAsyncMode() {
        List<JedisShardInfo> shards = Arrays.asList(
                new JedisShardInfo("localhost", 6379),
                new JedisShardInfo("localhost", 6380));
        ShardedJedis sharding = new ShardedJedis(shards);
        ShardedJedisPipeline pipeline = sharding.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            pipeline.set("sp" + i, "p" + i);
        }
        List<Object> results = pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        System.out.println("Pipelined@Sharing SET: " + ((end - start) / 1000.0) + " seconds");
        sharding.disconnect();
    }


    @After
    public void destroy() {
        if (this.jedis != null)
            jedis.close();
        jedis = null;
        System.out.println("The destroy method has been invoked ...");
    }
}
