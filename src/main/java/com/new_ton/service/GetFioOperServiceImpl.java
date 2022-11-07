package com.new_ton.service;


import com.new_ton.dao.CalibrationTableDao;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GetFioOperServiceImpl implements GetFioOperService {
    private static final Logger log = LoggerFactory.getLogger(GetFioOperServiceImpl.class);
    private final CalibrationTableDao calibrationTableDao;

    public GetFioOperServiceImpl(CalibrationTableDao calibrationTableDao) {
        this.calibrationTableDao = calibrationTableDao;
    }

    public List<String> getFioOper() {
        try {
            return this.calibrationTableDao.fioOperList();
        } catch (Exception var2) {
            log.error("Error getFioOper : {}, {}", ExceptionUtils.getMessage(var2), ExceptionUtils.getMessage(var2.getCause()));
            return Collections.emptyList();
        }
    }
}

