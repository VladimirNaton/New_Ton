package com.new_ton.dao;

import com.new_ton.domain.dto.CatalogDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchDataForTablesDao {

    Page<CatalogDto> getDataForTechnologistLeftTable(Pageable pageable);
}
