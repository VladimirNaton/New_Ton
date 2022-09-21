package com.new_ton.repository;

import com.new_ton.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query("select ue from UserEntity ue where ue.userName = ?1")
    Optional<UserEntity> findByUserName(String userName);
}
