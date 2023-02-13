package com.new_ton.controller;


import com.itextpdf.text.DocumentException;
import com.new_ton.domain.dto.productionpage.*;
import com.new_ton.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping({"/api/v1"})
@RestController
public class NewTonRestController {

    private final WeightLogTableService weightLogTableService;
    private final GetFioOperService getFioOperService;
    private final ProductTableService productTableService;
    private final PrintDischargePageService printDischargePageService;
    private final PrintTestReportService printTestReportService;
    private final GetDataForRecipePageService getDataForRecipePageService;


    @PostMapping({"/getTableData"})
    public WeightLogTableResponseDto insertWeighingLog(WeighingLogRequestDto weighingLogRequestDto) {
        return weightLogTableService.getWeightLogTableData(weighingLogRequestDto);
    }

    @GetMapping({"/getFioOper"})
    public List<String> getFioOper() {
        return getFioOperService.getFioOper();
    }

    @PostMapping({"/getProductTableData"})
    public ProductResponseDto getProductTableData(RequestDataTableDto requestDataTableDto) {
        return productTableService.getProductTableDate(requestDataTableDto);
    }

    @PostMapping({"/printDischargePage"})
    public ResponseEntity<?> printDischargePage(@RequestParam int id) throws DocumentException, IOException {
        boolean result = printDischargePageService.printDischargePage(id);
        return result ? ResponseEntity.ok("") : ResponseEntity.notFound().build();
    }

    @PostMapping({"/printTestReport"})
    public ResponseEntity<?> printTestReport(@RequestParam int id) {
        boolean result = printTestReportService.printTestReport(id);
        return result ? ResponseEntity.ok("") : ResponseEntity.notFound().build();
    }

    @PostMapping({"/recipePageData"})
    public ResponseEntity<?> recipePageData(@RequestParam int id) {
        RecipePageDataDto recipePageDataDto = getDataForRecipePageService.getDataForRecipePage(id);
        return recipePageDataDto != null ? ResponseEntity.ok(recipePageDataDto) : ResponseEntity.notFound().build();
    }
}
