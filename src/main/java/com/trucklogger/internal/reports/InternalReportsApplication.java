package com.trucklogger.internal.reports;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableScheduling
@EntityScan(basePackages="com.trucklogger.transportation.assets")
public class InternalReportsApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(InternalReportsApplication.class);
    }
}
