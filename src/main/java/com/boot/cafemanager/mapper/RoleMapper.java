package com.boot.cafemanager.mapper;

import com.boot.cafemanager.data.jpa.entity.RoleEntity;
import com.boot.cafemanager.service.role.dto.RoleCreateDTO;
import com.boot.cafemanager.service.role.dto.RoleDTO;
import com.boot.cafemanager.web.rest.model.role.RoleCreateModel;
import com.boot.cafemanager.web.rest.model.role.RoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO toDTO(RoleEntity roleEntity);

    RoleCreateDTO toDTO(RoleCreateModel roleCreateModel);

    RoleModel toModel(RoleDTO roleDTO);

}
