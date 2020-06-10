package com.example.demo.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class ConverterConfig {
    @Bean("JsonMessageConverter")
    public ObjectFactory converterConfig(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter(mapper);
        ObjectFactory<HttpMessageConverters> converter = () -> new HttpMessageConverters(jsonConverter);
        return converter;
    }
}
