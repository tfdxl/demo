package com.example;

import com.example.entity.Trace;
import com.example.entity.User;
import com.example.repository.TraceDao;
import com.example.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@ImportResource("classpath:appcontext-*.xml")
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private TraceDao traceDao;

    @Test
    public void contextLoads() {

        User user = userService.findUserByName("tianfeng");
        System.out.println(user);
    }

    @Test
    public void testInsert() {
        Trace trace = new Trace();
        trace.setVin(UUID.randomUUID().toString().substring(17));
        trace.setActionDesc("this is result");
        trace.setActionType(200);
        trace.setCarDealId(new Random().nextLong());
        trace.setDateHappen(new Date());
        trace.setUserId("tianfeng");
        trace.setUserName("tianfeng");
        trace.setUserPhone("12309087654");
        trace.setSaleOrderNo(String.valueOf(new Random().nextLong()));
        trace.setMsgId("fdsfdsf");
        trace.setResultStatus(230);
        trace.setResultStatusDesc("this is result");
        traceDao.save(trace);
    }
}
