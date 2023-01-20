package com.new_ton.service;

import com.new_ton.dao.SearchDataForTablesDao;
import com.new_ton.domain.dto.CatalogDto;
import com.new_ton.domain.dto.RequestTechnologistPageLeftTableDto;
import com.new_ton.domain.dto.TechnologistPageLeftTableResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchDataForTablesServiceImpl implements SearchDataForTablesService {
    private final SearchDataForTablesDao searchDataForTablesDao;
    private final ColumnNameService columnNameService;


    @Override
    public TechnologistPageLeftTableResponseDto getDataForTechnologistLeftTable(RequestTechnologistPageLeftTableDto requestTechnologistPageLeftTableDto) {
        String columnName = columnNameService.getColumnNameByTechnologistPageLeftDataTable(requestTechnologistPageLeftTableDto.getOrderColumn());
        Pageable pageable;
        if (requestTechnologistPageLeftTableDto.getOrderType().equals("asc")) {
            pageable = PageRequest.of(requestTechnologistPageLeftTableDto.getStart() / requestTechnologistPageLeftTableDto.getLength(), requestTechnologistPageLeftTableDto.getLength(), Sort.by(columnName).ascending());
        } else {
            pageable = PageRequest.of(requestTechnologistPageLeftTableDto.getStart() / requestTechnologistPageLeftTableDto.getLength(), requestTechnologistPageLeftTableDto.getLength(), Sort.by(columnName).descending());
        }
        Page<CatalogDto> page = searchDataForTablesDao.getDataForTechnologistLeftTable(pageable);
        TechnologistPageLeftTableResponseDto responseDto = new TechnologistPageLeftTableResponseDto();
        responseDto.setDraw(requestTechnologistPageLeftTableDto.getDraw());
        responseDto.setRecordsTotal(page.getTotalElements());
        responseDto.setData(page.getContent());
        responseDto.setRecordsFiltered(page.getTotalElements());
        return responseDto;
    }
}
