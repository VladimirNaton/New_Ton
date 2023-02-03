package com.new_ton.service;

import com.new_ton.dao.SearchDataForTablesDao;
import com.new_ton.dao.UpdateDataDao;
import com.new_ton.domain.dto.SendProductToTellerDto;
import com.new_ton.domain.entities.CatalogEntity;
import com.new_ton.domain.entities.CatrecEntity;
import com.new_ton.domain.entities.MainEntity;
import com.new_ton.domain.entities.RawEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UpdateDataServiceImpl implements UpdateDataService {

    private final SearchDataForTablesDao searchDataForTablesDao;

    private final UpdateDataDao updateDataDao;

    @Transactional
    @Override
    public boolean moveCatalogRowToMain(Integer id) {
        try {
            Optional<CatalogEntity> catalogEntityOptional = searchDataForTablesDao.getCatalogEntityById(id);
            if (catalogEntityOptional.isPresent()) {
                Calendar calendar = new GregorianCalendar(1111, 10, 11, 11, 11, 11);
                Date date = calendar.getTime();
                CatalogEntity catalogEntity = catalogEntityOptional.get();
                MainEntity mainEntity = new MainEntity();
                mainEntity.setIdCat(catalogEntity.getIdpr());
                mainEntity.setDatecr(catalogEntity.getDatecr());
                mainEntity.setBrend(catalogEntity.getBrend());
                mainEntity.setNameprod(catalogEntity.getNameprod());
                mainEntity.setPercent(catalogEntity.getPercent());
                mainEntity.setMass(catalogEntity.getMass());
                mainEntity.setTempprodmin(catalogEntity.getTempprodmin());
                mainEntity.setTempprodmax(catalogEntity.getTempprodmax());
                mainEntity.setState(1);
                mainEntity.setSp("Основная спецификаця");
                mainEntity.setDatestart(date);
                mainEntity.setTimemade(0);
                mainEntity.setOperfio("фио");
                mainEntity.setDeg(0);
                mainEntity.setLabfio("фио");
                mainEntity.setNumbprot(0);
                mainEntity.setNumbpart(0);
                mainEntity.setExpdate(date);
                mainEntity.setDateprot(date);
                mainEntity.setFiltr("0");
                mainEntity.setDatepl(date);
                mainEntity.setDatemade(date);
                mainEntity.setComment("норм");
                mainEntity.setReturndate(date);
                Integer idMain = updateDataDao.saveNewMainRow(mainEntity);
                if (idMain != 0) {
                    List<CatrecEntity> catrecEntityList = searchDataForTablesDao.getCatrecByCatId(catalogEntity.getIdpr());
                    List<RawEntity> rawEntityList = new ArrayList<>();
                    for (CatrecEntity entity : catrecEntityList) {
                        RawEntity rawEntity = new RawEntity();
                        rawEntity.setIdMain(idMain);
                        rawEntity.setN(entity.getN());
                        rawEntity.setStage(entity.getStage());
                        rawEntity.setCode(entity.getCode());
                        rawEntity.setNameraw(entity.getNameraw());
                        rawEntity.setPercent(entity.getPercent());
                        rawEntity.setMass(entity.getMass());
                        rawEntity.setDevper(entity.getDevper());
                        rawEntity.setDevmass(entity.getDevmass());
                        rawEntity.setTimemix(entity.getTurnmix());
                        rawEntity.setTimemix(entity.getTimemix());
                        rawEntity.setFactmass(0D);
                        rawEntity.setFactmassdev(0D);
                        rawEntity.setTempdep(0D);
                        rawEntity.setWetdep(0D);
                        rawEntity.setProdtemp(0D);
                        rawEntity.setDatestart(date);
                        rawEntity.setDatestop(date);
                        rawEntity.setTimemade(0);
                        rawEntity.setTurnmix(0);
                        rawEntity.setDevturn(0);
                        rawEntity.setFacttimemix(0);
                        rawEntity.setEq(0);
                        rawEntity.setPastpart(0);
                        rawEntity.setPastdate(date);
                        rawEntityList.add(rawEntity);
                    }
                    return updateDataDao.saveRawNewRows(rawEntityList);
                }
            }

        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl moveCatalogRowToMain : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean sentProductToTeller(SendProductToTellerDto sendProductToTellerDto) {
        try {
            Optional<MainEntity> mainEntityOptional = searchDataForTablesDao.getMainEntityById(sendProductToTellerDto.getId());
            if (mainEntityOptional.isPresent()) {
                MainEntity mainEntity = mainEntityOptional.get();
                mainEntity.setState(2);
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, sendProductToTellerDto.getDays());
                Date returnDate = calendar.getTime();
                mainEntity.setReturndate(returnDate);
                return updateDataDao.sendProductToTeller(mainEntity);
            }
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl sentProductToTeller : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Transactional
    @Override
    public boolean deleteSelectedRowFromRecipeTable(Integer id) {
        try {
            RawEntity rawEntity = updateDataDao.deleteSelectedRowFromRecipeTable(id);
            List<RawEntity> rawEntityList = searchDataForTablesDao.getAllByIdAndSequenceNumber(rawEntity.getIdMain(), rawEntity.getN());

            rawEntityList.stream().peek(elem -> {
                Integer sequenceNumber = elem.getN();
                elem.setN(--sequenceNumber);
            }).collect(Collectors.toList());
            return updateDataDao.updateRawEntity(rawEntityList);
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl deleteSelectedRowFromRecipeTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }
}
