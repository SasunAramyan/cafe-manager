package com.boot.cafemanager.mapper;

import com.boot.cafemanager.data.jpa.entity.UserEntity;
import com.boot.cafemanager.service.user.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


  UserDTO toDTO(UserEntity userEntity);

}
