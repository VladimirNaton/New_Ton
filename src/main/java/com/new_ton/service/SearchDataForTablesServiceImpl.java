package com.new_ton.service;

import com.new_ton.dao.SearchDataForTablesDao;
import com.new_ton.domain.dto.*;
import com.new_ton.domain.dto.EditeRecipeTableRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class SearchDataForTablesServiceImpl implements SearchDataForTablesService {
    private final SearchDataForTablesDao searchDataForTablesDao;
    private final ColumnNameService columnNameService;


    @Override
    public TechnologistPageLeftTableResponseDto getDataForTechnologistLeftTable(RequestTechnologistPageLeftTableDto requestTechnologistPageLeftTableDto) {
        try {
            String columnName = columnNameService.getColumnNameByTechnologistPageLeftDataTable(requestTechnologistPageLeftTableDto.getOrderColumn());
            Pageable pageable;
            if (requestTechnologistPageLeftTableDto.getOrderType().equals("asc")) {
                pageable = PageRequest.of(requestTechnologistPageLeftTableDto.getStart() / requestTechnologistPageLeftTableDto.getLength(), requestTechnologistPageLeftTableDto.getLength(), Sort.by(columnName).ascending());
            } else {
                pageable = PageRequest.of(requestTechnologistPageLeftTableDto.getStart() / requestTechnologistPageLeftTableDto.getLength(), requestTechnologistPageLeftTableDto.getLength(), Sort.by(columnName).descending());
            }

            Page<CatalogDtoByLeftTable> page;
            if (requestTechnologistPageLeftTableDto.getSearchValue().equals("")) {
                page = searchDataForTablesDao.getDataForTechnologistLeftTable(pageable);
            } else {
                page = searchDataForTablesDao.getDataForTechnologistLeftTableWithSearchData(pageable, requestTechnologistPageLeftTableDto.getSearchValue());
            }

            TechnologistPageLeftTableResponseDto responseDto = new TechnologistPageLeftTableResponseDto();
            responseDto.setDraw(requestTechnologistPageLeftTableDto.getDraw());
            responseDto.setRecordsTotal(page.getTotalElements());
            responseDto.setData(page.getContent());
            responseDto.setRecordsFiltered(page.getTotalElements());
            return responseDto;
        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl getDataForTechnologistLeftTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return new TechnologistPageLeftTableResponseDto();
    }

    @Override
    public TechnologistPageRightTableResponseDto getDataForTechnologistRightTable(RequestTechnologistPageRightTableDto requestTechnologistPageRightTableDto) {
        try {
            String columnName = columnNameService.getColumnNameByTechnologistPageRightDataTable(requestTechnologistPageRightTableDto.getOrderColumn());
            Pageable pageable;
            if (requestTechnologistPageRightTableDto.getOrderType().equals("asc")) {
                pageable = PageRequest.of(requestTechnologistPageRightTableDto.getStart() / requestTechnologistPageRightTableDto.getLength(), requestTechnologistPageRightTableDto.getLength(), Sort.by(columnName).ascending());
            } else {
                pageable = PageRequest.of(requestTechnologistPageRightTableDto.getStart() / requestTechnologistPageRightTableDto.getLength(), requestTechnologistPageRightTableDto.getLength(), Sort.by(columnName).descending());
            }
            Page<MainTableDto> page = searchDataForTablesDao.getDataForTechnologistRightTable(pageable);
            TechnologistPageRightTableResponseDto responseDto = new TechnologistPageRightTableResponseDto();
            responseDto.setDraw(requestTechnologistPageRightTableDto.getDraw());
            responseDto.setRecordsTotal(page.getTotalElements());
            List<MainTableDto> mainTableDtoList = page.getContent().stream().peek(elem -> {
                if (elem.getDatecr() != null) {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String strDate = dateFormat.format(elem.getDatecr());
                    elem.setDateCreate(strDate);
                }
            }).collect(Collectors.toList());
            responseDto.setData(mainTableDtoList);
            responseDto.setRecordsFiltered(page.getTotalElements());
            return responseDto;
        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl getDataForTechnologistRightTabl : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return new TechnologistPageRightTableResponseDto();
    }

    @Override
    public CatalogDtoSelectedRow searchDataFromSelectedCatalogRow(Integer idProd) {
        try {
            CatalogDtoSelectedRow catalogDtoSelectedRow = searchDataForTablesDao.searchDataFromSelectedCatalogRow(idProd);
            if (catalogDtoSelectedRow.getDatecr() != null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strDate = dateFormat.format(catalogDtoSelectedRow.getDatecr());
                catalogDtoSelectedRow.setDataCreate(strDate);
            }
            return catalogDtoSelectedRow;
        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl searchDataFromSelectedCatalogRow : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public EditeRecipeResponseDto searchDataForEditeRecipeTable(EditeRecipeTableRequestDto editeRecipeTableRequestDto) {
        try {
            List<EditeRecipeTableDto> editeRecipeTableDtoList = searchDataForTablesDao.searchDataForEditeRecipeTable(editeRecipeTableRequestDto.getIdMain());
            EditeRecipeResponseDto editeRecipeResponseDto = new EditeRecipeResponseDto();
            editeRecipeResponseDto.setData(editeRecipeTableDtoList);
            editeRecipeResponseDto.setDraw(editeRecipeTableRequestDto.getDraw());
            return editeRecipeResponseDto;
        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl searchDataForEditeRecipeTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public GetDataForInformationStringEditeRecipeDto getDataForInformationStringEditeRecipe(Integer idProd) {
        try {
            GetDataForInformationStringEditeRecipeDto getDataForInformationStringEditeRecipeDto = searchDataForTablesDao.getDataForInformationStringEditeTable(idProd);
            if (getDataForInformationStringEditeRecipeDto.getDateCreate() != null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strDate = dateFormat.format(getDataForInformationStringEditeRecipeDto.getDateCreate());
                getDataForInformationStringEditeRecipeDto.setDateString(strDate);
            }
            return getDataForInformationStringEditeRecipeDto;
        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl getDataForInformationStringEditeRecipe : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public EditeRecipeCatalogTableResponseDto getDataForEditeRecipeComponentTable(EditeRecipeComponentTableRequestDto editeRecipeComponentTableRequestDto) {
        try {
            EditeRecipeCatalogTableResponseDto editeRecipeCatalogTableResponseDto = new EditeRecipeCatalogTableResponseDto();
            Pageable pageable = PageRequest.of(editeRecipeComponentTableRequestDto.getStart() / editeRecipeComponentTableRequestDto.getLength(), editeRecipeComponentTableRequestDto.getLength(), Sort.by("id").ascending());

            Page<EditeRecipeComponentTableDto> editeRecipeComponentTableDtoPage;

            if (editeRecipeComponentTableRequestDto.getFindComponent().equals("")) {
                editeRecipeComponentTableDtoPage = searchDataForTablesDao.getDataForEditeRecipeComponentTable(editeRecipeComponentTableRequestDto.getCodeSearch(), pageable);
            } else {
                editeRecipeComponentTableDtoPage = searchDataForTablesDao.getDataForEditeRecipeComponentTableWithSearch(editeRecipeComponentTableRequestDto.getCodeSearch(), editeRecipeComponentTableRequestDto.getFindComponent(), pageable);
            }

            List<EditeRecipeComponentTableDto> editeRecipeComponentTableDtoList = editeRecipeComponentTableDtoPage.toList();
            editeRecipeComponentTableDtoList.stream().peek(elem -> {
                if (elem.getDate() != null) {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String strDate = dateFormat.format(elem.getDate());
                    elem.setDateStr(strDate);
                }
            }).collect(Collectors.toList());

            editeRecipeCatalogTableResponseDto.setData(editeRecipeComponentTableDtoList);
            editeRecipeCatalogTableResponseDto.setDraw(editeRecipeComponentTableRequestDto.getDraw());
            editeRecipeCatalogTableResponseDto.setRecordsTotal(editeRecipeComponentTableDtoPage.getTotalElements());
            editeRecipeCatalogTableResponseDto.setRecordsFiltered(editeRecipeComponentTableDtoPage.getTotalElements());
            return editeRecipeCatalogTableResponseDto;
        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl getDataForEditeRecipeComponentTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public GetDataForSelectedRowEditeRecipeTableResponseDto getDataForSelectedRowEditeRecipeTable(Integer id) {
        try {
            GetDataForSelectedRowEditeRecipeTableResponseDto getDataForSelectedRowEditeRecipeTableResponseDto = searchDataForTablesDao.getDataForSelectedRowEditeRecipeTable(id);
            if (getDataForSelectedRowEditeRecipeTableResponseDto.getPastdate() != null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat timeFormat = new SimpleDateFormat("hh:mm");
                String strDate = dateFormat.format(getDataForSelectedRowEditeRecipeTableResponseDto.getPastdate());
                String strTime = timeFormat.format(getDataForSelectedRowEditeRecipeTableResponseDto.getPastdate());
                getDataForSelectedRowEditeRecipeTableResponseDto.setDateString(strDate + "T" + strTime);
            }
            return getDataForSelectedRowEditeRecipeTableResponseDto;
        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl getDataForSelectedRowEditeRecipeTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }
}
