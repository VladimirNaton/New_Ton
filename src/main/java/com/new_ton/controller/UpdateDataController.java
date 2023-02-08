package com.new_ton.controller;

import com.new_ton.domain.dto.AddOrReplaceComponentToRecipeRequestDto;
import com.new_ton.domain.dto.SaveRecipeDto;
import com.new_ton.domain.dto.SendProductToTellerDto;
import com.new_ton.domain.dto.UpdateSelectedRowOfRecipeDto;
import com.new_ton.service.UpdateDataServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/update")
@RestController
public class UpdateDataController {

    private final UpdateDataServiceImpl updateDataService;

    @PutMapping("/move-catalog-row-to-main/{id}")
    public boolean moveCatalogRowToMain(@PathVariable(name = "id") Integer id) {
        return updateDataService.moveCatalogRowToMain(id);
    }

    @PutMapping("/send-product-to-teller")
    public boolean sendProductToTeller(@RequestBody SendProductToTellerDto sendProductToTellerDto) {
        return updateDataService.sentProductToTeller(sendProductToTellerDto);
    }

    @DeleteMapping("/delete-selected-row-from-recipe-table/{id}")
    public boolean deleteSelectedRowFromRecipeTable(@PathVariable(name = "id") Integer id) {
        return updateDataService.deleteSelectedRowFromRecipeTable(id);
    }

    @PostMapping("/add-component-to-recipe")
    public boolean addComponentToRecipe(@RequestBody AddOrReplaceComponentToRecipeRequestDto addOrReplaceComponentToRecipeRequestDto) {
        return updateDataService.addComponentToRecipe(addOrReplaceComponentToRecipeRequestDto);
    }

    @PutMapping("/replace-selected-recipe-element")
    public boolean replaceSelectedRecipeElement(@RequestBody AddOrReplaceComponentToRecipeRequestDto addOrReplaceComponentToRecipeRequestDto) {
        return updateDataService.replaceSelectedRecipeElement(addOrReplaceComponentToRecipeRequestDto);
    }

    @PutMapping("/selected-row-of-recipe")
    public boolean updateSelectedRowOfRecipe(@RequestBody UpdateSelectedRowOfRecipeDto updateSelectedRowOfRecipeDto) {
        return updateDataService.updateSelectedRowOfRecipe(updateSelectedRowOfRecipeDto);
    }


    @PutMapping("/save-recipe")
    public boolean saveRecipe(@RequestBody SaveRecipeDto saveRecipeDto) {
        return updateDataService.saveRecipe(saveRecipeDto);
    }

    @PutMapping("/update-data-by-catalog-from-main/{id}")
    public boolean updateDataByCatalogFromMain(@PathVariable(name = "id") Integer idMain) {
        return updateDataService.updateDataByCatalogFromMain(idMain);
    }


}
