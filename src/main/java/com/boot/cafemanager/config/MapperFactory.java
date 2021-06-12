package com.boot.cafemanager.config;


import com.boot.cafemanager.mapper.OrderMapper;
import com.boot.cafemanager.mapper.ProductMapper;
import com.boot.cafemanager.mapper.RoleMapper;
import com.boot.cafemanager.mapper.TableMapper;
import com.boot.cafemanager.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperFactory {

    @Bean
    public UserMapper userMapper() {
        return UserMapper.INSTANCE;
    }

    @Bean
    public RoleMapper roleMapper() {
        return RoleMapper.INSTANCE;
    }

    @Bean
    public TableMapper tableMapper() {
        return TableMapper.INSTANCE;
    }

    @Bean
    public ProductMapper productMapper() {
        return ProductMapper.INSTANCE;
    }

    @Bean
    public OrderMapper orderMapper() {
        return OrderMapper.INSTANCE;
    }
}
