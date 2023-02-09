package com.new_ton.repository;

import com.new_ton.domain.dto.EditeRecipeTableDto;
import com.new_ton.domain.dto.GetDataForSelectedRowEditeRecipeTableResponseDto;
import com.new_ton.domain.entities.RawEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RawRepository extends JpaRepository<RawEntity, Integer> {
    @Query("select re from RawEntity re where re.idMain = ?1 and re.code <> 3 and re.code <> 4 and re.code <> 5 and re.code <> 8")
    List<RawEntity> findAllByIdProd(int idProd);

    @Query("select re from RawEntity re where re.idMain = ?1 and re.code = 8 and re.stage = 1")
    Optional<RawEntity> findCode8Stage1(int idProd);

    @Query("select re from RawEntity re where re.idMain = ?1 and re.code = 8 and re.stage = 2")
    Optional<RawEntity> findCode8Stage2(int idProd);

    @Modifying
    @Query("delete from RawEntity r where r.idMain = ?1")
    void deleteAllByIdMain(Integer idMain);

    @Query("select new com.new_ton.domain.dto.EditeRecipeTableDto(r.id ,r.n, r.stage, r.code, r.nameraw, r.percent, r.mass, r.devper, r.devmass) from RawEntity r where r.idMain = ?1 order by r.n asc ")
    List<EditeRecipeTableDto> findAllIdMain(Integer idMain);


    @Query("select new com.new_ton.domain.dto.GetDataForSelectedRowEditeRecipeTableResponseDto(r.id, r.n, r.stage, r.nameraw, r.percent, r.mass, r.devper, r.devmass, r.turnmix, r.timemix, r.pastpart, r.pastdate, r.filter, r.factmass, r.factmassdev) from RawEntity r where r.id =?1")
    GetDataForSelectedRowEditeRecipeTableResponseDto getDataForSelectedRowEditeRecipeTable(Integer id);

    @Query("select re  from RawEntity re where re.idMain = ?1 and re.n > ?2")
    List<RawEntity> selectAllByIdAndSequenceNumber(Integer idMain, Integer sequenceNumber);


    @Query("select max(r.n) from RawEntity r where r.idMain = ?1")
    Integer getMaxSequenceNumber(Integer idMain);

    List<RawEntity> findAllByIdMain(Integer idMain);
}

