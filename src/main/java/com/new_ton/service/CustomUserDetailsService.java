package com.new_ton.service;

import com.lider.dao.UserDao;
import com.lider.domain.entities.UserEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDetails loadUserByUsername(String username) {
        Optional<UserEntity> userEntityOptional = this.userDao.findByUserName(username);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = (UserEntity)userEntityOptional.get();
            return new User(userEntity.getUserName(), "{noop}" + userEntity.getUserPassword(), AuthorityUtils.createAuthorityList(new String[]{userEntity.getUserRole()}));
        } else {
            throw new UsernameNotFoundException("Not found by " + username);
        }
    }
}
