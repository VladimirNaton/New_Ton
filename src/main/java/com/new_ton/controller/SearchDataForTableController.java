package com.new_ton.controller;


import com.new_ton.domain.dto.accountmanager.*;
import com.new_ton.domain.dto.technologistdto.EditeRecipeTableRequestDto;
import com.new_ton.domain.dto.technologistdto.*;
import com.new_ton.domain.dto.accountmanager.DrawDto;
import com.new_ton.domain.dto.testerdto.CheckTakeTemplateDto;
import com.new_ton.domain.dto.testerdto.ProtocolDto;
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

    @Secured({"ROLE_TECHNOLOGIST", "ROLE_ACCOUNTMANAGER", "ROLE_SUPERVISOR"})
    @GetMapping("/get-data-for-head-string-edite-recipe/{idProd}")
    public GetDataForInformationStringEditeRecipeDto getDataForInformationStringEditeRecipe(@PathVariable(name = "idProd") Integer idProd) {
        return searchDataForTablesService.getDataForInformationStringEditeRecipe(idProd);
    }

    @Secured({"ROLE_TECHNOLOGIST", "ROLE_ACCOUNTMANAGER"})
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
    @PostMapping("/get-data-for-product-in-production-table")
    public GetDataForProductInProductionTableRequestDto getDataForProductInProductionTable(DrawDto drawDto) {
        return searchDataForTablesService.getDataForProductInProductionTable(drawDto);
    }

    @Secured("ROLE_ACCOUNTMANAGER")
    @PostMapping("/account-manager-table-data")
    public AccountManagerTableDataResponseDto getDataForAccountManagerTable(AccountManagerTableRequestDto accountManagerTableRequestDto) {
        return searchDataForTablesService.getDataForAccountManagerTable(accountManagerTableRequestDto);
    }

    @Secured({"ROLE_TECHNOLOGIST", "ROLE_ACCOUNTMANAGER"})
    @PostMapping("/data-for-edite-recipe-catalog-table")
    public EditeCatalogRecipeResponseDto searchDataForEditeRecipeCatalogTable(EditeRecipeCatalogTableRequestDto editeRecipeTableRequestDto) {
        return searchDataForTablesService.getDataForEditeCatalogRecipe(editeRecipeTableRequestDto);
    }

    @Secured({"ROLE_TECHNOLOGIST", "ROLE_ACCOUNTMANAGER"})
    @GetMapping("/get-data-for-selected-row-edite-catalog-recipe-table/{id}")
    public GetDataForSelectedRowEditeCatalogRecipeTableResponseDto getDataForSelectedRowEditeRecipeCatalogTable(@PathVariable(name = "id") Integer id) {
        return searchDataForTablesService.getDataForSelectedRowEditeRecipeCatalogTable(id);
    }

    @Secured("ROLE_ACCOUNTMANAGER")
    @PostMapping("/data-for-dissolvers-table")
    public EditeDissolversResponceDto getDataForDissolversTable(EditeDissolversTableRequestDto editeDissolversTableRequestDto) {
        return searchDataForTablesService.getDataForDissolversTable(editeDissolversTableRequestDto);
    }

    @Secured("ROLE_ACCOUNTMANAGER")
    @GetMapping("/get-data-for-selected-row-dissolvers-table/{id}")
    public CateqDto getDataForSelectedRowDissolversTable(@PathVariable(name = "id") Integer id) {
        return searchDataForTablesService.getDataForSelectedRowDissolversTable(id);
    }

    @Secured("ROLE_ACCOUNTMANAGER")
    @PostMapping("/data-for-edite-component-table")
    public EditeCatalogTableResponseDto searchDataForEditeComponentTable(EditeRecipeComponentTableRequestDto editeRecipeTableRequestDto) {
        return searchDataForTablesService.searchDataForEditeComponentTable(editeRecipeTableRequestDto);
    }

    @Secured("ROLE_ACCOUNTMANAGER")
    @GetMapping("/data-selected-component/{id}")
    public ComponentTableDto getDataSelectedComponent(@PathVariable(name = "id") Integer id) {
        return searchDataForTablesService.getDataSelectedComponent(id);
    }

    @Secured("ROLE_TESTER")
    @PostMapping("/get-data-for-tester-table")
    public GetDataForProductInProductionTableRequestDto getDataForTesterTable(DrawDto drawDto) {
        return searchDataForTablesService.getDataForTesterTable(drawDto);
    }

    @Secured("ROLE_TESTER")
    @GetMapping("/get-data-for-tester-head-string/{idProd}")
    public GetDataForInformationStringEditeRecipeDto getDataForTesterStringEditeRecipe(@PathVariable(name = "idProd") Integer idProd) {
        return searchDataForTablesService.getDataForInformationStringEditeRecipe(idProd);
    }

    @Secured({"ROLE_TESTER", "ROLE_SUPERVISOR"})
    @PostMapping("/data-for-tester-recipe-table")
    public EditeRecipeResponseDto searchDataForViewRecipeTable(EditeRecipeTableRequestDto editeRecipeTableRequestDto) {
        return searchDataForTablesService.searchDataForEditeRecipeTable(editeRecipeTableRequestDto);
    }

    @Secured({"ROLE_TESTER", "ROLE_SUPERVISOR"})
    @GetMapping("/get-data-for-head-string-tester-recipe/{idProd}")
    public GetDataForInformationStringEditeRecipeDto getDataForInformationStringTesterRecipe(@PathVariable(name = "idProd") Integer idProd) {
        return searchDataForTablesService.getDataForInformationStringEditeRecipe(idProd);
    }

    @Secured({"ROLE_TESTER", "ROLE_SUPERVISOR"})
    @GetMapping("/get-data-for-selected-row-tester-recipe-table/{id}")
    public GetDataForSelectedRowEditeRecipeTableResponseDto getDataForSelectedRowTesterRecipeTable(@PathVariable(name = "id") Integer id) {
        return searchDataForTablesService.getDataForSelectedRowEditeRecipeTable(id);
    }

    @PostMapping("/get-comment-to-stage")
    public CommentToStageDto getCommentToStage(@RequestBody CommentToStageDto commentToStageDto) {
        return searchDataForTablesService.getCommentToStage(commentToStageDto);
    }

    @GetMapping("/check-take-template/{id}")
    public CheckTakeTemplateDto checkTakeTemplate(@PathVariable(name = "id") Integer id) {
        return searchDataForTablesService.checkTakeTemplate(id);
    }

    @GetMapping("/get-data-for-protocol/{id}")
    public ProtocolDto getDataForProtocol(@PathVariable(name = "id") Integer id) {
        return searchDataForTablesService.getDataForProtocol(id);
    }

    @Secured("ROLE_SUPERVISOR")
    @PostMapping("/get-data-for-product-for-production-table")
    public GetDataForProductInProductionTableRequestDto getDataForProductForProductionTable(DrawDto drawDto) {
        return searchDataForTablesService.getDataForProductForProductionTable(drawDto);
    }

    @Secured("ROLE_SUPERVISOR")
    @PostMapping("/get-data-for-task-shift-table")
    public GetDataForProductInProductionTableRequestDto getDataForTAskShiftTable(DrawDto drawDto) {
        return searchDataForTablesService.getDataForTaskShiftTable(drawDto);
    }

    @Secured("ROLE_SUPERVISOR")
    @PostMapping("/get-data-for-product-in-production-supervisor-table")
    public GetDataForProductInProductionTableRequestDto getDataForProductInProductionSupervisorTable(DrawDto drawDto) {
        return searchDataForTablesService.getDataForProductInProductionSupervisorTable(drawDto);
    }
}