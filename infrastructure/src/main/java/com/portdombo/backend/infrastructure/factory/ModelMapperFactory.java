package com.portdombo.backend.infrastructure.factory;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperFactory {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
