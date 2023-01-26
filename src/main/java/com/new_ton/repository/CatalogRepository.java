package com.new_ton.repository;

import com.new_ton.domain.dto.CatalogDto;
import com.new_ton.domain.dto.MainTableDto;
import com.new_ton.domain.entities.CatalogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatalogRepository extends JpaRepository<CatalogEntity, Integer> {
    <T> Page<T> findAllBy(Class<T> type, Pageable pageable);
    @Query("SELECT new com.new_ton.domain.dto.CatalogDto(cat.idpr, cat.brend, cat.nameprod)  FROM CatalogEntity cat  WHERE LOWER(cat.nameprod) LIKE LOWER(CONCAT('%', ?1,'%'))")
    Page<CatalogDto> findByNameProdWithPagination(String nameProd, Pageable pageable);
}
