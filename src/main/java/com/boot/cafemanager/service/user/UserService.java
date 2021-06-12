package com.boot.cafemanager.service.user;

import com.boot.cafemanager.service.user.dto.UserCreateDTO;
import com.boot.cafemanager.service.user.dto.UserDTO;
import java.util.List;

public interface UserService {

  UserDTO create(UserCreateDTO createDTO);

  UserDTO getById(Long id);

  List<UserDTO> getAll();

  void delete(Long id);
}
