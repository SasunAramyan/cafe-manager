package com.boot.cafemanager.service.user.dto;

import com.boot.cafemanager.data.jpa.entity.RoleEntity;
import com.boot.cafemanager.service.role.dto.RoleDTO;
import com.boot.cafemanager.types.enums.Gender;
import java.util.List;

public class UserDTO {

  private Long id;

  private String username;

  private String phoneNumber;

  private String firstName;

  private String lastName;

  private Gender gender;

  private String email;

  private List<RoleDTO> roles;

}
