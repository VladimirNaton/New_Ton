package com.new_ton.service;

import com.new_ton.dao.UploadTableDao;
import com.new_ton.domain.dto.productionpage.DischargePageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class DischargePageServiceImpl implements DischargePageService {
    private final UploadTableDao uploadTableDao;

    public List<DischargePageDto> getDischangeList(int id) {
        try {
            return uploadTableDao.getUnloadEntityById(id).stream().map((ue) -> {
                return DischargePageDto.builder().idpr(ue.getIdpr()).numb(ue.getNumb()).barrel(ue.getBarrel()).mass(ue.getMass()).build();
            }).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error getDischangeLis : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
            return Collections.emptyList();
        }
    }
}

