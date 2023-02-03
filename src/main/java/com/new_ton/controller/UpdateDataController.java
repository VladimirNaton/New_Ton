package com.new_ton.controller;

import com.new_ton.domain.dto.SendProductToTellerDto;
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


}
