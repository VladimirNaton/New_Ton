package com.new_ton.repository;

import com.new_ton.domain.dto.accountmanager.EditeCatalogRecipeTableDto;
import com.new_ton.domain.dto.accountmanager.GetDataForSelectedRowEditeCatalogRecipeTableResponseDto;
import com.new_ton.domain.dto.technologistdto.GetDataForSelectedRowEditeRecipeTableResponseDto;
import com.new_ton.domain.entities.CatrecEntity;
import com.new_ton.domain.entities.RawEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatrecRepository extends JpaRepository<CatrecEntity, Integer> {
    List<CatrecEntity> findAllByIdCat(Integer id);

    void deleteAllByIdCat(Integer idCat);

    @Query("select max(r.n) from CatrecEntity r where r.idCat = ?1")
    Integer getMaxSequenceNumber(Integer idMain);

    @Query("select rc  from CatrecEntity rc where rc.idCat = ?1 and rc.n > ?2")
    List<CatrecEntity> selectAllByIdAndSequenceNumber(Integer idCat, Integer sequenceNumber);

    @Query("select new com.new_ton.domain.dto.accountmanager.GetDataForSelectedRowEditeCatalogRecipeTableResponseDto(r.id, r.n, r.stage, r.nameraw, r.percent, r.mass, r.devper, r.devmass, r.turnmix, r.timemix, r.pastpart, r.pastdate, r.filter) from CatrecEntity r where r.id =?1")
    GetDataForSelectedRowEditeCatalogRecipeTableResponseDto getDataForSelectedRowEditeCatalogRecipeTable(Integer id);

}
