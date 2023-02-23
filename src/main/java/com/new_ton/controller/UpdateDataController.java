package com.new_ton.controller;

import com.new_ton.domain.dto.accountmanager.AddOrReplaceComponentToCatalogRecipeDto;
import com.new_ton.domain.dto.accountmanager.ReturnRecipeToTechnologistRequestDto;
import com.new_ton.domain.dto.technologistdto.*;
import com.new_ton.domain.dto.accountmanager.SaveCatalogRecipeDto;
import com.new_ton.domain.entities.CreateCateqDto;
import com.new_ton.service.UpdateDataServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/update")
@RestController
public class UpdateDataController {

    private final UpdateDataServiceImpl updateDataService;

    @Secured("ROLE_TECHNOLOGIST")
    @PutMapping("/move-catalog-row-to-main/{id}")
    public boolean moveCatalogRowToMain(@PathVariable(name = "id") Integer id) {
        return updateDataService.moveCatalogRowToMain(id);
    }

    @Secured("ROLE_TECHNOLOGIST")
    @PutMapping("/send-product-to-teller")
    public boolean sendProductToTeller(@RequestBody SendProductToAccountManagerDto sendProductToAccountManagerDto) {
        return updateDataService.sentProductToTeller(sendProductToAccountManagerDto);
    }

    @Secured("ROLE_TECHNOLOGIST")
    @DeleteMapping("/delete-selected-row-from-recipe-table/{id}")
    public boolean deleteSelectedRowFromRecipeTable(@PathVariable(name = "id") Integer id) {
        return updateDataService.deleteSelectedRowFromRecipeTable(id);
    }

    @Secured("ROLE_TECHNOLOGIST")
    @PostMapping("/add-component-to-recipe")
    public boolean addComponentToRecipe(@RequestBody AddOrReplaceComponentToRecipeRequestDto addOrReplaceComponentToRecipeRequestDto) {
        return updateDataService.addComponentToRecipe(addOrReplaceComponentToRecipeRequestDto);
    }

    @Secured("ROLE_TECHNOLOGIST")
    @PutMapping("/replace-selected-recipe-element")
    public boolean replaceSelectedRecipeElement(@RequestBody AddOrReplaceComponentToRecipeRequestDto addOrReplaceComponentToRecipeRequestDto) {
        return updateDataService.replaceSelectedRecipeElement(addOrReplaceComponentToRecipeRequestDto);
    }

    @Secured("ROLE_TECHNOLOGIST")
    @PutMapping("/selected-row-of-recipe")
    public boolean updateSelectedRowOfRecipe(@RequestBody UpdateSelectedRowOfRecipeDto updateSelectedRowOfRecipeDto) {
        return updateDataService.updateSelectedRowOfRecipe(updateSelectedRowOfRecipeDto);
    }


    @Secured("ROLE_TECHNOLOGIST")
    @PutMapping("/save-recipe")
    public boolean saveRecipe(@RequestBody SaveRecipeDto saveRecipeDto) {
        return updateDataService.saveRecipe(saveRecipeDto);
    }

    @Secured("ROLE_TECHNOLOGIST")
    @PutMapping("/update-data-by-catalog-from-main/{id}")
    public boolean updateDataByCatalogFromMain(@PathVariable(name = "id") Integer idMain) {
        return updateDataService.updateDataByCatalogFromMain(idMain);
    }

    @Secured("ROLE_ACCOUNTMANAGER")
    @PutMapping("/return-recipe-to-technologist")
    public boolean returnRecipeToTechnologist(@RequestBody ReturnRecipeToTechnologistRequestDto returnRecipeToTechnologistRequestDto) {
        return updateDataService.returnRecipeToTechnologist(returnRecipeToTechnologistRequestDto);
    }

    @Secured("ROLE_ACCOUNTMANAGER")
    @PutMapping("/send-to-production")
    public boolean sentToProductionRecipe(@RequestBody ReturnRecipeToTechnologistRequestDto returnRecipeToTechnologistRequestDto) {
        return updateDataService.sendToProduction(returnRecipeToTechnologistRequestDto);
    }

