package com.new_ton.dao;

import com.new_ton.domain.dto.CatalogDto;
import com.new_ton.domain.dto.MainTableDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchDataForTablesDao {

    Page<CatalogDto> getDataForTechnologistLeftTable(Pageable pageable);

    Page<MainTableDto> getDataForTechnologistRightTable(Pageable pageable);

    Page<CatalogDto> getDataForTechnologistLeftTableWithSearchData(Pageable pageable, String nameprod);
}
