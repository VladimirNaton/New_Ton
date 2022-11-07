package com.new_ton.dao;

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
            return this.printRepository.selectTaskPrintId();
        } catch (Exception var2) {
            log.error("Error getIdPrintTask : {}, {}", ExceptionUtils.getMessage(var2), ExceptionUtils.getMessage(var2.getCause()));
            return Collections.emptyList();
        }
    }

    @Transactional
    public void changeCode(int id) {
        try {
            this.printRepository.changeCode(id);
        } catch (Exception var3) {
            log.error("Error changeCode : {}, {}", ExceptionUtils.getMessage(var3), ExceptionUtils.getMessage(var3.getCause()));
        }

    }
}

