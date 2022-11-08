package com.new_ton.controller;


import com.new_ton.domain.dto.*;
import com.new_ton.service.CalibrationTableExelServiceImpl;
import com.new_ton.service.ProductTableExelServiceImpl;
import com.new_ton.service.ProductTableService;
import com.new_ton.service.WeightLogTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping({"/service/export/excel"})
@Controller
public class CreateExelForCalibrationTableController {

    private final WeightLogTableService weightLogTableService;
    private final ProductTableService productTableService;


    @GetMapping({"/calibrationTable"})
    public void exportToExcelCalibrationTable(HttpServletResponse response, @RequestParam(name = "orderColumn",required = false) String orderColumn, @RequestParam(name = "orderType",required = false) String orderType, @RequestParam(name = "startDate",required = false) String startDate, @RequestParam(name = "endDate",required = false) String endDate, @RequestParam(name = "scales",required = false) String scales, @RequestParam(name = "fioOper",required = false) String fioOper) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=calibration_table" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        WeighingLogRequestDto weighingLogRequestDto = new WeighingLogRequestDto();
        weighingLogRequestDto.setOrderColumn(orderColumn);
        weighingLogRequestDto.setOrderType(orderType);
        weighingLogRequestDto.setStartDate(startDate);
        weighingLogRequestDto.setEndDate(endDate);
        weighingLogRequestDto.setScales(scales);
        weighingLogRequestDto.setFioOper(fioOper);
        weighingLogRequestDto.setRequestFlag("exel");
        WeightLogTableResponseDto weightLogTableResponseDto = weightLogTableService.getWeightLogTableData(weighingLogRequestDto);
        List<CalibrationTableDto> calibrationTableDtoList = weightLogTableResponseDto.getData();
        CalibrationTableExelServiceImpl excelExporter = new CalibrationTableExelServiceImpl(calibrationTableDtoList);
        excelExporter.export(response);
    }

    @GetMapping({"/productTable"})
    public void exportToExcelProductTable(HttpServletResponse response, @RequestParam(name = "orderColumn",required = false) String orderColumn, @RequestParam(name = "orderType",required = false) String orderType, @RequestParam(name = "typeDate",required = false) String typeDate, @RequestParam(name = "startDate",required = false) String startDate, @RequestParam(name = "endDate",required = false) String endDate, @RequestParam(name = "brend",required = false) String brend, @RequestParam(name = "productName",required = false) String productName, @RequestParam(name = "specification",required = false) String specification) {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=product_table" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        ProductTableRequestDto productTableRequestDto = new ProductTableRequestDto();
        productTableRequestDto.setOrderColumn(orderColumn);
        productTableRequestDto.setOrderType(orderType);
        productTableRequestDto.setTypeDate(typeDate);
        productTableRequestDto.setStartDate(startDate);
        productTableRequestDto.setEndDate(endDate);
        productTableRequestDto.setBrend(brend);
        productTableRequestDto.setProductName(productName);
        productTableRequestDto.setSpecification(specification);
        productTableRequestDto.setRequestFlag("exel");
        ProductTableResponseDto productTableResponseDto = productTableService.getProductTableDate(productTableRequestDto);
        List<ProductTableDto> productTableDtoList = productTableResponseDto.getData();
        ProductTableExelServiceImpl excelExporter = new ProductTableExelServiceImpl(productTableDtoList);
        excelExporter.export(response);
    }
}
