package com.new_ton.service;

import com.new_ton.dao.SearchDataForTablesDao;
import com.new_ton.domain.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public TechnologistPageRightTableResponseDto getDataForTechnologistRightTable(RequestTechnologistPageRightTableDto requestTechnologistPageRightTableDto) {
        String columnName = columnNameService.getColumnNameByTechnologistPageRightDataTable(requestTechnologistPageRightTableDto.getOrderColumn());
        Pageable pageable;
        if (requestTechnologistPageRightTableDto.getOrderType().equals("asc")) {
            pageable = PageRequest.of(requestTechnologistPageRightTableDto.getStart() / requestTechnologistPageRightTableDto.getLength(), requestTechnologistPageRightTableDto.getLength(), Sort.by(columnName).ascending());
        } else {
            pageable = PageRequest.of(requestTechnologistPageRightTableDto.getStart() / requestTechnologistPageRightTableDto.getLength(), requestTechnologistPageRightTableDto.getLength(), Sort.by(columnName).descending());
        }
        Page<MainTableDto> page = searchDataForTablesDao.getDataForTechnologistRightTable(pageable);
        TechnologistPageRightTableResponseDto responseDto = new TechnologistPageRightTableResponseDto();
        responseDto.setDraw(requestTechnologistPageRightTableDto.getDraw());
        responseDto.setRecordsTotal(page.getTotalElements());
        List<MainTableDto> mainTableDtoList = page.getContent().stream().peek(elem -> {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String strDate = dateFormat.format(elem.getDatecr());
            elem.setDateCreate(strDate);
        }).collect(Collectors.toList());
        responseDto.setData(mainTableDtoList);
        responseDto.setRecordsFiltered(page.getTotalElements());
        return responseDto;
    }
}
