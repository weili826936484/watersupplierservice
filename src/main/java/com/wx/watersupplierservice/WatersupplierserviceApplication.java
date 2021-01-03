package com.wx.watersupplierservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.wx")
@EnableConfigurationProperties
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
public class WatersupplierserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatersupplierserviceApplication.class, args);
    }

}
