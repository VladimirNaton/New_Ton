package com.new_ton.domain.dto.productionpage;

import lombok.Data;

@Data
public class CreateResponsibleSemiFinishedProductDto {
    private String dilution;
    private String density;
    private String viscosity;
    private String quantity;
    private String valveType;
    private String sprayHeadType;
    private String brand;
    private String productName;
    private String dateOfManufacture;
    private String batchNumber;
    private String bestBeforeDate;
    private String sectionManager;
    private String dateToday;
    private String number;
    private Integer path;
    private Integer idProd;
}
