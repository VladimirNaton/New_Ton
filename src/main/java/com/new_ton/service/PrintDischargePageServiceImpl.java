package com.new_ton.service;


import com.lider.dao.LabProtDao;
import com.lider.dao.MainTableDao;
import com.lider.dao.UploadTableDao;
import com.lider.domain.dto.CreateResponsibleSemiFinishedProductDto;
import com.lider.domain.entities.LabprotEntity;
import com.lider.domain.entities.MainEntity;
import com.lider.domain.entities.UnloadEntity;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
public class PrintDischargePageServiceImpl implements PrintDischargePageService {
    private static final Logger log = LoggerFactory.getLogger(PrintDischargePageServiceImpl.class);
    private final UploadTableDao uploadTableDao;
    private final MainTableDao mainTableDao;
    private final LabProtDao labProtDao;
    private final ResponsibleSemiFinishedProductPdfService responsibleSemiFinishedProductPdfService;

    public PrintDischargePageServiceImpl(UploadTableDao uploadTableDao, MainTableDao mainTableDao, LabProtDao labProtDao, ResponsibleSemiFinishedProductPdfService responsibleSemiFinishedProductPdfService) {
        this.uploadTableDao = uploadTableDao;
        this.mainTableDao = mainTableDao;
        this.labProtDao = labProtDao;
        this.responsibleSemiFinishedProductPdfService = responsibleSemiFinishedProductPdfService;
    }

    public boolean printDischargePage(int id) {
        try {
            List<LabprotEntity> labprotEntityList = this.labProtDao.findAllByIdpr(id);
            Optional<MainEntity> mainEntityOptional = this.mainTableDao.findByIdpr(id);
            List<UnloadEntity> unloadEntityList = this.uploadTableDao.getUnloadEntityById(id);
            Iterator var5 = unloadEntityList.iterator();

            boolean result;
            do {
                UnloadEntity unloadEntity;
                do {
                    if (!var5.hasNext()) {
                        return true;
                    }

                    unloadEntity = (UnloadEntity)var5.next();
                } while(unloadEntity.getBarrel().equals("Потери"));

                CreateResponsibleSemiFinishedProductDto dto = new CreateResponsibleSemiFinishedProductDto();
                Iterator var8 = labprotEntityList.iterator();

                while(var8.hasNext()) {
                    LabprotEntity lb = (LabprotEntity)var8.next();
                    int indexDilution = lb.getIndicator().indexOf("Разв");
                    int indexDensity = lb.getIndicator().indexOf("Плот");
                    int indexViscosity = lb.getIndicator().indexOf("Вязк");
                    int indexQuantity = lb.getIndicator().indexOf("газа");
                    int indexValveType = lb.getIndicator().indexOf("клап");
                    int indexSprayHeadType = lb.getIndicator().indexOf("голов");
                    if (indexDilution != -1) {
                        dto.setDilution(lb.getResult());
                    }

                    if (indexDensity != -1) {
                        dto.setDensity(lb.getResult());
                    }

                    if (indexViscosity != -1) {
                        dto.setViscosity(lb.getResult());
                    }

                    if (indexQuantity != -1) {
                        dto.setQuantity(lb.getResult());
                    }

                    if (indexValveType != -1) {
                        dto.setValveType(lb.getResult());
                    }

                    if (indexSprayHeadType != -1) {
                        dto.setSprayHeadType(lb.getResult());
                    }
                }

                if (mainEntityOptional.isPresent()) {
                    MainEntity mainEntity = (MainEntity)mainEntityOptional.get();
                    dto.setBrand(mainEntity.getBrend());
                    dto.setProductName(mainEntity.getNameprod());
                    SimpleDateFormat dateFormat;
                    String strDate;
                    if (mainEntity.getDatemade() != null) {
                        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                        strDate = dateFormat.format(mainEntity.getDatemade());
                        dto.setDateOfManufacture(strDate);
                    }

                    dto.setBatchNumber(String.valueOf(mainEntity.getNumbpart()));
                    if (mainEntity.getExpdate() != null) {
                        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                        strDate = dateFormat.format(mainEntity.getExpdate());
                        dto.setBestBeforeDate(strDate);
                    }

                    dto.setSectionManager(mainEntity.getLabfio());
                    if (mainEntity.getDateprot() != null) {
                        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                        strDate = dateFormat.format(mainEntity.getDateprot());
                        dto.setDateToday(strDate);
                    }
                }

                dto.setNumber(String.valueOf(unloadEntity.getMass()));
                dto.setPath(unloadEntity.getNumb());
                dto.setIdProd(id);
                result = this.responsibleSemiFinishedProductPdfService.responsibleSemiFinishedProduct(dto);
            } while(result);

            throw new NullPointerException("Error responsibleSemiFinishedProduct");
        } catch (Exception var16) {
            log.error("Error printDischargePage : {}, {}", ExceptionUtils.getMessage(var16), ExceptionUtils.getMessage(var16.getCause()));
            return false;
        }
    }
}
