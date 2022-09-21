package com.new_ton.dao;

import com.new_ton.domain.entities.UserEntity;

import java.util.Optional;

public interface UserDao {
    Optional<UserEntity> findByUserName(String userName);
}
