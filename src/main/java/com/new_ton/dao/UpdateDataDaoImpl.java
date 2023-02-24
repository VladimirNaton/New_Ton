package com.new_ton.dao;

import com.new_ton.domain.dto.accountmanager.ComponentTableDto;
import com.new_ton.domain.entities.*;
import com.new_ton.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Repository
public class UpdateDataDaoImpl implements UpdateDataDao {

    private final RawRepository rawRepository;
    private final MainRepository mainRepository;
    private final CatrecRepository catrecRepository;
    public final CatalogRepository catalogRepository;
    private final CateqRepository cateqRepository;
    private final CatrawRepository catrawRepository;

    @Override
    public Integer saveNewMainRow(MainEntity mainEntity) {
        try {
            MainEntity save = mainRepository.save(mainEntity);
            return save.getIdpr();
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl moveCatalogRowToMain : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return 0;
    }

    @Override
    public boolean saveRawNewRows(List<RawEntity> rawEntityList) {
        try {
            rawRepository.saveAll(rawEntityList);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl saveRawNewRows : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean sendProductToTeller(MainEntity mainEntity) {
        try {
            mainRepository.save(mainEntity);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl sendProductToTeller : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public RawEntity deleteSelectedRowFromRecipeTable(Integer id) {
        try {
            Optional<RawEntity> rawEntityOptional = rawRepository.findById(id);
            if (rawEntityOptional.isPresent()) {
                RawEntity rawEntity = rawEntityOptional.get();
                rawRepository.delete(rawEntity);
                return rawEntity;
            }
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl deleteSelectedRowFromRecipeTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public boolean updateRawEntityList(List<RawEntity> rawEntityList) {
        try {
            rawRepository.saveAllAndFlush(rawEntityList);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl updateRawEntity : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean saveNewRowToRawTable(RawEntity rawEntity) {
        try {
            rawRepository.save(rawEntity);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl saveNewRowToRawTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean updateRawEntity(RawEntity rawEntity) {
        try {
            rawRepository.saveAndFlush(rawEntity);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl updateRawEntity : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean updateMainEntity(MainEntity mainEntity) {
        try {
            mainRepository.saveAndFlush(mainEntity);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl updateMainEntity : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean deleteCatrecEntityByIdCat(Integer idCat) {
        try {
            catrecRepository.deleteAllByIdCat(idCat);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl deleteCatrecEntityByIdCat : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean saveCatrecEntity(List<CatrecEntity> catrecEntityList) {
        try {
            catrecRepository.saveAllAndFlush(catrecEntityList);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl saveCatrecEntity : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean updateCatalogEntity(CatalogEntity catalogEntity) {
        try {
            catalogRepository.saveAndFlush(catalogEntity);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl updateCatalogEntity : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean deleteSelectedCatalogRow(Integer id) {
        try {
            catalogRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl deleteSelectedCatalogRow : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean saveNewCatalogRow(CatalogEntity catalogEntity) {
        try {
            catalogRepository.save(catalogEntity);
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl saveNewCatalogRow : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean saveNewRowToCatrecTable(CatrecEntity catrecEntity) {
        try {
            catrecRepository.save(catrecEntity);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl saveNewRowToRawTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public CatrecEntity deleteSelectedRowFromCatalogRecipeTable(Integer id) {
        try {
            Optional<CatrecEntity> catrecEntityOptional = catrecRepository.findById(id);
            if (catrecEntityOptional.isPresent()) {
                CatrecEntity catrecEntity = catrecEntityOptional.get();
                catrecRepository.delete(catrecEntity);
                return catrecEntity;
            }
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl deleteSelectedRowFromCatalogRecipeTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public boolean updateCatrecEntityList(List<CatrecEntity> catrecEntityList) {
        try {
            catrecRepository.saveAllAndFlush(catrecEntityList);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl updateCatrecEntityList : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean updateCatrecEntity(CatrecEntity catrecEntity) {
        try {
            catrecRepository.saveAndFlush(catrecEntity);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl updateCatrecEntity : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Transactional
    @Override
    public boolean deleteSelectedDissolverRow(Integer id) {
        try {
            cateqRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl deleteSelectedDissolverRow : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean createNewCateqRow(CateqEntity cateqEntity) {
        try {
            cateqRepository.save(cateqEntity);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl createNewCateqRow : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean updateCateqRow(CateqEntity cateqEntity) {
        try {
            cateqRepository.saveAndFlush(cateqEntity);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl updateCateqRow : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean addComponent(CatrawEntity catrawEntity) {
        try {
            catrawRepository.save(catrawEntity);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl addComponent : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean updateComponent(CatrawEntity catrawEntity) {
        try {
            catrawRepository.saveAndFlush(catrawEntity);
            return true;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl updateComponent : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }

    @Override
    public boolean deleteComponent(Integer id) {
        try {
            catrawRepository.deleteById(id);
            return false;
        } catch (Exception e) {
            log.error("Error UpdateDataDaoImpl deleteComponent : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }
}
