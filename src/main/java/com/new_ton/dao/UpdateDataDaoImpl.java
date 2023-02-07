package com.new_ton.dao;

import com.new_ton.domain.dto.SaveRecipeDto;
import com.new_ton.domain.entities.MainEntity;
import com.new_ton.domain.entities.RawEntity;
import com.new_ton.repository.CatalogRepository;
import com.new_ton.repository.CatrecRepository;
import com.new_ton.repository.MainRepository;
import com.new_ton.repository.RawRepository;
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
}
