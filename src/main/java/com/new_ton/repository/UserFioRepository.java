package com.new_ton.repository;

import com.new_ton.domain.entities.UserFioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserFioRepository extends JpaRepository<UserFioEntity, Long> {

    @Query("select us.userFio from UserFioEntity us where us.userLogin = ?1")
    String getUserFio(String userLogin);
}
