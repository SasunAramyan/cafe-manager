package com.boot.cafemanager.config;


import com.boot.cafemanager.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperFactory {

  @Bean
  public UserMapper employeeMapper() {
    return UserMapper.INSTANCE;
  }

}
