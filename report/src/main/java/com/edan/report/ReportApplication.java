package com.edan.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.edan.report"}, exclude = {MongoAutoConfiguration.class})
//@WebServlet、@WebFilter、@WebListener注解自动注册，无需其他代码。
@ServletComponentScan
public class ReportApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(ReportApplication.class, args);
    }
}
