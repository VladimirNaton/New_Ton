package com.new_ton.dao;

import com.new_ton.domain.entities.CatalogEntity;
import com.new_ton.domain.entities.CatrecEntity;
import com.new_ton.domain.entities.MainEntity;
import com.new_ton.domain.entities.RawEntity;

import java.util.List;

public interface UpdateDataDao {
    Integer saveNewMainRow(MainEntity mainEntity);

    boolean saveRawNewRows(List<RawEntity> rawEntityList);

    boolean sendProductToTeller(MainEntity mainEntity);

    RawEntity deleteSelectedRowFromRecipeTable(Integer id);

    boolean updateRawEntityList(List<RawEntity> rawEntityList);

    boolean saveNewRowToRawTable(RawEntity rawEntity);

    boolean updateRawEntity(RawEntity rawEntity);

    boolean updateMainEntity(MainEntity mainEntity);

    boolean deleteCatrecEntityByIdCat(Integer idCat);

    boolean saveCatrecEntity(List<CatrecEntity> catrecEntityList);

    boolean updateCatalogEntity(CatalogEntity catalogEntity);

    boolean deleteSelectedCatalogRow(Integer id);

    boolean saveNewCatalogRow(CatalogEntity catalogEntity);
}
