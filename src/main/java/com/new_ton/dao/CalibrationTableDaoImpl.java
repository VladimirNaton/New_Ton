package com.new_ton.dao;

import com.new_ton.domain.dto.CalibrationTableEntityDto;
import com.new_ton.domain.dto.WeighingLogRequestDto;
import com.new_ton.domain.entities.CalibrationEntity;
import com.new_ton.repository.CalibrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Repository
public class CalibrationTableDaoImpl implements CalibrationTableDao {

    private final CalibrationRepository calibrationRepository;
    private final EntityManager entityManager;


    @Override
    public List<String> fioOperList() {
        try {
            return calibrationRepository.findDistinctByOperFioNoDate();
        } catch (Exception e) {
            log.error("Error getFioOperList : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
            return Collections.emptyList();
        }
    }

    @Override
    public CalibrationTableEntityDto getDataCalibrationTable(WeighingLogRequestDto weighingLogRequestDto) {
        try {
            CalibrationTableEntityDto calibrationTableEntityDto = new CalibrationTableEntityDto();
            String where = " where ";
            if (weighingLogRequestDto.getStartDate().equals("all") && weighingLogRequestDto.getEndDate().equals("all") && weighingLogRequestDto.getScales().equals("all") && weighingLogRequestDto.getFioOper().equals("all")) {
                where = "";
            }

            List<Integer> filterCount = new ArrayList();
            StringBuilder countQuery = new StringBuilder();
            StringBuilder selectQuery = new StringBuilder();
            countQuery.append("select count(distinct ce) from CalibrationEntity ce ").append(where);
            selectQuery.append("select ce from CalibrationEntity ce ").append(where);
            if (!weighingLogRequestDto.getStartDate().equals("all")) {
                countQuery.append(" ce.date >= '").append(weighingLogRequestDto.getStartDate()).append("'");
                selectQuery.append(" ce.date >= '").append(weighingLogRequestDto.getStartDate()).append("'");
                filterCount.add(1);
            }

            if (filterCount.size() > 0 && !weighingLogRequestDto.getEndDate().equals("all")) {
                countQuery.append(" and ce.date <= '").append(weighingLogRequestDto.getEndDate()).append("'");
                selectQuery.append(" and ce.date <= '").append(weighingLogRequestDto.getEndDate()).append("'");
                filterCount.add(1);
            }

            if (filterCount.size() > 0 && weighingLogRequestDto.getEndDate().equals("all")) {
                Date dateNow = new Date();
                Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String date = formatter.format(dateNow);
                countQuery.append(" and ce.date <= '").append(date).append("'");
                selectQuery.append(" and ce.date <= '").append(date).append("'");
                filterCount.add(1);
            }

            if (filterCount.size() == 0 && !weighingLogRequestDto.getEndDate().equals("all")) {
                countQuery.append(" ce.date <= '").append(weighingLogRequestDto.getEndDate()).append("'");
                selectQuery.append("  ce.date <= '").append(weighingLogRequestDto.getEndDate()).append("'");
                filterCount.add(1);
            }

            if (!weighingLogRequestDto.getScales().equals("all")) {
                if (filterCount.size() > 0) {
                    countQuery.append(" and ");
                    selectQuery.append(" and");
                }

                countQuery.append(" ce.nw = '").append(weighingLogRequestDto.getScales()).append("'");
                selectQuery.append(" ce.nw = '").append(weighingLogRequestDto.getScales()).append("'");
                filterCount.add(1);
            }

            if (!weighingLogRequestDto.getFioOper().equals("all")) {
                if (filterCount.size() > 0) {
                    countQuery.append(" and ");
                    selectQuery.append(" and");
                }

                countQuery.append(" ce.operfio = '").append(weighingLogRequestDto.getFioOper()).append("'");
                selectQuery.append(" ce.operfio = '").append(weighingLogRequestDto.getFioOper()).append("'");
                filterCount.add(1);
            }

            selectQuery.append(" order by ce.").append(weighingLogRequestDto.getOrderColumn()).append(" ").append(weighingLogRequestDto.getOrderType());
            Long pageCount = null;
            if (weighingLogRequestDto.getRequestFlag().equals("request")) {
                Query queryCount = entityManager.createQuery(countQuery.toString(), Long.class);
                List<Long> resultList = queryCount.getResultList();
                pageCount = resultList.get(0);
            }

            Query queryResult = entityManager.createQuery(selectQuery.toString(), CalibrationEntity.class);
            if (weighingLogRequestDto.getRequestFlag().equals("request") && weighingLogRequestDto.getLength() != -1) {
                queryResult.setFirstResult(weighingLogRequestDto.getStart());
                queryResult.setMaxResults(weighingLogRequestDto.getLength());
            }

            List<CalibrationEntity> calibrationEntityList = queryResult.getResultList();
            calibrationTableEntityDto.setCalibrationEntityList(calibrationEntityList);
            calibrationTableEntityDto.setTotalElements(pageCount);
            return calibrationTableEntityDto;
        } catch (Exception var11) {
            log.error("Error getDataCalibrationTable : {}, {}", ExceptionUtils.getMessage(var11), ExceptionUtils.getMessage(var11.getCause()));
            return null;
        }
    }
}
