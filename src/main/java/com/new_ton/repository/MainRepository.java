package com.new_ton.repository;


import com.new_ton.domain.dto.technologistdto.GetDataForInformationStringEditeRecipeDto;
import com.new_ton.domain.dto.technologistdto.GetDataForProductInProductionTableDto;
import com.new_ton.domain.entities.MainEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MainRepository extends JpaRepository<MainEntity, Integer> {
    Optional<MainEntity> findByIdpr(int id);

    <T> Page<T> findAllByState(Integer state, Class<T> type, Pageable pageable);

    @Query("select new com.new_ton.domain.dto.technologistdto.GetDataForInformationStringEditeRecipeDto(me.brend, me.datecr, me.nameprod, me.tempprodmin, me.tempprodmax, me.mass, me.percent,me.comment) from MainEntity me where me.idpr = ?1")
    GetDataForInformationStringEditeRecipeDto findDataForInformationStringEditeTable(Integer idProd);

    @Query("select  new com.new_ton.domain.dto.technologistdto.GetDataForProductInProductionTableDto(m.idpr, m.datecr, m.datepl, m.brend, m.nameprod, m.percent, m.mass, m.tempprodmin, m.tempprodmax, m.comment) from MainEntity m where m.state >= 5 and m.state <= 10")
    List<GetDataForProductInProductionTableDto> getDataForProductInProductionTable();

    <T> Page<T> findAllByStateAndNameprodContainingIgnoreCase(Integer state, String nameProd,  Class<T>type, Pageable pageable);
}

