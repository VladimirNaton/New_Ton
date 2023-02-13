package com.new_ton.repository;

import com.new_ton.domain.dto.technologistdto.CatalogDtoSelectedRow;
import com.new_ton.domain.dto.technologistdto.CatalogDtoByLeftTable;
import com.new_ton.domain.entities.CatalogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CatalogRepository extends JpaRepository<CatalogEntity, Integer> {
    <T> Page<T> findAllBy(Class<T> type, Pageable pageable);

    @Query("SELECT new com.new_ton.domain.dto.technologistdto.CatalogDtoByLeftTable(cat.idpr, cat.brend, cat.nameprod)  FROM CatalogEntity cat  WHERE LOWER(cat.nameprod) LIKE LOWER(CONCAT('%', ?1,'%'))")
    Page<CatalogDtoByLeftTable> findByNameProdWithPagination(String nameProd, Pageable pageable);

    @Query("select new com.new_ton.domain.dto.technologistdto.CatalogDtoSelectedRow(cat.datecr, cat.brend, cat.nameprod, cat.mass) from CatalogEntity cat where cat.idpr =?1")
    CatalogDtoSelectedRow getDataBySelectedRowCatalog(Integer idProd);
}
