package com.boot.cafemanager.web.rest.controller;

import com.boot.cafemanager.mapper.RoleMapper;
import com.boot.cafemanager.service.role.RoleService;
import com.boot.cafemanager.service.role.dto.RoleCreateDTO;
import com.boot.cafemanager.service.role.dto.RoleDTO;
import com.boot.cafemanager.web.rest.model.role.RoleCreateModel;
import com.boot.cafemanager.web.rest.model.role.RoleModel;
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
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;
    private final RoleMapper roleMapper;

    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @PostMapping
    public ResponseEntity<RoleModel> create(@RequestBody RoleCreateModel roleCreateModel) {
        RoleCreateDTO roleCreateDTO = roleMapper.toDTO(roleCreateModel);
        RoleDTO roleDTO = roleService.create(roleCreateDTO);
        RoleModel roleModel = roleMapper.toModel(roleDTO);
        return ResponseEntity.ok(roleModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleModel> getById(@PathVariable Long id) {
        RoleDTO roleDTO = roleService.getById(id);
        RoleModel roleModel = roleMapper.toModel(roleDTO);
        return ResponseEntity.ok(roleModel);
    }

    @GetMapping
    public ResponseEntity<List<RoleModel>> getAll() {
        List<RoleDTO> roleDTOs = roleService.getAll();
        List<RoleModel> roleModels = roleDTOs.stream().map(roleMapper::toModel).collect(Collectors.toList());
        return ResponseEntity.ok(roleModels);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        roleService.delete(id);
    }
}
