package com.new_ton.dao;

import com.new_ton.repository.PrintRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PrintDaoImpl implements PrintDao {

    private final PrintRepository printRepository;

    public List<Integer> getIdPrintTask() {
        try {
            return printRepository.selectTaskPrintId();
        } catch (Exception e) {
            log.error("Error getIdPrintTask : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
            return Collections.emptyList();
        }
    }

    @Transactional
    public void changeCode(int id) {
        try {
            printRepository.changeCode(id);
        } catch (Exception e) {
            log.error("Error changeCode : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }

    }
}

