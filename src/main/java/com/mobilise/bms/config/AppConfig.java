package com.mobilise.bms.config;

import com.mobilise.bms.dto.BookDTO;
import com.mobilise.bms.model.Author;
import com.mobilise.bms.model.Book;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.stream.Collectors;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    //Model Mapper Config
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
