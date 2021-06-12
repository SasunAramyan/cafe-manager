package com.boot.cafemanager.service.role;

import com.boot.cafemanager.service.role.dto.RoleCreateDTO;
import com.boot.cafemanager.service.role.dto.RoleDTO;
import com.boot.cafemanager.service.user.dto.UserCreateDTO;
import com.boot.cafemanager.service.user.dto.UserDTO;

import java.util.List;

public interface RoleService {

  RoleDTO create(RoleCreateDTO createDTO);

  RoleDTO getById(Long id);

  List<RoleDTO> getAll();

  void delete(Long id);
}
