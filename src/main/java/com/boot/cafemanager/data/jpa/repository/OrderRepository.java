package com.boot.cafemanager.data.jpa.repository;

import com.boot.cafemanager.data.jpa.entity.OrderEntity;
import com.boot.cafemanager.types.enums.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {

    Optional<OrderEntity> findByTable_IdAndStatus(Long tableId, OrderType status);
}
