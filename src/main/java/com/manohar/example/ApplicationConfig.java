package com.manohar.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class ApplicationConfig {

    @Bean
    public MyFirstClass myFirstBean() {
        return new MyFirstClass("First Bean");
    }

    @Bean("bean2")
    @Qualifier("mySecondBean")
    public MyFirstClass mySecondBean() {
        return new MyFirstClass("Second Bean");
    }

    @Bean
    @Primary
    @Profile("dev")
    public MyFirstClass myThirdBean() {
        return new MyFirstClass("Third Bean");
    }
}
