package com.zelinskiyrk.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class StoreRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreRestApiApplication.class, args);
    }

}
