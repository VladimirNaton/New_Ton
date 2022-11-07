package com.new_ton.dao;

import com.new_ton.domain.entities.UserEntity;
import com.new_ton.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    public Optional<UserEntity> findByUserName(String userName) {
        try {
            return userRepository.findByUserName(userName);
        } catch (Exception e) {
            log.error("Error findByUserNam : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
            return Optional.empty();
        }
    }
}
