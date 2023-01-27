package com.new_ton.dao;

import com.new_ton.domain.dto.CatalogDtoSelectedRow;
import com.new_ton.domain.dto.CatalogDtoByLeftTable;
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
    public Page<CatalogDtoByLeftTable> getDataForTechnologistLeftTable(Pageable pageable) {
        try {
            return catalogRepository.findAllBy(CatalogDtoByLeftTable.class, pageable);
        } catch (Exception e) {
            log.error("Error SearchDataForTablesDaoImpl getDataForTechnologistLeftTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Page.empty();
    }

    @Override
    public Page<MainTableDto> getDataForTechnologistRightTable(Pageable pageable) {
        try {
            return mainRepository.findAllByState(1, MainTableDto.class, pageable);
        } catch (Exception e) {
            log.error("Error SearchDataForTablesDaoImpl getDataForTechnologistRightTable : {}, {} ", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Page.empty();
    }

    @Override
    public Page<CatalogDtoByLeftTable> getDataForTechnologistLeftTableWithSearchData(Pageable pageable, String nameprod) {
        try {
            return catalogRepository.findByNameProdWithPagination(nameprod, pageable);
        } catch (Exception e) {
            log.error("Error SearchDataForTablesDaoImpl getDataForTechnologistLeftTableWithSearchData {}, {} ", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return Page.empty();
    }

    @Override
    public CatalogDtoSelectedRow searchDataFromSelectedCatalogRow(Integer idProd) {
        try {
            return catalogRepository.getDataBySelectedRowCatalog(idProd);
        } catch (Exception e) {
            log.error("Error SearchDataForTablesDaoImpl searchDataFromSelectedCatalogRow : {}, {} ", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }
}
