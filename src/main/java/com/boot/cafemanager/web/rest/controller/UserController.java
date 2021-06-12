package com.boot.cafemanager.web.rest.controller;


import com.boot.cafemanager.mapper.UserMapper;
import com.boot.cafemanager.service.user.UserService;
import com.boot.cafemanager.service.user.dto.UserCreateDTO;
import com.boot.cafemanager.service.user.dto.UserDTO;
import com.boot.cafemanager.web.rest.model.user.UserCreateModel;
import com.boot.cafemanager.web.rest.model.user.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserModel> create(@RequestBody UserCreateModel userCreateModel) {
        UserCreateDTO userCreateDTO = userMapper.toDTO(userCreateModel);
        UserDTO userDTO = userService.create(userCreateDTO);
        UserModel userModel = userMapper.toModel(userDTO);
        return ResponseEntity.ok(userModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getById(@PathVariable Long id) {
        UserDTO userDTO = userService.getById(id);
        UserModel userModel = userMapper.toModel(userDTO);
        return ResponseEntity.ok(userModel);
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAll() {
        List<UserDTO> userDTOs = userService.getAll();
        List<UserModel> userModels = userDTOs.stream().map(userMapper::toModel).collect(Collectors.toList());
        return ResponseEntity.ok(userModels);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }
}
