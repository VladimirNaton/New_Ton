package com.new_ton.repository;

import com.new_ton.domain.dto.CatalogDto;
import com.new_ton.domain.entities.CatalogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatalogRepository extends JpaRepository<CatalogEntity, Integer> {
    <T> Page<T> findAllBy(Class<T> type, Pageable pageable);
}
