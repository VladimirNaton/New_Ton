package com.new_ton.controller;


import com.new_ton.domain.dto.accountmanager.AccountManagerTableDataResponseDto;
import com.new_ton.domain.dto.accountmanager.AccountManagerTableRequestDto;
import com.new_ton.domain.dto.technologistdto.EditeRecipeTableRequestDto;
import com.new_ton.domain.dto.technologistdto.*;
import com.new_ton.service.SearchDataForTablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/search")
@RestController
public class SearchDataForTableController {

    private final SearchDataForTablesService searchDataForTablesService;

    @Secured({"ROLE_TECHNOLOGIST", "ROLE_ACCOUNTMANAGER"})
    @PostMapping("/data-for-technologist-left-table")
    public TechnologistPageLeftTableResponseDto getDataForLeftProductTableTechnologistPage(RequestTechnologistPageLeftTableDto requestTechnologistPageLeftTableDto) {
        return searchDataForTablesService.getDataForTechnologistLeftTable(requestTechnologistPageLeftTableDto);
    }

    @Secured("ROLE_TECHNOLOGIST")
    @PostMapping("/data-for-technologist-right-table")
    public TechnologistPageRightTableResponseDto getDataForRightProductTableTechnologistPage(RequestTechnologistPageRightTableDto requestTechnologistPageRightTableDto) {
        return searchDataForTablesService.getDataForTechnologistRightTable(requestTechnologistPageRightTableDto);
    }

    @Secured({"ROLE_TECHNOLOGIST", "ROLE_ACCOUNTMANAGER"})
    @GetMapping("/get-data-by-selected-catalog-row")
    public CatalogDtoSelectedRow getDataBySelectedCatalogRow(@RequestParam Integer idProd) {
        return searchDataForTablesService.searchDataFromSelectedCatalogRow(idProd);
    }

    @Secured({"ROLE_TECHNOLOGIST", "ROLE_ACCOUNTMANAGER"})
    @PostMapping("/data-for-edite-recipe-table")
    public EditeRecipeResponseDto searchDataForEditeRecipeTable(EditeRecipeTableRequestDto editeRecipeTableRequestDto) {
        return searchDataForTablesService.searchDataForEditeRecipeTable(editeRecipeTableRequestDto);
    }

    @Secured({"ROLE_TECHNOLOGIST", "ROLE_ACCOUNTMANAGER"})
    @GetMapping("/get-data-for-head-string-edite-recipe/{idProd}")
    public GetDataForInformationStringEditeRecipeDto getDataForInformationStringEditeRecipe(@PathVariable(name = "idProd") Integer idProd) {
        return searchDataForTablesService.getDataForInformationStringEditeRecipe(idProd);
    }

    @Secured("ROLE_TECHNOLOGIST")
    @PostMapping("/data-for-edite-recipe-component-table")
    public EditeRecipeCatalogTableResponseDto searchDataForEditeRecipeComponentTable(EditeRecipeComponentTableRequestDto editeRecipeTableRequestDto) {
        return searchDataForTablesService.getDataForEditeRecipeComponentTable(editeRecipeTableRequestDto);
    }

    @Secured("ROLE_TECHNOLOGIST")
    @GetMapping("/get-data-for-selected-row-edite-recipe-table/{id}")
    public GetDataForSelectedRowEditeRecipeTableResponseDto getDataForSelectedRowEditeRecipeTable(@PathVariable(name = "id") Integer id) {
        return searchDataForTablesService.getDataForSelectedRowEditeRecipeTable(id);
    }

    @Secured("ROLE_TECHNOLOGIST")
    @GetMapping("/get-data-for-product-in-production-table")
    public GetDataForProductInProductionTableRequestDto getDataForProductInProductionTable() {
        return searchDataForTablesService.getDataForProductInProductionTable();
    }

    @Secured("ROLE_ACCOUNTMANAGER")
    @PostMapping("/account-manager-table-data")
    public AccountManagerTableDataResponseDto getDataForAccountManagerTable(AccountManagerTableRequestDto accountManagerTableRequestDto) {
        return searchDataForTablesService.getDataForAccountManagerTable(accountManagerTableRequestDto);
    }

}
