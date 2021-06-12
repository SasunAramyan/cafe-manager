package com.boot.cafemanager.data.jpa.repository;

import com.boot.cafemanager.data.jpa.entity.TableEntity;
import com.boot.cafemanager.data.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long>, JpaSpecificationExecutor<TableEntity> {

    boolean existsByNumber(Long number);

    Optional<TableEntity> findByIdAndDeletedIsFalse(Long id);

    List<TableEntity> findAllByDeletedIsFalse();

    List<TableEntity> findAllByWaiter_IdAndDeletedIsFalse(Long waiterId);

}
