package com.new_ton.service;


import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Log4j2
@Service
public class GetTypeDateServiceImpl implements GetTypeDateService {

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
        } catch (Exception e) {
            log.error("Error getTypeDate : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
            return null;
        }
    }
}
