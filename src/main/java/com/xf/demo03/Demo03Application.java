package com.xf.demo03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author xfgg
 */
@ServletComponentScan
@EnableAsync
@SpringBootApplication
public class Demo03Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo03Application.class, args);
    }

}
