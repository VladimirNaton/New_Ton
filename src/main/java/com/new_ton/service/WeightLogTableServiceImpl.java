package com.new_ton.service;

import com.new_ton.dao.CalibrationTableDao;
import com.new_ton.domain.dto.CalibrationTableDto;
import com.new_ton.domain.dto.CalibrationTableEntityDto;
import com.new_ton.domain.dto.WeighingLogRequestDto;
import com.new_ton.domain.dto.WeightLogTableResponseDto;
import com.new_ton.domain.entities.CalibrationEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WeightLogTableServiceImpl implements WeightLogTableService {
    private static final Logger log = LoggerFactory.getLogger(WeightLogTableServiceImpl.class);
    private final CalibrationTableDao calibrationTableDao;
    private final ColumnNameService columnNameService;


    public WeightLogTableResponseDto getWeightLogTableData(WeighingLogRequestDto weighingLogRequestDto) {
        try {
            String columnName = weighingLogRequestDto.getOrderColumn();
            weighingLogRequestDto.setOrderColumn(columnNameService.getColumnNameCalibrationTable(columnName));
            CalibrationTableEntityDto calibrationTableEntityDto = calibrationTableDao.getDataCalibrationTable(weighingLogRequestDto);
            WeightLogTableResponseDto weightLogTableResponseDto = new WeightLogTableResponseDto();
            if (weighingLogRequestDto.getRequestFlag().equals("request")) {
                weightLogTableResponseDto.setDraw(weighingLogRequestDto.getDraw());
                weightLogTableResponseDto.setRecordsTotal(calibrationTableEntityDto.getTotalElements());
                weightLogTableResponseDto.setRecordsFiltered(calibrationTableEntityDto.getTotalElements());
            }

            List<CalibrationTableDto> calibrationTableDtoList = new ArrayList();
            Iterator var6 = calibrationTableEntityDto.getCalibrationEntityList().iterator();

            while (var6.hasNext()) {
                CalibrationEntity ce = (CalibrationEntity) var6.next();
                CalibrationTableDto calibrationTableDto = new CalibrationTableDto();
                calibrationTableDto.setId(ce.getId());
                if (ce.getDate() != null) {
                    String pattern = "yyyy-MM-dd HH:mm:ss";
                    DateFormat df = new SimpleDateFormat(pattern);
                    Date date = ce.getDate();
                    String dateAsString = df.format(date);
                    calibrationTableDto.setDate(dateAsString);
                }

                calibrationTableDto.setNw(String.valueOf(ce.getNw()));
                calibrationTableDto.setPlmass(String.valueOf(ce.getPlmass()));
                calibrationTableDto.setFactmass(String.valueOf(ce.getFactmass()));
                calibrationTableDto.setOperfio(ce.getOperfio());
                calibrationTableDtoList.add(calibrationTableDto);
            }

            weightLogTableResponseDto.setData(calibrationTableDtoList);
            return weightLogTableResponseDto;
        } catch (Exception e) {
            log.error("Error getWeightLogTableData : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
            return null;
        }
    }
}
