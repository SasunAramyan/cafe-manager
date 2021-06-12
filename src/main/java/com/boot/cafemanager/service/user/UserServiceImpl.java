package com.boot.cafemanager.service.user;

import com.boot.cafemanager.data.jpa.entity.RoleEntity;
import com.boot.cafemanager.data.jpa.entity.UserEntity;
import com.boot.cafemanager.data.jpa.repository.RoleRepository;
import com.boot.cafemanager.data.jpa.repository.UserRepository;
import com.boot.cafemanager.exception.RoleNotFoundException;
import com.boot.cafemanager.exception.UserAlreadyExistsException;
import com.boot.cafemanager.exception.UserNotFoundException;
import com.boot.cafemanager.mapper.UserMapper;
import com.boot.cafemanager.service.user.dto.UserCreateDTO;
import com.boot.cafemanager.service.user.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    public static final String DELETED = "DELETED";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserDTO create(UserCreateDTO createDTO) {
        validateUserUniqueFields(createDTO);
        UserEntity userEntity = new UserEntity();
        setFields(userEntity, createDTO);
        userRepository.save(userEntity);
        return userMapper.toDTO(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getById(Long id) {
        UserEntity userEntity = getUser(id);
        return userMapper.toDTO(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAll() {
        List<UserEntity> users = userRepository.findAllByDeletedIsFalse();
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        UserEntity user = getUser(id);
        LocalDateTime now = LocalDateTime.now();
        //because username and phone numbers are unique
        user.setUsername(String.format("%s_%s_%s", user.getUsername(), DELETED, now));
        user.setPhoneNumber(String.format("%s_%s_%s", user.getPhoneNumber(), DELETED, now));
        user.setDeleted(true);
        userRepository.save(user);
    }

    private void setFields(UserEntity userEntity, UserCreateDTO createDTO) {
        userEntity.setEmail(createDTO.getEmail());
        userEntity.setFirstName(createDTO.getFirstName());
        userEntity.setGender(createDTO.getGender());
        userEntity.setUsername(createDTO.getUsername());
        userEntity.setFirstName(createDTO.getFirstName());
        userEntity.setLastName(createDTO.getLastName());
        userEntity.setPhoneNumber(createDTO.getPhoneNumber());
        setRoles(userEntity, createDTO.getRoleIds());
    }

    private void setRoles(UserEntity userEntity, List<Long> roleIds) {
        List<RoleEntity> roles = roleIds.stream().map(r -> roleRepository.findById(r)
                .orElseThrow(() -> new RoleNotFoundException(r))).collect(Collectors.toList());
        userEntity.setRoles(roles);
    }

    private void validateUserUniqueFields(UserCreateDTO createDTO) {
        validateUniquePhoneNumber(createDTO.getPhoneNumber());
        validateUniqueUsername(createDTO.getUsername());
    }

    private void validateUniqueUsername(String username) {
        if (userRepository.existsByUsernameAndDeletedIsFalse(username)) {
            throw new UserAlreadyExistsException("username", username);
        }
    }

    private void validateUniquePhoneNumber(String phoneNumber) {
        if (userRepository.existsByPhoneNumberAndDeletedIsFalse(phoneNumber)) {
            throw new UserAlreadyExistsException("phone number", phoneNumber);
        }
    }

    private UserEntity getUser(Long id) {
        return userRepository.findByIdAndDeletedIsFalse(id).orElseThrow(() -> new UserNotFoundException(id));
    }

}
