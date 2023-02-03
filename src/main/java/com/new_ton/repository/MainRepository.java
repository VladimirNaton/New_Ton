package com.new_ton.repository;


import com.new_ton.domain.dto.GetDataForInformationStringEditeRecipeDto;
import com.new_ton.domain.entities.MainEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MainRepository extends JpaRepository<MainEntity, Integer> {
    Optional<MainEntity> findByIdpr(int id);

    <T> Page<T> findAllByState(Integer state, Class<T> type, Pageable pageable);

    @Query("select new com.new_ton.domain.dto.GetDataForInformationStringEditeRecipeDto(me.brend, me.datecr, me.nameprod, me.tempprodmin, me.tempprodmax, me.mass, me.percent) from MainEntity me where me.idpr = ?1")
    GetDataForInformationStringEditeRecipeDto findDataForInformationStringEditeTable(Integer idProd);
}

