package com.vnborx.config;

import com.vnborx.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.vnborx")
public class MyConfig {

    @Bean
    public User user() {
        return new User();
    }
}
