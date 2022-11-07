package com.new_ton.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Repository
public class LabProtDaoImpl implements LabProtDao{
    private final LabprotRepository labprotRepository;

    public List<LabprotEntity> findAllByIdpr(int id) {
        try {
            return labprotRepository.findAllByIdpr(id);
        } catch (Exception var3) {
            log.error("Error findAllByIdpr : {}, {}", ExceptionUtils.getMessage(var3), ExceptionUtils.getMessage(var3.getCause()));
            return Collections.emptyList();
        }
    }
}
