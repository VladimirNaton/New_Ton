package com.new_ton.repository;

import com.new_ton.domain.dto.accountmanager.CateqDto;
import com.new_ton.domain.entities.CateqEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CateqRepository extends JpaRepository<CateqEntity, Integer> {

    List<CateqDto> findAllBy();

}
