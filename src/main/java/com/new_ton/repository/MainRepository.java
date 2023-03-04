package com.new_ton.repository;


import com.new_ton.domain.dto.technologistdto.GetDataForInformationStringEditeRecipeDto;
import com.new_ton.domain.dto.technologistdto.GetDataForProductInProductionTableDto;
import com.new_ton.domain.entities.MainEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MainRepository extends JpaRepository<MainEntity, Integer> {
    Optional<MainEntity> findByIdpr(int id);

    <T> Page<T> findAllByState(Integer state, Class<T> type, Pageable pageable);

    @Query("select new com.new_ton.domain.dto.technologistdto.GetDataForInformationStringEditeRecipeDto(me.brend, me.datecr, me.nameprod, me.tempprodmin, me.tempprodmax, me.mass, me.percent,me.comment) from MainEntity me where me.idpr = ?1")
    GetDataForInformationStringEditeRecipeDto findDataForInformationStringEditeTable(Integer idProd);

    @Query("select  new com.new_ton.domain.dto.technologistdto.GetDataForProductInProductionTableDto(m.idpr, m.datecr, m.datepl, m.brend, m.nameprod, m.percent, m.mass, m.tempprodmin, m.tempprodmax, m.comment, m.state) from MainEntity m where m.state >= 5 and m.state <= 10")
    List<GetDataForProductInProductionTableDto> getDataForProductInProductionTable();

    <T> Page<T> findAllByStateAndNameprodContainingIgnoreCase(Integer state, String nameProd, Class<T> type, Pageable pageable);

    @Query("select  new com.new_ton.domain.dto.technologistdto.GetDataForProductInProductionTableDto(m.idpr, m.datecr, m.datepl, m.brend, m.nameprod, m.percent, m.mass, m.tempprodmin, m.tempprodmax, m.comment, m.state) from MainEntity m where m.state >= 5 and m.state <= 10")
    List<GetDataForProductInProductionTableDto> getDataForTesterTable();

    @Modifying
    @Query("update MainEntity me set me.state = 5 where me.idpr = ?1")
    void returnToWork(Integer id);

    @Modifying
    @Query("update MainEntity me set me.state = 8 where me.idpr = ?1")
    void sendToReject(Integer id);

    @Modifying
    @Query("update MainEntity me set me.state = 7 where me.idpr = ?1")
    void sendPutAside(Integer id);

    @Modifying
    @Query("update MainEntity me set me.comment = ?1 where me.idpr = ?2")
    void sendComment(String comment, Integer id);

    @Query("select  new com.new_ton.domain.dto.technologistdto.GetDataForProductInProductionTableDto(m.idpr, m.datecr, m.datepl, m.brend, m.nameprod, m.percent, m.mass, m.tempprodmin, m.tempprodmax, m.comment, m.state) from MainEntity m where m.state = 3")
    List<GetDataForProductInProductionTableDto> getDataForProductForProductionTable();

    @Query("select  new com.new_ton.domain.dto.technologistdto.GetDataForProductInProductionTableDto(m.idpr, m.datecr, m.datepl, m.brend, m.nameprod, m.percent, m.mass, m.tempprodmin, m.tempprodmax, m.comment, m.state) from MainEntity m where m.state = 4")
    List<GetDataForProductInProductionTableDto> getDataForTaskShiftTable();

    @Query("select  new com.new_ton.domain.dto.technologistdto.GetDataForProductInProductionTableDto(m.idpr, m.datecr, m.datepl, m.brend, m.nameprod, m.percent, m.mass, m.tempprodmin, m.tempprodmax, m.comment, m.state) from MainEntity m where m.state >= ?1 and m.state <= ?2")
    List<GetDataForProductInProductionTableDto> getDataForProductInProductionSupervisorTable(Integer state1, Integer state2);
}

