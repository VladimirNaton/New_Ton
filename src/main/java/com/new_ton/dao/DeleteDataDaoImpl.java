package com.new_ton.dao;

import com.new_ton.repository.MainRepository;
import com.new_ton.repository.RawRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Repository
public class DeleteDataDaoImpl implements DeleteDataDao {

    private final RawRepository rawRepository;

    private final MainRepository mainRepository;


    @Transactional
    @Override
    public boolean deleteDataSelectedRowRightTableTechnologistPage(Integer idProd) {
        try {
            rawRepository.deleteAllByIdMain(idProd);
            mainRepository.deleteById(idProd);
            return true;
        } catch (Exception e) {
            log.error("Error DeleteDataDaoImp deleteDataSelectedRowRightTableTechnologistPage : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }
}
