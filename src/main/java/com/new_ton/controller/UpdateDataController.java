package com.new_ton.controller;

import com.new_ton.domain.dto.accountmanager.ReturnRecipeToTechnologistRequestDto;
import com.new_ton.domain.dto.technologistdto.AddOrReplaceComponentToRecipeRequestDto;
import com.new_ton.domain.dto.technologistdto.SaveRecipeDto;
import com.new_ton.domain.dto.technologistdto.SendProductToAccountManagerDto;
import com.new_ton.domain.dto.technologistdto.UpdateSelectedRowOfRecipeDto;
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

    @PutMapping("/return-recipe-to-technologist")
    public boolean returnRecipeToTechnologist(@RequestBody ReturnRecipeToTechnologistRequestDto returnRecipeToTechnologistRequestDto) {
        return updateDataService.returnRecipeToTechnologist(returnRecipeToTechnologistRequestDto);
    }

    @PutMapping("/send-to-production")
    public boolean sentToProductionRecipe(@RequestBody ReturnRecipeToTechnologistRequestDto returnRecipeToTechnologistRequestDto) {
        return updateDataService.sendToProduction(returnRecipeToTechnologistRequestDto);
    }


}
