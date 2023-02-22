package com.new_ton.repository;

import com.new_ton.domain.entities.CatpastEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatpastRepository extends JpaRepository<CatpastEntity, Integer> {

    <T> Page<T> findAllBy(Class<T> type, Pageable pageable);

    @Query("select cat from CatpastEntity cat where  LOWER(cat.namepast) LIKE LOWER(CONCAT('%', ?1,'%') )")
    Page<CatpastEntity> findAllByNamepastIgnoreCase(String namePast, Pageable pageable);
}
