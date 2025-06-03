package ru.vbalakin.jewelrymanagerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class JewelryManagerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JewelryManagerApiApplication.class, args);
    }
}
