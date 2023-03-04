package com.new_ton.service;

import com.new_ton.dao.SearchDataForTablesDao;
import com.new_ton.dao.UpdateDataDao;
import com.new_ton.domain.dto.accountmanager.AddOrReplaceComponentToCatalogRecipeDto;
import com.new_ton.domain.dto.accountmanager.ComponentTableDto;
import com.new_ton.domain.dto.accountmanager.ReturnRecipeToTechnologistRequestDto;
import com.new_ton.domain.dto.accountmanager.SaveCatalogRecipeDto;
import com.new_ton.domain.dto.technologistdto.*;
import com.new_ton.domain.dto.testerdto.CommentDto;
import com.new_ton.domain.dto.testerdto.SaveProtocolDto;
import com.new_ton.domain.dto.testerdto.TimeDto;
import com.new_ton.domain.entities.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UpdateDataServiceImpl implements UpdateDataService {

    private final SearchDataForTablesDao searchDataForTablesDao;
    private final UpdateDataDao updateDataDao;
    private final HttpServletRequest request;

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

            rawEntity = addSomeParameter(addOrReplaceComponentToRecipeRequestDto.getCode(), addOrReplaceComponentToRecipeRequestDto.getIdComponentTable(), rawEntity, addOrReplaceComponentToRecipeRequestDto.getNameSelectedComponent(), date, addOrReplaceComponentToRecipeRequestDto.isOutPast());

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
            rawEntity.setFilter("");
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

                rawEntity = addSomeParameter(addOrReplaceComponentToRecipeRequestDto.getCode(), addOrReplaceComponentToRecipeRequestDto.getIdComponentTable(), rawEntity, addOrReplaceComponentToRecipeRequestDto.getNameSelectedComponent(), date, addOrReplaceComponentToRecipeRequestDto.isOutPast());

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
                rawEntity.setFilter("");
                rawEntity.setComponentLoaded(0);
                return updateDataDao.updateRawEntity(rawEntity);
            }
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl replaceSelectedRecipeElement : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    private RawEntity addSomeParameter(Integer code, Integer idComponentTable, RawEntity rawEntity, String nameSelectedComponent, Date date, boolean outerPast) {
        if (code == null || code < 3 || code == 9) {
            if (!outerPast) {
                Optional<CatrawEntity> catrawEntityOptional = searchDataForTablesDao.getCatrawEntityById(idComponentTable);
                if (catrawEntityOptional.isPresent()) {
                    CatrawEntity catrawEntity = catrawEntityOptional.get();
                    rawEntity.setNameraw(catrawEntity.getNameraw());
                    rawEntity.setCode(catrawEntity.getCode());
                    rawEntity.setPastpart(catrawEntity.getPart());
                    rawEntity.setPastdate(catrawEntity.getDate());
                }
            } else {
                searchDataForTablesDao.getCatpastEntityById(idComponentTable)
                        .ifPresent(elem -> {
                            rawEntity.setNameraw(elem.getNamepast());
                            rawEntity.setCode(2);
                            rawEntity.setPastpart(elem.getPart());
                            rawEntity.setPastdate(elem.getDate());
                        });
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

    @Transactional
    @Override
    public boolean updateSelectedCatalogRow(CatalogDtoSelectedRow catalogDtoSelectedRow) {
        try {
            searchDataForTablesDao.getCatalogEntityById(catalogDtoSelectedRow.getId())
                    .ifPresent(elem -> {
                        elem.setDatecr(catalogDtoSelectedRow.getDatecr());
                        elem.setBrend(catalogDtoSelectedRow.getBrend());
                        elem.setNameprod(catalogDtoSelectedRow.getNameprod());
                        elem.setPercent(catalogDtoSelectedRow.getPercent());
                        elem.setMass(catalogDtoSelectedRow.getMass());
                        elem.setTempprodmin(catalogDtoSelectedRow.getTempprodmin());
                        elem.setTempprodmax(catalogDtoSelectedRow.getTempprodmax());
                        updateDataDao.updateCatalogEntity(elem);
                    });
            return true;

        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl updateSelectedCatalogRow : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e));
        }
        return false;
    }

    @Transactional
    @Override
    public boolean deleteSelectedCatalogRow(Integer id) {
        try {
            updateDataDao.deleteSelectedCatalogRow(id);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl deleteSelectedCatalogRow : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Transactional
    @Override
    public boolean addNewRecipe(CatalogDtoSelectedRow catalogDtoSelectedRow) {
        try {
            CatalogEntity catalogEntity = new CatalogEntity();
            if (catalogDtoSelectedRow.getDataCreate() != null) {
                catalogEntity.setDatecr(catalogDtoSelectedRow.getDatecr());
            } else {
                catalogEntity.setDatecr(new Date());
            }
            catalogEntity.setBrend(catalogDtoSelectedRow.getBrend());
            catalogEntity.setNameprod(catalogDtoSelectedRow.getNameprod());
            if (catalogDtoSelectedRow.getPercent() != null) {
                catalogEntity.setPercent(catalogDtoSelectedRow.getPercent());
            } else {
                catalogEntity.setPercent(0D);
            }

            if (catalogDtoSelectedRow.getMass() != null) {
                catalogEntity.setMass(catalogDtoSelectedRow.getMass());
            } else {
                catalogEntity.setMass(0D);
            }

            if (catalogDtoSelectedRow.getTempprodmin() != null) {
                catalogEntity.setTempprodmin(catalogDtoSelectedRow.getTempprodmin());
            } else {
                catalogEntity.setTempprodmin(0D);
            }

            if (catalogDtoSelectedRow.getTempprodmax() != null) {
                catalogEntity.setTempprodmax(catalogDtoSelectedRow.getTempprodmax());
            } else {
                catalogEntity.setTempprodmax(0D);
            }
            updateDataDao.saveNewCatalogRow(catalogEntity);
            return true;

        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl addNewRecipe : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }


    @Transactional
    @Override
    public boolean addComponentToCatalogRecipe(AddOrReplaceComponentToCatalogRecipeDto addOrReplaceComponentToCatalogRecipeDto) {
        try {
            Calendar calendar = new GregorianCalendar(1111, 10, 11, 11, 11, 11);
            Date date = calendar.getTime();
            Integer maxSequenceNumber = null;
            maxSequenceNumber = searchDataForTablesDao.selectMaxSequenceNumberForCatalog(addOrReplaceComponentToCatalogRecipeDto.getIdCat());
            CatrecEntity recEntity = new CatrecEntity();
            if (maxSequenceNumber != null) {
                recEntity.setN(++maxSequenceNumber);
            } else {
                recEntity.setN(1);
            }

            recEntity = addSomeCatalogParameter(addOrReplaceComponentToCatalogRecipeDto.getCode(), addOrReplaceComponentToCatalogRecipeDto.getIdComponentTable(), recEntity, addOrReplaceComponentToCatalogRecipeDto.getNameSelectedComponent(), date, addOrReplaceComponentToCatalogRecipeDto.isOutPast());

            recEntity.setIdCat(addOrReplaceComponentToCatalogRecipeDto.getIdCat());
            recEntity.setStage(0);
            recEntity.setPercent(0.0);
            recEntity.setMass(0.0);
            recEntity.setDevper(0.0);
            recEntity.setDevmass(0.0);
            recEntity.setTurnmix(0);
            recEntity.setTimemix(0);
            recEntity.setFilter("");
            return updateDataDao.saveNewRowToCatrecTable(recEntity);
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl addComponentToCatalogRecipe : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    private CatrecEntity addSomeCatalogParameter(Integer code, Integer idComponentTable, CatrecEntity recEntity, String nameSelectedComponent, Date date, boolean outerPast) {
        if (code == null || code < 3 || code == 9) {
            if (!outerPast) {
                Optional<CatrawEntity> catrawEntityOptional = searchDataForTablesDao.getCatrawEntityById(idComponentTable);
                if (catrawEntityOptional.isPresent()) {
                    CatrawEntity catrawEntity = catrawEntityOptional.get();
                    recEntity.setNameraw(catrawEntity.getNameraw());
                    recEntity.setCode(catrawEntity.getCode());
                    recEntity.setPastpart(catrawEntity.getPart());
                    recEntity.setPastdate(catrawEntity.getDate());
                }
            } else {
                searchDataForTablesDao.getCatpastEntityById(idComponentTable)
                        .ifPresent(elem -> {
                            recEntity.setNameraw(elem.getNamepast());
                            recEntity.setCode(2);
                            recEntity.setPastpart(elem.getPart());
                            recEntity.setPastdate(elem.getDate());
                        });
            }

        } else {
            recEntity.setNameraw(nameSelectedComponent);
            recEntity.setCode(code);
            recEntity.setPastpart(0);
            recEntity.setPastdate(date);
        }
        return recEntity;
    }

    @Transactional
    @Override
    public boolean deleteSelectedRowFromRecipeCatalogTable(Integer id) {
        try {
            CatrecEntity catrecEntity = updateDataDao.deleteSelectedRowFromCatalogRecipeTable(id);
            List<CatrecEntity> recEntityList = searchDataForTablesDao.getAllByIdAndSequenceNumberCatalog(catrecEntity.getIdCat(), catrecEntity.getN());
            recEntityList.stream().peek(elem -> {
                Integer sequenceNumber = elem.getN();
                elem.setN(--sequenceNumber);
            }).collect(Collectors.toList());
            return updateDataDao.updateCatrecEntityList(recEntityList);
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl deleteSelectedRowFromRecipeCatalogTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean replaceSelectedCatalogRecipeElement(AddOrReplaceComponentToCatalogRecipeDto addOrReplaceComponentToCatalogRecipeDto) {
        try {
            Calendar calendar = new GregorianCalendar(1111, 10, 11, 11, 11, 11);
            Date date = calendar.getTime();
            Optional<CatrecEntity> recEntityOptional = searchDataForTablesDao.getCatrecEntityById(addOrReplaceComponentToCatalogRecipeDto.getIdSelectedElemRecipeTable());
            if (recEntityOptional.isPresent()) {
                CatrecEntity catrecEntity = recEntityOptional.get();

                catrecEntity = addSomeCatalogParameter(addOrReplaceComponentToCatalogRecipeDto.getCode(), addOrReplaceComponentToCatalogRecipeDto.getIdComponentTable(), catrecEntity, addOrReplaceComponentToCatalogRecipeDto.getNameSelectedComponent(), date, addOrReplaceComponentToCatalogRecipeDto.isOutPast());

                catrecEntity.setIdCat(addOrReplaceComponentToCatalogRecipeDto.getIdCat());
                catrecEntity.setStage(0);
                catrecEntity.setPercent(0.0);
                catrecEntity.setMass(0.0);
                catrecEntity.setDevper(0.0);
                catrecEntity.setDevmass(0.0);
                catrecEntity.setTurnmix(0);
                catrecEntity.setTimemix(0);
                catrecEntity.setFilter("");
                return updateDataDao.updateCatrecEntity(catrecEntity);
            }
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl replaceSelectedCatalogRecipeElement : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateSelectedCatalogRowOfRecipe(UpdateSelectedRowOfRecipeDto updateSelectedRowOfRecipeDto) {
        try {
            searchDataForTablesDao.getCatrecEntityById(updateSelectedRowOfRecipeDto.getSelectedComponentId()).ifPresent(elem -> {
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
                updateDataDao.updateCatrecEntity(elem);
            });
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl updateSelectedCatalogRowOfRecipe : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Transactional
    @Override
    public boolean saveCatalogRecipe(SaveCatalogRecipeDto catalogRecipeDto) {
        try {
            searchDataForTablesDao.getCatalogEntityById(catalogRecipeDto.getIdCat())
                    .ifPresent(elem -> {
                        elem.setTempprodmin(catalogRecipeDto.getTempMin());
                        elem.setTempprodmax(catalogRecipeDto.getTempMax());
                        elem.setMass(catalogRecipeDto.getCommonWeight());
                        elem.setPercent(catalogRecipeDto.getControl());
                        elem.setDatecr(new Date());
                        updateDataDao.updateCatalogEntity(elem);
                    });
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl saveCatalogRecipe : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean deleteSelectedDissolverRow(Integer id) {
        try {
            return updateDataDao.deleteSelectedDissolverRow(id);
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl deleteSelectedDissolverRow : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean createNewCateqRow(CreateCateqDto cateqDto) {
        try {
            CateqEntity cateqEntity = new CateqEntity();
            cateqEntity.setEq(cateqDto.getEq());
            cateqEntity.setCode(cateqDto.getCode());
            return updateDataDao.createNewCateqRow(cateqEntity);
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl createNewCateqRow ; {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e));
        }
        return false;
    }

    @Override
    public boolean updateCateqRow(CreateCateqDto cateqDto) {
        try {
            CateqEntity cateqEntity = new CateqEntity();
            cateqEntity.setEq(cateqDto.getEq());
            cateqEntity.setCode(cateqDto.getCode());
            cateqEntity.setId(cateqDto.getId());
            return updateDataDao.updateCateqRow(cateqEntity);
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl updateCateqRow : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean addComponent(ComponentTableDto componentTableDto) {
        try {
            CatrawEntity catrawEntity = new CatrawEntity();
            if (componentTableDto.getCode1c() != null) {
                catrawEntity.setCode1C(componentTableDto.getCode1c());
            } else {
                catrawEntity.setCode1C(0);
            }
            catrawEntity.setNameraw(componentTableDto.getNameraw());
            catrawEntity.setCode(componentTableDto.getCode());
            if (componentTableDto.getPart() != null) {
                catrawEntity.setPart(componentTableDto.getPart());
            } else {
                catrawEntity.setPart(0);
            }


            if (componentTableDto.getDate() != null) {
                catrawEntity.setDate(componentTableDto.getDate());
            } else {
                catrawEntity.setDate(new Date());
            }
            return updateDataDao.addComponent(catrawEntity);
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl addComponent : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean updateComponent(ComponentTableDto componentTableDto) {
        try {
            CatrawEntity catrawEntity = new CatrawEntity();

            catrawEntity.setId(componentTableDto.getId());

            if (componentTableDto.getCode1c() != null) {
                catrawEntity.setCode1C(componentTableDto.getCode1c());
            } else {
                catrawEntity.setCode1C(0);
            }

            catrawEntity.setNameraw(componentTableDto.getNameraw());
            catrawEntity.setCode(componentTableDto.getCode());

            if (componentTableDto.getPart() != null) {
                catrawEntity.setPart(componentTableDto.getPart());
            } else {
                catrawEntity.setPart(0);
            }

            if (componentTableDto.getDate() != null) {
                catrawEntity.setDate(componentTableDto.getDate());
            } else {
                catrawEntity.setDate(new Date());
            }
            return updateDataDao.updateComponent(catrawEntity);
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl updateComponent : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean deleteComponent(Integer id) {
        try {
            return updateDataDao.deleteComponent(id);
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl deleteComponent : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean returnToWork(Integer id) {
        try {
            return updateDataDao.returnToWork(id);
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl returnToWork : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean sendToReject(Integer id) {
        try {
            return updateDataDao.sendToReject(id);
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl  sendToReject : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean sendPutAside(Integer id) {
        try {
            return updateDataDao.sendPutAside(id);
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl  sendPutAside : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean sendComment(CommentDto commentDto) {
        try {
            return updateDataDao.sendComment(commentDto);
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl  sendComment : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public Integer saveCommentToStage(CommentToStageDto commentToStageDto) {
        try {
            CommentToStageEntity commentToStageEntity = new CommentToStageEntity();

            if (commentToStageDto.getId() != null) {
                commentToStageEntity.setId(commentToStageDto.getId());
            }

            commentToStageEntity.setIdMain(commentToStageDto.getIdMain());
            commentToStageEntity.setIdStage(commentToStageDto.getIdStage());
            commentToStageEntity.setComment(commentToStageDto.getComment());
            return updateDataDao.saveCommentToStage(commentToStageEntity);
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl  saveCommentToStage : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public boolean saveTimeTemplate(TimeDto timeDto) {
        try {
            Optional<RawEntity> rawEntityOptional = searchDataForTablesDao.getRawEntityById(timeDto.getRawId());
            if (rawEntityOptional.isPresent()) {
                RawEntity rawEntity = rawEntityOptional.get();
                rawEntity.setFacttimemix(timeDto.getTime());
                updateDataDao.updateRawEntity(rawEntity);
            }
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl  saveTimeTemplate : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Transactional
    @Override
    public boolean saveProtocol(SaveProtocolDto saveProtocolDto) {
        try {
            SecurityContextImpl ct = (SecurityContextImpl) request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
            Optional<MainEntity> mainEntityOptional = searchDataForTablesDao.getMainEntityById(saveProtocolDto.getIdMain());
            if (mainEntityOptional.isPresent()) {
                MainEntity mainEntity = mainEntityOptional.get();
                if (!saveProtocolDto.getDateCreateProduct().equals("")) {
                    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(saveProtocolDto.getDateCreateProduct());
                    mainEntity.setDatemade(date);
                }
                String userFio = searchDataForTablesDao.getUserFio(ct.getAuthentication().getName());
                mainEntity.setLabfio(userFio);
                mainEntity.setNumbprot(saveProtocolDto.getNumberProtocol());
                mainEntity.setNumbpart(saveProtocolDto.getPartNumber());
                if (!saveProtocolDto.getBestBeforeDate().equals("")) {
                    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(saveProtocolDto.getBestBeforeDate());
                    mainEntity.setExpdate(date);
                }
                if (!saveProtocolDto.getDateProtocol().equals("")) {
                    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(saveProtocolDto.getDateProtocol());
                    mainEntity.setDateprot(date);
                }
                mainEntity.setFiltr(saveProtocolDto.getFilter());
                updateDataDao.updateMainEntity(mainEntity);

                if (saveProtocolDto.getTipProtocol() == 1) {
                    List<LabprotEntity> labprotEntityList = createLabprotEntityType1(saveProtocolDto);
                    labprotEntityList.stream()
                            .peek(elem -> {
                                elem.setIdpr(mainEntity.getIdpr());
                            }).collect(Collectors.toList());
                    updateDataDao.updateLabprotEntity(labprotEntityList, mainEntity.getIdpr());
                }

                if (saveProtocolDto.getTipProtocol() == 2) {
                    List<LabprotEntity> labprotEntityList = createLabprotEntityType2(saveProtocolDto);
                    labprotEntityList.stream()
                            .peek(elem -> {
                                elem.setIdpr(mainEntity.getIdpr());
                            }).collect(Collectors.toList());
                    updateDataDao.updateLabprotEntity(labprotEntityList, mainEntity.getIdpr());
                }

                if (saveProtocolDto.getTipProtocol() == 3) {
                    List<LabprotEntity> labprotEntityList = createLabprotEntityType3(saveProtocolDto);
                    labprotEntityList.stream()
                            .peek(elem -> {
                                elem.setIdpr(mainEntity.getIdpr());
                            }).collect(Collectors.toList());
                    updateDataDao.updateLabprotEntity(labprotEntityList, mainEntity.getIdpr());
                }
            }

            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl  saveProtocol : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }


    private List<LabprotEntity> createLabprotEntityType1(SaveProtocolDto saveProtocolDto) {
        List<LabprotEntity> labprotEntityList = new ArrayList<>();
        LabprotEntity labprotEntity1 = new LabprotEntity();
        labprotEntity1.setIndicator("Внешиний вид");
        labprotEntity1.setNd(saveProtocolDto.getRow2col2());
        labprotEntity1.setAllvalues(saveProtocolDto.getRow2col3());
        labprotEntity1.setDev(saveProtocolDto.getRow2col4());
        labprotEntity1.setResult(saveProtocolDto.getRow2col5());
        labprotEntityList.add(labprotEntity1);

        LabprotEntity labprotEntity5 = new LabprotEntity();
        labprotEntity5.setIndicator("Вязкость");
        labprotEntity5.setNd(saveProtocolDto.getRow5col2());
        labprotEntity5.setAllvalues(saveProtocolDto.getRow5col3());
        labprotEntity5.setDev(saveProtocolDto.getRow5col4());
        labprotEntity5.setResult(saveProtocolDto.getRow5col5());
        labprotEntityList.add(labprotEntity5);

        LabprotEntity labprotEntity6 = new LabprotEntity();
        labprotEntity6.setIndicator("Плотность");
        labprotEntity6.setNd(saveProtocolDto.getRow6col2());
        labprotEntity6.setAllvalues(saveProtocolDto.getRow6col3());
        labprotEntity6.setDev(saveProtocolDto.getRow6col4());
        labprotEntity6.setResult(saveProtocolDto.getRow6col5());
        labprotEntityList.add(labprotEntity6);

        LabprotEntity labprotEntity7 = new LabprotEntity();
        labprotEntity7.setIndicator("Разведение");
        labprotEntity7.setNd(saveProtocolDto.getRow7col2());
        labprotEntity7.setAllvalues(saveProtocolDto.getRow7col3());
        labprotEntity7.setDev(saveProtocolDto.getRow7col4());
        labprotEntity7.setResult(saveProtocolDto.getRow7col5_1() + "-" + saveProtocolDto.getRow7col5_2());
        labprotEntityList.add(labprotEntity7);


        LabprotEntity labprotEntity8 = new LabprotEntity();
        labprotEntity8.setIndicator("Кол-во газа");
        labprotEntity8.setNd(saveProtocolDto.getRow8col2());
        labprotEntity8.setAllvalues(saveProtocolDto.getRow8col3());
        labprotEntity8.setDev(saveProtocolDto.getRow8col4());
        labprotEntity8.setResult(saveProtocolDto.getRow8col5());
        labprotEntityList.add(labprotEntity8);


        LabprotEntity labprotEntity9 = new LabprotEntity();
        labprotEntity9.setIndicator("Тип клапана");
        labprotEntity9.setNd(saveProtocolDto.getRow9col2());
        labprotEntity9.setAllvalues(saveProtocolDto.getRow9col3());
        labprotEntity9.setDev(saveProtocolDto.getRow9col4());
        labprotEntity9.setResult(saveProtocolDto.getRow9col5());
        labprotEntityList.add(labprotEntity9);

        LabprotEntity labprotEntity10 = new LabprotEntity();
        labprotEntity10.setIndicator("Тип головки");
        labprotEntity10.setNd(saveProtocolDto.getRow10col2());
        labprotEntity10.setAllvalues(saveProtocolDto.getRow10col3());
        labprotEntity10.setDev(saveProtocolDto.getRow10col4());
        labprotEntity10.setResult(saveProtocolDto.getRow10col5());
        labprotEntityList.add(labprotEntity10);

        LabprotEntity labprotEntity11 = new LabprotEntity();
        labprotEntity11.setIndicator("Степень глянца");
        labprotEntity11.setNd(saveProtocolDto.getRow11col2());
        labprotEntity11.setAllvalues(saveProtocolDto.getRow11col3());
        labprotEntity11.setDev(saveProtocolDto.getRow11col4());
        labprotEntity11.setResult(saveProtocolDto.getRow11col5());
        labprotEntityList.add(labprotEntity11);

        LabprotEntity labprotEntity13 = new LabprotEntity();
        labprotEntity13.setIndicator("Цвет");
        labprotEntity13.setNd(saveProtocolDto.getRow13col2());
        labprotEntity13.setAllvalues(saveProtocolDto.getRow13col3());
        labprotEntity13.setDev(saveProtocolDto.getRow13col4());
        labprotEntity13.setResult(saveProtocolDto.getRow13col5());
        labprotEntityList.add(labprotEntity13);
        return labprotEntityList;
    }

    private List<LabprotEntity> createLabprotEntityType2(SaveProtocolDto saveProtocolDto) {
        List<LabprotEntity> labprotEntityList = new ArrayList<>();

        LabprotEntity labprotEntity2 = new LabprotEntity();
        labprotEntity2.setIndicator("Внешиний вид");
        labprotEntity2.setNd(saveProtocolDto.getRow2col2());
        labprotEntity2.setAllvalues(saveProtocolDto.getRow2col3());
        labprotEntity2.setDev(saveProtocolDto.getRow2col4());
        labprotEntity2.setResult(saveProtocolDto.getRow2col5());
        labprotEntityList.add(labprotEntity2);

        LabprotEntity labprotEntity3 = new LabprotEntity();
        labprotEntity3.setIndicator("Активация");
        labprotEntity3.setNd(saveProtocolDto.getRow3col2());
        labprotEntity3.setAllvalues(saveProtocolDto.getRow3col3());
        labprotEntity3.setDev(saveProtocolDto.getRow3col4());
        labprotEntity3.setResult(saveProtocolDto.getRow3col5());
        labprotEntityList.add(labprotEntity3);

        LabprotEntity labprotEntity4 = new LabprotEntity();
        labprotEntity4.setIndicator("Степень перетирания");
        labprotEntity4.setNd(saveProtocolDto.getRow4col2());
        labprotEntity4.setAllvalues(saveProtocolDto.getRow4col3());
        labprotEntity4.setDev(saveProtocolDto.getRow4col4());
        labprotEntity4.setResult(saveProtocolDto.getRow4col5());
        labprotEntityList.add(labprotEntity4);

        LabprotEntity labprotEntity5 = new LabprotEntity();
        labprotEntity5.setIndicator("Вязкость");
        labprotEntity5.setNd(saveProtocolDto.getRow5col2());
        labprotEntity5.setAllvalues(saveProtocolDto.getRow5col3());
        labprotEntity5.setDev(saveProtocolDto.getRow5col4());
        labprotEntity5.setResult(saveProtocolDto.getRow5col5());
        labprotEntityList.add(labprotEntity5);

        LabprotEntity labprotEntity13 = new LabprotEntity();
        labprotEntity13.setIndicator("Цвет");
        labprotEntity13.setNd(saveProtocolDto.getRow13col2());
        labprotEntity13.setAllvalues(saveProtocolDto.getRow13col3());
        labprotEntity13.setDev(saveProtocolDto.getRow13col4());
        labprotEntity13.setResult(saveProtocolDto.getRow13col5());
        labprotEntityList.add(labprotEntity13);
        return labprotEntityList;

    }

    private List<LabprotEntity> createLabprotEntityType3(SaveProtocolDto saveProtocolDto) {
        List<LabprotEntity> labprotEntityList = new ArrayList<>();

        LabprotEntity labprotEntity2 = new LabprotEntity();
        labprotEntity2.setIndicator("Внешиний вид");
        labprotEntity2.setNd(saveProtocolDto.getRow2col2());
        labprotEntity2.setAllvalues(saveProtocolDto.getRow2col3());
        labprotEntity2.setDev(saveProtocolDto.getRow2col4());
        labprotEntity2.setResult(saveProtocolDto.getRow2col5());
        labprotEntityList.add(labprotEntity2);

        LabprotEntity labprotEntity12 = new LabprotEntity();
        labprotEntity12.setIndicator("Растворимость смолы");
        labprotEntity12.setNd(saveProtocolDto.getRow12col2());
        labprotEntity12.setAllvalues(saveProtocolDto.getRow12col3());
        labprotEntity12.setDev(saveProtocolDto.getRow12col4());
        labprotEntity12.setResult(saveProtocolDto.getRow12col5());
        labprotEntityList.add(labprotEntity12);

        LabprotEntity labprotEntity13 = new LabprotEntity();
        labprotEntity13.setIndicator("Цвет");
        labprotEntity13.setNd(saveProtocolDto.getRow13col2());
        labprotEntity13.setAllvalues(saveProtocolDto.getRow13col3());
        labprotEntity13.setDev(saveProtocolDto.getRow13col4());
        labprotEntity13.setResult(saveProtocolDto.getRow13col5());
        labprotEntityList.add(labprotEntity13);
        return labprotEntityList;
    }

    @Override
    public boolean sendToTaskShift(Integer id) {
        try {
            Optional<MainEntity> mainEntityOptional = searchDataForTablesDao.getMainEntityById(id);
            if (mainEntityOptional.isPresent()) {
                MainEntity mainEntity = mainEntityOptional.get();
                mainEntity.setState(4);
                mainEntity.setDatepl(new Date());
                return updateDataDao.sendToTaskShift(mainEntity);
            }
            return false;
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl  sendToTaskShift : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean sendToProductInProduction(Integer id) {
        try {
            Optional<MainEntity> mainEntityOptional = searchDataForTablesDao.getMainEntityById(id);
            if (mainEntityOptional.isPresent()) {
                MainEntity mainEntity = mainEntityOptional.get();
                mainEntity.setState(3);
                Calendar calendar = new GregorianCalendar(1111, 10, 11, 11, 11, 11);
                Date date = calendar.getTime();
                mainEntity.setDatepl(date);
                return updateDataDao.sendToTaskShift(mainEntity);
            }
            return false;
        } catch (Exception e) {
            log.error("Error UpdateDataServiceImpl  sendToProductInProduction : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }
}
