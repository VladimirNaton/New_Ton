package com.new_ton.service;



import com.new_ton.dao.LabProtDao;
import com.new_ton.dao.MainTableDao;
import com.new_ton.dao.UploadTableDao;
import com.new_ton.domain.dto.productionpage.CreateResponsibleSemiFinishedProductDto;
import com.new_ton.domain.entities.LabprotEntity;
import com.new_ton.domain.entities.MainEntity;
import com.new_ton.domain.entities.UnloadEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Log4j2
@Service
public class PrintDischargePageServiceImpl implements PrintDischargePageService {

    private final UploadTableDao uploadTableDao;
    private final MainTableDao mainTableDao;
    private final LabProtDao labProtDao;
    private final ResponsibleSemiFinishedProductPdfService responsibleSemiFinishedProductPdfService;


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
                result = responsibleSemiFinishedProductPdfService.responsibleSemiFinishedProduct(dto);
            } while(result);

            throw new NullPointerException("Error responsibleSemiFinishedProduct");
        } catch (Exception e) {
            log.error("Error printDischargePage : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
            return false;
        }
    }
}
