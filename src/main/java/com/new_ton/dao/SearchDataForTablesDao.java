package com.new_ton.dao;

import com.new_ton.domain.dto.CatalogDtoSelectedRow;
import com.new_ton.domain.dto.CatalogDtoByLeftTable;
import com.new_ton.domain.dto.MainTableDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchDataForTablesDao {

    Page<CatalogDtoByLeftTable> getDataForTechnologistLeftTable(Pageable pageable);

    Page<MainTableDto> getDataForTechnologistRightTable(Pageable pageable);

    Page<CatalogDtoByLeftTable> getDataForTechnologistLeftTableWithSearchData(Pageable pageable, String nameprod);

    CatalogDtoSelectedRow searchDataFromSelectedCatalogRow(Integer idProd);
}
