package com.new_ton.dao;

import com.new_ton.domain.dto.accountmanager.ComponentTableDto;
import com.new_ton.domain.dto.testerdto.CommentDto;
import com.new_ton.domain.entities.*;

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

    boolean saveNewRowToCatrecTable(CatrecEntity catrecEntity);

    CatrecEntity deleteSelectedRowFromCatalogRecipeTable(Integer id);

    boolean updateCatrecEntityList(List<CatrecEntity> catrecEntityList);

    boolean updateCatrecEntity(CatrecEntity catrecEntity);

    boolean deleteSelectedDissolverRow(Integer id);

    boolean createNewCateqRow(CateqEntity cateqEntity);

    boolean updateCateqRow(CateqEntity cateqEntity);

    boolean addComponent(CatrawEntity catrawEntity);

    boolean updateComponent(CatrawEntity catrawEntity);

    boolean deleteComponent(Integer id);

    boolean returnToWork(Integer id);

    boolean sendToReject(Integer id);

    boolean sendPutAside(Integer id);

    boolean sendComment(CommentDto commentDto);

    Integer saveCommentToStage(CommentToStageEntity commentToStageEntity);

    boolean updateLabprotEntity(List<LabprotEntity> labprotEntityList, Integer idMain);

    boolean sendToTaskShift(MainEntity mainEntity);

    boolean sendToProductInProduction(MainEntity mainEntity);

    boolean updateStatusRecipe(MainEntity mainEntity);
}
