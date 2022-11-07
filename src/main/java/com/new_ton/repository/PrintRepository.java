package com.new_ton.repository;


import com.lider.domain.entities.PrintEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrintRepository extends JpaRepository<PrintEntity, Integer> {
    @Query("select pe.idpr from PrintEntity pe where pe.code = 1 and pe.idpr is not null")
    List<Integer> selectTaskPrintId();

    @Modifying
    @Query("update PrintEntity pe set pe.code = 0 where pe.idpr = ?1")
    void changeCode(int id);
}
