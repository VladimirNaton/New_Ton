package com.new_ton.controller;


import com.new_ton.domain.dto.*;
import com.new_ton.domain.dto.EditeRecipeTableRequestDto;
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


    @PostMapping("/data-for-edite-recipe-table")
    public EditeRecipeResponseDto searchDataForEditeRecipeTable(EditeRecipeTableRequestDto editeRecipeTableRequestDto) {
        return searchDataForTablesService.searchDataForEditeRecipeTable(editeRecipeTableRequestDto);
    }


    @GetMapping("/get-data-for-head-string-edite-recipe/{idProd}")
    public GetDataForInformationStringEditeRecipeDto getDataForInformationStringEditeRecipe(@PathVariable(name = "idProd") Integer idProd) {
        return searchDataForTablesService.getDataForInformationStringEditeRecipe(idProd);
    }

    @PostMapping("/data-for-edite-recipe-component-table")
    public EditeRecipeCatalogTableResponseDto searchDataForEditeRecipeComponentTable(EditeRecipeComponentTableRequestDto editeRecipeTableRequestDto) {
        return searchDataForTablesService.getDataForEditeRecipeComponentTable(editeRecipeTableRequestDto);
    }


    @GetMapping("/get-data-for-selected-row-edite-recipe-table/{id}")
    public GetDataForSelectedRowEditeRecipeTableResponseDto getDataForSelectedRowEditeRecipeTable(@PathVariable(name = "id") Integer id) {
        return searchDataForTablesService.getDataForSelectedRowEditeRecipeTable(id);
    }

    @GetMapping("/get-data-for-product-in-production-table")
    public GetDataForProductInProductionTableRequestDto getDataForProductInProductionTable() {
        return searchDataForTablesService.getDataForProductInProductionTable();
    }

}
