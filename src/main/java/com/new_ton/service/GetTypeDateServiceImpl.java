package com.new_ton.service;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class GetTypeDateServiceImpl implements GetTypeDateService {
    private static final Logger log = LoggerFactory.getLogger(GetTypeDateServiceImpl.class);

    public GetTypeDateServiceImpl() {
    }

    public String getTypeDate(String typeDate) {
        try {
            String getTypeDate = "";
            if (typeDate.equals("dateCreate")) {
                getTypeDate = "datecr";
            }

            if (typeDate.equals("datePlan")) {
                getTypeDate = "datepl";
            }

            if (typeDate.equals("dateProduction")) {
                getTypeDate = "datemade";
            }

            return getTypeDate;
        } catch (Exception var3) {
            log.error("Error getTypeDate : {}, {}", ExceptionUtils.getMessage(var3), ExceptionUtils.getMessage(var3.getCause()));
            return null;
        }
    }
}
