package com.boot.cafemanager.service.role;

import com.boot.cafemanager.service.role.dto.RoleCreateDTO;
import com.boot.cafemanager.service.role.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    RoleDTO create(RoleCreateDTO createDTO);

    RoleDTO getById(Long id);

    List<RoleDTO> getAll();

    void delete(Long id);
}
