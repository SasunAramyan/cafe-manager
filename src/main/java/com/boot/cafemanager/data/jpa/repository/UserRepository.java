package com.boot.cafemanager.data.jpa.repository;

import com.boot.cafemanager.data.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {


    Optional<UserEntity> findByIdAndDeletedIsFalse(Long id);

    Optional<UserEntity> findByIdAndRoles_NameAndDeletedIsFalse(Long id,String roleName);

    boolean existsByPhoneNumberAndDeletedIsFalse(String phoneNumber);

    boolean existsByRoles_IdAndDeletedIsFalse(Long id);

    boolean existsByUsernameAndDeletedIsFalse(String username);

    List<UserEntity> findAllByDeletedIsFalse();
}
