package com.new_ton.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UploadTableDaoImpl implements UploadTableDao{

    private final UnloadRepository unloadRepository;

    public List<UnloadEntity> getUnloadEntityById(int id) {
        try {
            return unloadRepository.findAllByIdpr(id);
        } catch (Exception e) {
            log.error("Error getUnloadEntityById : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
            return Collections.emptyList();
        }
    }
}
