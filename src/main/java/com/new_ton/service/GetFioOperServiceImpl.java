package com.new_ton.service;


import com.new_ton.dao.CalibrationTableDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class GetFioOperServiceImpl implements GetFioOperService {
    private final CalibrationTableDao calibrationTableDao;

    public List<String> getFioOper() {
        try {
            return calibrationTableDao.fioOperList();
        } catch (Exception e) {
            log.error("Error getFioOper : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
            return Collections.emptyList();
        }
    }
}

