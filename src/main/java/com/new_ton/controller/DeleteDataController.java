package com.new_ton.controller;

import com.new_ton.service.DeleteDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/delete")
@RestController
public class DeleteDataController {

    private final DeleteDataService deleteDataService;

    @DeleteMapping("/selected-row-from-right-data-table-technologist-page")
    public boolean deleteSelectedRowFromTechnologistPageRightDataTable(@RequestParam Integer idProd) {
        return deleteDataService.deleteDataSelectedRowRightTableTechnologistPage(idProd);
    }
}
