package com.boot.cafemanager.data.jpa.repository;

import com.boot.cafemanager.data.jpa.entity.ProductEntity;
import com.boot.cafemanager.data.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    boolean existsByName(String name);
}
