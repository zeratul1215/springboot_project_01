package com.tianhong.reggi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//启动类
@Slf4j
@SpringBootApplication
@ServletComponentScan
public class ReggiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReggiApplication.class, args);
        log.info("project initalized successfully");
    }

}