    @Secured({"ROLE_ACCOUNTMANAGER", "ROLE_TECHNOLOGIST"})
    @PutMapping("/update-selected-catalog-row")
    public boolean updateSelectedCatalogRow(@RequestBody CatalogDtoSelectedRow catalogDtoSelectedRow) {
        return updateDataService.updateSelectedCatalogRow(catalogDtoSelectedRow);
    }

    @Secured({"ROLE_ACCOUNTMANAGER", "ROLE_TECHNOLOGIST"})
    @PutMapping("/delete-selected-catalog-row/{id}")
    public boolean deleteSelectedCatalogRow(@PathVariable(name = "id") Integer id) {
        return updateDataService.deleteSelectedCatalogRow(id);
    }

    @Secured({"ROLE_ACCOUNTMANAGER", "ROLE_TECHNOLOGIST"})
    @PutMapping("/add-new-recipe")
    public boolean addNewRecipe(@RequestBody CatalogDtoSelectedRow catalogDtoSelectedRow) {
        return updateDataService.addNewRecipe(catalogDtoSelectedRow);
    }

    @Secured({"ROLE_TECHNOLOGIST", "ROLE_ACCOUNTMANAGER"})
    @PostMapping("/add-component-to-catalog-recipe")
    public boolean addComponentToCatalogRecipe(@RequestBody AddOrReplaceComponentToCatalogRecipeDto addOrReplaceComponentToRecipeRequestDto) {
        return updateDataService.addComponentToCatalogRecipe(addOrReplaceComponentToRecipeRequestDto);
    }

    @Secured({"ROLE_TECHNOLOGIST", "ROLE_ACCOUNTMANAGER"})
    @DeleteMapping("/delete-selected-row-from-recipe-catalog-table/{id}")
    public boolean deleteSelectedRowFromRecipeCatalogTable(@PathVariable(name = "id") Integer id) {
        return updateDataService.deleteSelectedRowFromRecipeCatalogTable(id);
    }

    @Secured({"ROLE_TECHNOLOGIST", "ROLE_ACCOUNTMANAGER"})
    @PutMapping("/replace-selected-catalog-recipe-element")
    public boolean replaceSelectedRecipeCatalogElement(@RequestBody AddOrReplaceComponentToCatalogRecipeDto addOrReplaceComponentToRecipeRequestDto) {
        return updateDataService.replaceSelectedCatalogRecipeElement(addOrReplaceComponentToRecipeRequestDto);
    }

    @Secured({"ROLE_TECHNOLOGIST", "ROLE_ACCOUNTMANAGER"})
    @PutMapping("/selected-catalog-row-of-recipe")
    public boolean updateSelectedCatalogRowOfRecipe(@RequestBody UpdateSelectedRowOfRecipeDto updateSelectedRowOfRecipeDto) {
        return updateDataService.updateSelectedCatalogRowOfRecipe(updateSelectedRowOfRecipeDto);
    }

    @Secured({"ROLE_TECHNOLOGIST", "ROLE_ACCOUNTMANAGER"})
    @PutMapping("/save-catalog-recipe")
    public boolean saveCatalogRecipe(@RequestBody SaveCatalogRecipeDto catalogRecipeDto) {
        return updateDataService.saveCatalogRecipe(catalogRecipeDto);
    }

    @Secured("ROLE_ACCOUNTMANAGER")
    @DeleteMapping("/delete-selected-dissolver-row/{id}")
    public boolean deleteSelectedDissolverRow(@PathVariable(name = "id") Integer id) {
        return updateDataService.deleteSelectedDissolverRow(id);
    }

    @Secured("ROLE_ACCOUNTMANAGER")
    @PostMapping("/create-new-cateq-row")
    public boolean createNewCateqRow(@RequestBody CreateCateqDto cateqDto) {
        return updateDataService.createNewCateqRow(cateqDto);
    }

    @Secured("ROLE_ACCOUNTMANAGER")
    @PutMapping("/update-cateq-row")
    public boolean updateCateqRow(@RequestBody CreateCateqDto cateqDto) {
        return updateDataService.updateCateqRow(cateqDto);
    }


}
