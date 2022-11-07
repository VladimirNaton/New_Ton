package com.new_ton.dao;

import com.new_ton.domain.entities.RawEntity;
import com.new_ton.repository.RawRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RawTableDaoImpl implements RawTableDao {

    private final RawRepository rawRepository;

    public List<RawEntity> findAllByIdProd(int idProd) {
        try {
            return rawRepository.findAllByIdProd(idProd);
        } catch (Exception e) {
            log.error("Error findAllByIdProd : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
            return Collections.emptyList();
        }
    }

    public Optional<RawEntity> findCode8Stage1(int idProd) {
        return rawRepository.findCode8Stage1(idProd);
    }

    public Optional<RawEntity> findCode8Stage2(int idProd) {
        return rawRepository.findCode8Stage2(idProd);
    }
}

