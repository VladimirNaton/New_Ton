package com.new_ton.service;


import com.new_ton.dao.LabProtDao;
import com.new_ton.dao.MainTableDao;
import com.new_ton.domain.dto.*;
import com.new_ton.domain.entities.LabprotEntity;
import com.new_ton.domain.entities.MainEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Log4j2
@Service
public class PrintTestReportServiceImpl implements PrintTestReportService {


    private final LabProtDao labProtDao;
    private final MainTableDao mainTableDao;
    private final TestReportPdfService testReportPdfService;
    private final ProductIntroductionCardPdfService productIntroductionCardPdfService;


    public boolean printTestReport(int id) {
        try {
            List<LabprotEntity> labprotEntityList = labProtDao.findAllByIdpr(id);
            Optional<MainEntity> mainEntityOptional = mainTableDao.findByIdpr(id);
            if (labprotEntityList.size() > 0 && mainEntityOptional.isPresent()) {
                PrintTestReportDto dto = new PrintTestReportDto();
                dto.setIdProd(id);
                MainEntity mainEntity =  mainEntityOptional.get();
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

                for (LabprotEntity lb : labprotEntityList) {
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

                productIntroductionCardPdfService.productIntroductionCard(dto);
                return testReportPdfService.createTestReportPdf(dto);
            }
        } catch (Exception e) {
            log.error("Error printTestReport : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }

        return false;
    }
}
