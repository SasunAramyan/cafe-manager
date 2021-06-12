package com.boot.cafemanager.mapper;

import com.boot.cafemanager.data.jpa.entity.UserEntity;
import com.boot.cafemanager.service.user.dto.UserCreateDTO;
import com.boot.cafemanager.service.user.dto.UserDTO;
import com.boot.cafemanager.web.rest.model.user.UserCreateModel;
import com.boot.cafemanager.web.rest.model.user.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(UserEntity userEntity);

    UserCreateDTO toDTO(UserCreateModel userCreateModel);

    UserModel toModel(UserDTO userDTO);

}
