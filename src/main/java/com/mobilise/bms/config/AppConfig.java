package com.mobilise.bms.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    //Model Mapper Config
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
