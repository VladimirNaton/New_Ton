package com.new_ton.repository;

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
}

