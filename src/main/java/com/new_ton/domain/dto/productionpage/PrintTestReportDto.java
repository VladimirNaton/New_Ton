package com.new_ton.domain.dto.productionpage;

import lombok.Data;

@Data
public class PrintTestReportDto {
    private Integer idProd;
    private Integer numbprot;
    private String dateprot;
    private String brend;
    private String nameprod;
    private String datemade;
    private String numbpart;
    private String expdate;
    private String labfio;
    private String filtr;
    private DulitionDto dulitionDto;
    private DensityDto densityDto;
    private ViscosityDto viscosityDto;
    private QuantityDto quantityDto;
    private ValveTypeDto valveTypeDto;
    private SprayHeadTypeDto sprayHeadTypeDto;
    private AppearanceDto appearanceDto;
    private DegreeOfGlossDto degreeOfGlossDto;
    private ColorDto colorDto;
}
