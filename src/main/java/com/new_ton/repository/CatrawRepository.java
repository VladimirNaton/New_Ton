package com.new_ton.repository;

import com.new_ton.domain.dto.accountmanager.ComponentTableDto;
import com.new_ton.domain.dto.technologistdto.EditeRecipeComponentTableDto;
import com.new_ton.domain.entities.CatrawEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CatrawRepository extends JpaRepository<CatrawEntity, Integer> {
    @Query("select new com.new_ton.domain.dto.technologistdto.EditeRecipeComponentTableDto(cat.id , cat.date, cat.nameraw) from CatrawEntity cat where cat.code =?1")
    Page<EditeRecipeComponentTableDto> getDataForEditeRecipeComponentTable(Integer code, Pageable pageable);

    @Query("select new com.new_ton.domain.dto.technologistdto.EditeRecipeComponentTableDto(cat.id , cat.date, cat.nameraw) from CatrawEntity cat where cat.code =?1 and LOWER(cat.nameraw) LIKE LOWER(CONCAT('%', ?2,'%') )")
    Page<EditeRecipeComponentTableDto> getDataForEditeRecipeComponentTableWithSearch(Integer code, String searchComponent, Pageable pageable);

    @Query("select new com.new_ton.domain.dto.accountmanager.ComponentTableDto(cat.id, cat.code1C, cat.nameraw, cat.code, cat.part, cat.date) from CatrawEntity cat where cat.code =?1")
    Page<ComponentTableDto> getDataForEditeComponentTable(Integer code, Pageable pageable);

    @Query("select new com.new_ton.domain.dto.accountmanager.ComponentTableDto(cat.id, cat.code1C, cat.nameraw, cat.code, cat.part, cat.date) from CatrawEntity cat where cat.code =?1 and LOWER(cat.nameraw) LIKE LOWER(CONCAT('%', ?2,'%') )")
    Page<ComponentTableDto> getDataForEditeComponentTableWithSearch(Integer code, String searchComponent, Pageable pageable);

    @Query("select new com.new_ton.domain.dto.accountmanager.ComponentTableDto(cat.id, cat.code1C, cat.nameraw, cat.code, cat.part, cat.date) from CatrawEntity cat where cat.id = ?1")
    ComponentTableDto findComponentById(Integer id);

}
