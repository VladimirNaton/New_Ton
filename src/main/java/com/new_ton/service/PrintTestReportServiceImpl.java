package com.new_ton.service;


import com.lider.dao.LabProtDao;
import com.lider.dao.MainTableDao;
import com.lider.domain.dto.*;
import com.lider.domain.entities.LabprotEntity;
import com.lider.domain.entities.MainEntity;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PrintTestReportServiceImpl implements PrintTestReportService {
    private static final Logger log = LoggerFactory.getLogger(PrintTestReportServiceImpl.class);
    private final LabProtDao labProtDao;
    private final MainTableDao mainTableDao;
    private final TestReportPdfService testReportPdfService;
    private final ProductIntroductionCardPdfService productIntroductionCardPdfService;

    public PrintTestReportServiceImpl(LabProtDao labProtDao, MainTableDao mainTableDao, TestReportPdfService testReportPdfService, ProductIntroductionCardPdfService productIntroductionCardPdfService) {
        this.labProtDao = labProtDao;
        this.mainTableDao = mainTableDao;
        this.testReportPdfService = testReportPdfService;
        this.productIntroductionCardPdfService = productIntroductionCardPdfService;
    }

    public boolean printTestReport(int id) {
        try {
            List<LabprotEntity> labprotEntityList = this.labProtDao.findAllByIdpr(id);
            Optional<MainEntity> mainEntityOptional = this.mainTableDao.findByIdpr(id);
            if (labprotEntityList.size() > 0 && mainEntityOptional.isPresent()) {
                PrintTestReportDto dto = new PrintTestReportDto();
                dto.setIdProd(id);
                MainEntity mainEntity = (MainEntity) mainEntityOptional.get();
                dto.setNameprod(mainEntity.getNameprod());
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                String strDateprot = dateFormat.format(mainEntity.getDateprot());
                dto.setDateprot(strDateprot);
                dto.setBrend(mainEntity.getBrend());
                dto.setNumbprot(mainEntity.getNumbprot());
                String strDatemade = dateFormat.format(mainEntity.getDatemade());
                dto.setDatemade(strDatemade);
                dto.setNumbpart(String.valueOf(mainEntity.getNumbpart()));
                String strExpdate = dateFormat.format(mainEntity.getExpdate());
                dto.setExpdate(strExpdate);
                dto.setLabfio(mainEntity.getLabfio());
                dto.setFiltr(mainEntity.getFiltr());
                Iterator var10 = labprotEntityList.iterator();

                while (var10.hasNext()) {
                    LabprotEntity lb = (LabprotEntity) var10.next();
                    int indexDilution = lb.getIndicator().indexOf("Разв");
                    int indexDensity = lb.getIndicator().indexOf("Плот");
                    int indexViscosity = lb.getIndicator().indexOf("Вязк");
                    int indexQuantity = lb.getIndicator().indexOf("газа");
                    int indexValveType = lb.getIndicator().indexOf("клап");
                    int indexSprayHeadType = lb.getIndicator().indexOf("голов");
                    int indexAppearance = lb.getIndicator().indexOf("Внеш");
                    int indexDegreeOfGloss = lb.getIndicator().indexOf("глян");
                    int indexColor = lb.getIndicator().indexOf("вет");
                    if (indexDilution != -1) {
                        DulitionDto dulitionDto = DulitionDto.builder().dev(lb.getDev()).nd(lb.getNd()).allvalues(lb.getAllvalues()).result(lb.getResult()).build();
                        dto.setDulitionDto(dulitionDto);
                    }

                    if (indexDensity != -1) {
                        DensityDto densityDto = DensityDto.builder().dev(lb.getDev()).nd(lb.getNd()).allvalues(lb.getAllvalues()).result(lb.getResult()).build();
                        dto.setDensityDto(densityDto);
                    }

                    if (indexViscosity != -1) {
                        ViscosityDto viscosityDto = ViscosityDto.builder().dev(lb.getDev()).nd(lb.getNd()).allvalues(lb.getAllvalues()).result(lb.getResult()).build();
                        dto.setViscosityDto(viscosityDto);
                    }

                    if (indexQuantity != -1) {
                        QuantityDto quantityDto = QuantityDto.builder().dev(lb.getDev()).nd(lb.getNd()).allvalues(lb.getAllvalues()).result(lb.getResult()).build();
                        dto.setQuantityDto(quantityDto);
                    }

                    if (indexValveType != -1) {
                        ValveTypeDto valveTypeDto = ValveTypeDto.builder().dev(lb.getDev()).nd(lb.getNd()).allvalues(lb.getAllvalues()).result(lb.getResult()).build();
                        dto.setValveTypeDto(valveTypeDto);
                    }

                    if (indexSprayHeadType != -1) {
                        SprayHeadTypeDto sprayHeadTypeDto = SprayHeadTypeDto.builder().dev(lb.getDev()).nd(lb.getNd()).allvalues(lb.getAllvalues()).result(lb.getResult()).build();
                        dto.setSprayHeadTypeDto(sprayHeadTypeDto);
                    }

                    if (indexAppearance != -1) {
                        AppearanceDto appearanceDto = AppearanceDto.builder().dev(lb.getDev()).nd(lb.getNd()).allvalues(lb.getAllvalues()).result(lb.getResult()).build();
                        dto.setAppearanceDto(appearanceDto);
                    }

                    if (indexDegreeOfGloss != -1) {
                        DegreeOfGlossDto degreeOfGlossDto = DegreeOfGlossDto.builder().dev(lb.getDev()).nd(lb.getNd()).allvalues(lb.getAllvalues()).result(lb.getResult()).build();
                        dto.setDegreeOfGlossDto(degreeOfGlossDto);
                    }

                    if (indexColor != -1) {
                        ColorDto colorDto = ColorDto.builder().dev(lb.getDev()).nd(lb.getNd()).allvalues(lb.getAllvalues()).result(lb.getResult()).build();
                        dto.setColorDto(colorDto);
                    }
                }

                this.productIntroductionCardPdfService.productIntroductionCard(dto);
                return this.testReportPdfService.createTestReportPdf(dto);
            }
        } catch (Exception var22) {
            log.error("Error printTestReport : {}, {}", ExceptionUtils.getMessage(var22), ExceptionUtils.getMessage(var22.getCause()));
        }

        return false;
    }
}
