package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 应用启动类
 *
 * @author tianfeng
 */
@ImportResource("classpath:application-*.xml")
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.repository")
@EntityScan(basePackages = "com.example.entity")
@EnableTransactionManagement
@EnableSwagger2
@EnableCaching
public class DemoApplication {

    private static Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        logger.info("spring application has started ");
    }
}
