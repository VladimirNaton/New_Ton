package com.new_ton.controller;


import com.new_ton.domain.dto.*;
import com.new_ton.service.SearchDataForTablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/search")
@RestController
public class SearchDataForTableController {

    private final SearchDataForTablesService searchDataForTablesService;


    @PostMapping("/data-for-technologist-left-table")
    public TechnologistPageLeftTableResponseDto getDataForLeftProductTableTechnologistPage(RequestTechnologistPageLeftTableDto requestTechnologistPageLeftTableDto) {
        return searchDataForTablesService.getDataForTechnologistLeftTable(requestTechnologistPageLeftTableDto);
    }


    @PostMapping("/data-for-technologist-right-table")
    public TechnologistPageRightTableResponseDto getDataForRightProductTableTechnologistPage(RequestTechnologistPageRightTableDto requestTechnologistPageRightTableDto) {
        return searchDataForTablesService.getDataForTechnologistRightTable(requestTechnologistPageRightTableDto);
    }

    @GetMapping("/get-data-by-selected-catalog-row")
    public CatalogDtoSelectedRow getDataBySelectedCatalogRow(@RequestParam Integer idProd) {
        return searchDataForTablesService.searchDataFromSelectedCatalogRow(idProd);
    }


}
