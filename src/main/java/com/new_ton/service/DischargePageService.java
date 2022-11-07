package com.new_ton.service;

import com.new_ton.domain.dto.DischargePageDto;

import java.util.List;

public interface DischargePageService {
    List<DischargePageDto> getDischangeList(int id);
}
