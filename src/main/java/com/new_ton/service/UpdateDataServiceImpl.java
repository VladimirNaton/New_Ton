package com.new_ton.service;

import com.new_ton.dao.SearchDataForTablesDao;
import com.new_ton.dao.UpdateDataDao;
import com.new_ton.domain.dto.accountmanager.ReturnRecipeToTechnologistRequestDto;
import com.new_ton.domain.dto.technologistdto.AddOrReplaceComponentToRecipeRequestDto;
import com.new_ton.domain.dto.technologistdto.SaveRecipeDto;
import com.new_ton.domain.dto.technologistdto.SendProductToAccountManagerDto;
import com.new_ton.domain.dto.technologistdto.UpdateSelectedRowOfRecipeDto;
import com.new_ton.domain.entities.*;
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
    public boolean sentProductToTeller(SendProductToAccountManagerDto sendProductToAccountManagerDto) {
        try {
            Optional<MainEntity> mainEntityOptional = searchDataForTablesDao.getMainEntityById(sendProductToAccountManagerDto.getId());
            if (mainEntityOptional.isPresent()) {
                MainEntity mainEntity = mainEntityOptional.get();
                mainEntity.setState(2);
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, sendProductToAccountManagerDto.getDays());
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
            return updateDataDao.updateRawEntityList(rawEntityList);
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl deleteSelectedRowFromRecipeTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Transactional
    @Override
    public boolean addComponentToRecipe(AddOrReplaceComponentToRecipeRequestDto addOrReplaceComponentToRecipeRequestDto) {
        try {
            Calendar calendar = new GregorianCalendar(1111, 10, 11, 11, 11, 11);
            Date date = calendar.getTime();
            Integer maxSequenceNumber = null;
            maxSequenceNumber = searchDataForTablesDao.selectMaxSequenceNumber(addOrReplaceComponentToRecipeRequestDto.getIdMain());
            RawEntity rawEntity = new RawEntity();
            if (maxSequenceNumber != null) {
                rawEntity.setN(++maxSequenceNumber);
            } else {
                rawEntity.setN(1);
            }

            rawEntity = addSomeParameter(addOrReplaceComponentToRecipeRequestDto.getCode(), addOrReplaceComponentToRecipeRequestDto.getIdComponentTable(), rawEntity, addOrReplaceComponentToRecipeRequestDto.getNameSelectedComponent(), date);

            rawEntity.setIdMain(addOrReplaceComponentToRecipeRequestDto.getIdMain());
            rawEntity.setStage(0);
            rawEntity.setPercent(0.0);
            rawEntity.setMass(0.0);
            rawEntity.setDevper(0.0);
            rawEntity.setDevmass(0.0);
            rawEntity.setFactmass(0.0);
            rawEntity.setFactmassdev(0.0);
            rawEntity.setTempdep(0.0);
            rawEntity.setWetdep(0.0);
            rawEntity.setProdtemp(0.0);
            rawEntity.setDatestart(date);
            rawEntity.setDatestop(date);
            rawEntity.setTimemade(0);
            rawEntity.setTurnmix(0);
            rawEntity.setDevturn(0);
            rawEntity.setTimemix(0);
            rawEntity.setFactturn(0);
            rawEntity.setFacttimemix(0);
            rawEntity.setEq(0);
            rawEntity.setFilter(0);
            rawEntity.setComponentLoaded(0);
            return updateDataDao.saveNewRowToRawTable(rawEntity);
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl addComponentToRecipe : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean replaceSelectedRecipeElement(AddOrReplaceComponentToRecipeRequestDto addOrReplaceComponentToRecipeRequestDto) {
        try {
            Calendar calendar = new GregorianCalendar(1111, 10, 11, 11, 11, 11);
            Date date = calendar.getTime();
            Optional<RawEntity> rawEntityOptional = searchDataForTablesDao.getRawEntityById(addOrReplaceComponentToRecipeRequestDto.getIdSelectedElemRecipeTable());
            if (rawEntityOptional.isPresent()) {
                RawEntity rawEntity = rawEntityOptional.get();

                rawEntity = addSomeParameter(addOrReplaceComponentToRecipeRequestDto.getCode(), addOrReplaceComponentToRecipeRequestDto.getIdComponentTable(), rawEntity, addOrReplaceComponentToRecipeRequestDto.getNameSelectedComponent(), date);

                rawEntity.setIdMain(addOrReplaceComponentToRecipeRequestDto.getIdMain());
                rawEntity.setStage(0);
                rawEntity.setPercent(0.0);
                rawEntity.setMass(0.0);
                rawEntity.setDevper(0.0);
                rawEntity.setDevmass(0.0);
                rawEntity.setFactmass(0.0);
                rawEntity.setFactmassdev(0.0);
                rawEntity.setTempdep(0.0);
                rawEntity.setWetdep(0.0);
                rawEntity.setProdtemp(0.0);
                rawEntity.setDatestart(date);
                rawEntity.setDatestop(date);
                rawEntity.setTimemade(0);
                rawEntity.setTurnmix(0);
                rawEntity.setDevturn(0);
                rawEntity.setTimemix(0);
                rawEntity.setFactturn(0);
                rawEntity.setFacttimemix(0);
                rawEntity.setEq(0);
                rawEntity.setFilter(0);
                rawEntity.setComponentLoaded(0);
                return updateDataDao.updateRawEntity(rawEntity);
            }
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl replaceSelectedRecipeElement : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    private RawEntity addSomeParameter(Integer code, Integer idComponentTable, RawEntity rawEntity, String nameSelectedComponent, Date date) {
        if (code == null || code < 3) {
            Optional<CatrawEntity> catrawEntityOptional = searchDataForTablesDao.getCatrawEntityById(idComponentTable);
            if (catrawEntityOptional.isPresent()) {
                CatrawEntity catrawEntity = catrawEntityOptional.get();
                rawEntity.setNameraw(catrawEntity.getNameraw());
                rawEntity.setCode(catrawEntity.getCode());
                rawEntity.setPastpart(catrawEntity.getPart());
                rawEntity.setPastdate(catrawEntity.getDate());
            }
        } else {
            rawEntity.setNameraw(nameSelectedComponent);
            rawEntity.setCode(code);
            rawEntity.setPastpart(0);
            rawEntity.setPastdate(date);
        }
        return rawEntity;
    }

    @Transactional
    @Override
    public boolean updateSelectedRowOfRecipe(UpdateSelectedRowOfRecipeDto updateSelectedRowOfRecipeDto) {
        try {
            searchDataForTablesDao.getRawEntityById(updateSelectedRowOfRecipeDto.getSelectedComponentId()).ifPresent(elem -> {
                if (updateSelectedRowOfRecipeDto.getSequenceNumber() != null) {
                    elem.setN(updateSelectedRowOfRecipeDto.getSequenceNumber());
                }

                if (updateSelectedRowOfRecipeDto.getStage() != null) {
                    elem.setStage(updateSelectedRowOfRecipeDto.getStage());
                }

                if (updateSelectedRowOfRecipeDto.getPercent() != null) {
                    elem.setPercent(updateSelectedRowOfRecipeDto.getPercent());
                }

                if (updateSelectedRowOfRecipeDto.getMass() != null) {
                    elem.setMass(updateSelectedRowOfRecipeDto.getMass());
                }

                if (updateSelectedRowOfRecipeDto.getInfelicityPercent() != null) {
                    elem.setDevper(updateSelectedRowOfRecipeDto.getInfelicityPercent());
                }

                if (updateSelectedRowOfRecipeDto.getInfelicityMass() != null) {
                    elem.setDevmass(updateSelectedRowOfRecipeDto.getInfelicityMass());
                }

                if (updateSelectedRowOfRecipeDto.getMixing() != null) {
                    elem.setTurnmix(updateSelectedRowOfRecipeDto.getMixing());
                }

                if (updateSelectedRowOfRecipeDto.getMixingTime() != null) {
                    elem.setTimemix(updateSelectedRowOfRecipeDto.getMixingTime());
                }

                if (updateSelectedRowOfRecipeDto.getPart() != null) {
                    elem.setPastpart(updateSelectedRowOfRecipeDto.getPart());
                }

                if (updateSelectedRowOfRecipeDto.getPastDate() != null) {
                    elem.setPastdate(updateSelectedRowOfRecipeDto.getPastDate());
                }

                if (updateSelectedRowOfRecipeDto.getFilter() != null) {
                    elem.setFilter(updateSelectedRowOfRecipeDto.getFilter());
                }
                updateDataDao.updateRawEntity(elem);
            });
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl updateSelectedRowOfRecipe : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Transactional
    @Override
    public boolean saveRecipe(SaveRecipeDto saveRecipeDto) {
        try {
            searchDataForTablesDao.getMainEntityById(saveRecipeDto.getIdMain())
                    .ifPresent(elem -> {
                        if (saveRecipeDto.getComment().equals("")) {
                            elem.setComment("норм");
                        } else {
                            elem.setComment(saveRecipeDto.getComment());
                        }
                        elem.setTempprodmin(saveRecipeDto.getTempMin());
                        elem.setTempprodmax(saveRecipeDto.getTempMax());
                        elem.setMass(saveRecipeDto.getCommonWeight());
                        elem.setPercent(saveRecipeDto.getControl());
                        elem.setDatecr(new Date());
                        updateDataDao.updateMainEntity(elem);
                    });
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl saveRecipe : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateDataByCatalogFromMain(Integer idMain) {
        try {
            searchDataForTablesDao.getMainEntityById(idMain).ifPresent(elem -> {
                List<RawEntity> rawEntityList = searchDataForTablesDao.getRawEntityByIdMain(idMain);
                updateDataDao.deleteCatrecEntityByIdCat(elem.getIdCat());
                List<CatrecEntity> catrecEntityList = new ArrayList<>();
                for (RawEntity raw : rawEntityList) {
                    CatrecEntity catrecEntity = new CatrecEntity();
                    catrecEntity.setIdCat(elem.getIdCat());
                    catrecEntity.setN(raw.getN());
                    catrecEntity.setStage(raw.getStage());
                    catrecEntity.setCode(raw.getCode());
                    catrecEntity.setNameraw(raw.getNameraw());
                    catrecEntity.setPercent(raw.getPercent());
                    catrecEntity.setMass(raw.getMass());
                    catrecEntity.setDevper(raw.getDevper());
                    catrecEntity.setDevmass(raw.getDevmass());
                    catrecEntity.setTurnmix(raw.getTurnmix());
                    catrecEntity.setTimemix(raw.getTimemix());
                    catrecEntityList.add(catrecEntity);
                }
                updateDataDao.saveCatrecEntity(catrecEntityList);

                searchDataForTablesDao.getCatalogEntityById(elem.getIdCat())
                        .ifPresent(catalog -> {
                            catalog.setTempprodmax(elem.getTempprodmax());
                            catalog.setTempprodmin(elem.getTempprodmin());
                            catalog.setMass(elem.getMass());
                            catalog.setPercent(elem.getPercent());
                            catalog.setDatecr(new Date());
                            updateDataDao.updateCatalogEntity(catalog);
                        });
            });

            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl updateDataByCatalogFromMain : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Transactional
    @Override
    public boolean returnRecipeToTechnologist(ReturnRecipeToTechnologistRequestDto returnRecipeToTechnologistRequestDto) {
        try {
            searchDataForTablesDao.getMainEntityById(returnRecipeToTechnologistRequestDto.getIdMain())
                    .ifPresent(elem -> {
                        elem.setComment(returnRecipeToTechnologistRequestDto.getComment());
                        elem.setState(1);
                        updateDataDao.updateMainEntity(elem);
                    });
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl returnRecipeToTechnologist : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Transactional
    @Override
    public boolean sendToProduction(ReturnRecipeToTechnologistRequestDto returnRecipeToTechnologistRequestDto) {
        try {
            searchDataForTablesDao.getMainEntityById(returnRecipeToTechnologistRequestDto.getIdMain())
                    .ifPresent(elem -> {
                        elem.setComment(returnRecipeToTechnologistRequestDto.getComment());
                        elem.setState(3);
                        updateDataDao.updateMainEntity(elem);
                    });
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl returnRecipeToTechnologist : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }
}
