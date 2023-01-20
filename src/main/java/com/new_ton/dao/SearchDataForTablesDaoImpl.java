package com.new_ton.dao;

import com.new_ton.domain.dto.CatalogDto;
import com.new_ton.domain.dto.MainTableDto;
import com.new_ton.repository.CatalogRepository;
import com.new_ton.repository.MainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Slf4j
@RequiredArgsConstructor
@Repository
public class SearchDataForTablesDaoImpl implements SearchDataForTablesDao {

    private final CatalogRepository catalogRepository;

    private final MainRepository mainRepository;

    @Override
    public Page<CatalogDto> getDataForTechnologistLeftTable(Pageable pageable) {
        try {
            return catalogRepository.findAllBy(CatalogDto.class, pageable);
        } catch (Exception e) {
            log.error("Error SearchDataForTablesDaoImpl getDataForTechnologistLeftTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Page.empty();
    }

    @Override
    public Page<MainTableDto> getDataForTechnologistRightTable(Pageable pageable) {
        try {
            return mainRepository.findAllBy(MainTableDto.class, pageable);
        } catch (Exception e) {
            log.error("Error SearchDataForTablesDaoImpl getDataForTechnologistRightTable : {}, {} ", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Page.empty();
    }
}
