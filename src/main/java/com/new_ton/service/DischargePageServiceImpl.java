package com.new_ton.service;

import com.new_ton.dao.UploadTableDao;
import com.new_ton.domain.dto.DischargePageDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DischargePageServiceImpl implements DischargePageService {
    private static final Logger log = LoggerFactory.getLogger(DischargePageServiceImpl.class);
    private final UploadTableDao uploadTableDao;

    public List<DischargePageDto> getDischangeList(int id) {
        try {
            return (List)this.uploadTableDao.getUnloadEntityById(id).stream().map((ue) -> {
                return DischargePageDto.builder().idpr(ue.getIdpr()).numb(ue.getNumb()).barrel(ue.getBarrel()).mass(ue.getMass()).build();
            }).collect(Collectors.toList());
        } catch (Exception var3) {
            log.error("Error getDischangeLis : {}, {}", ExceptionUtils.getMessage(var3), ExceptionUtils.getMessage(var3.getCause()));
            return Collections.emptyList();
        }
    }
}

