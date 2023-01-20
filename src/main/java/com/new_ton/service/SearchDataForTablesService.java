package com.new_ton.service;

import com.new_ton.domain.dto.RequestTechnologistPageLeftTableDto;
import com.new_ton.domain.dto.TechnologistPageLeftTableResponseDto;

public interface SearchDataForTablesService {

    TechnologistPageLeftTableResponseDto getDataForTechnologistLeftTable(RequestTechnologistPageLeftTableDto requestTechnologistPageLeftTableDto);
}
