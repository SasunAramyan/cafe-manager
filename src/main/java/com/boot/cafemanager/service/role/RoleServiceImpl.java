package com.boot.cafemanager.service.role;

import com.boot.cafemanager.data.jpa.entity.RoleEntity;
import com.boot.cafemanager.data.jpa.repository.RoleRepository;
import com.boot.cafemanager.data.jpa.repository.UserRepository;
import com.boot.cafemanager.exception.RoleAlreadyExistsException;
import com.boot.cafemanager.exception.RoleIsUsedException;
import com.boot.cafemanager.exception.RoleNotFoundException;
import com.boot.cafemanager.mapper.RoleMapper;
import com.boot.cafemanager.service.role.dto.RoleCreateDTO;
import com.boot.cafemanager.service.role.dto.RoleDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    @Transactional
    public RoleDTO create(RoleCreateDTO createDTO) {
        validateRoleUniqueFields(createDTO);
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(createDTO.getName());
        roleRepository.save(roleEntity);
        return roleMapper.toDTO(roleEntity);
    }


    @Override
    @Transactional(readOnly = true)
    public RoleDTO getById(Long id) {
        RoleEntity roleEntity = getRoleById(id);
        return roleMapper.toDTO(roleEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleDTO> getAll() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        return roleEntities.stream().map(roleMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        throwIfUsed(id);
        roleRepository.deleteById(id);
    }

    private void throwIfUsed(Long id) {
        if (userRepository.existsByRoles_IdAndDeletedIsFalse(id)) {
            throw new RoleIsUsedException(id);
        }
    }

    private RoleEntity getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id));
    }

    private void validateRoleUniqueFields(RoleCreateDTO createDTO) {
        validateUniqueName(createDTO.getName());
    }

    private void validateUniqueName(String name) {
        if (roleRepository.existsByName(name)) {
            throw new RoleAlreadyExistsException("name", name);
        }
    }
}
