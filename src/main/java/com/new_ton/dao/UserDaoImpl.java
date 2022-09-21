package com.new_ton.dao;

import com.new_ton.domain.entities.UserEntity;
import com.new_ton.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> findByUserName(String userName) {
        try {
            return userRepository.findByUserName(userName);
        } catch (Exception var3) {
            log.error("Error findByUserNam : {}, {}", ExceptionUtils.getMessage(var3), ExceptionUtils.getMessage(var3.getCause()));
            return Optional.empty();
        }
    }
}
