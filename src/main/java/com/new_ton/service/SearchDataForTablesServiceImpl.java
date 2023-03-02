package com.new_ton.service;

import com.new_ton.dao.SearchDataForTablesDao;
import com.new_ton.domain.dto.accountmanager.*;
import com.new_ton.domain.dto.technologistdto.EditeRecipeTableRequestDto;
import com.new_ton.domain.dto.technologistdto.*;
import com.new_ton.domain.dto.testerdto.CheckTakeTemplateDto;
import com.new_ton.domain.dto.testerdto.ProtocolDto;
import com.new_ton.domain.entities.*;
import com.new_ton.domain.dto.accountmanager.DrawDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class SearchDataForTablesServiceImpl implements SearchDataForTablesService {
    private final SearchDataForTablesDao searchDataForTablesDao;
    private final ColumnNameService columnNameService;
    private final HttpServletRequest request;


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
            List<CatalogDtoByLeftTable> catalogDtoByLeftTableList = page.getContent()
                    .stream()
                    .peek(elem -> {
                        if (elem.getDatecr() != null) {
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            String strDate = dateFormat.format(elem.getDatecr());
                            elem.setStrDate(strDate);
                        }
                    }).collect(Collectors.toList());
            responseDto.setData(catalogDtoByLeftTableList);
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
            editeRecipeTableDtoList.stream().peek(elem -> {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strDate = dateFormat.format(elem.getPastdate());
                elem.setStrDate(strDate);
            }).collect(Collectors.toList());
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

            Page<EditeRecipeComponentTableDto> editeRecipeComponentTableDtoPage = null;

            Page<CatpastEntity> catpastEntityPage = null;

            if (editeRecipeComponentTableRequestDto.getFindComponent().equals("") && editeRecipeComponentTableRequestDto.getCodeSearch() != 9) {
                editeRecipeComponentTableDtoPage = searchDataForTablesDao.getDataForEditeRecipeComponentTable(editeRecipeComponentTableRequestDto.getCodeSearch(), pageable);
                editeRecipeCatalogTableResponseDto.setRecordsTotal(editeRecipeComponentTableDtoPage.getTotalElements());
                editeRecipeCatalogTableResponseDto.setRecordsFiltered(editeRecipeComponentTableDtoPage.getTotalElements());
            }
            if (!editeRecipeComponentTableRequestDto.getFindComponent().equals("") && editeRecipeComponentTableRequestDto.getCodeSearch() != 9) {
                editeRecipeComponentTableDtoPage = searchDataForTablesDao.getDataForEditeRecipeComponentTableWithSearch(editeRecipeComponentTableRequestDto.getCodeSearch(), editeRecipeComponentTableRequestDto.getFindComponent(), pageable);
                editeRecipeCatalogTableResponseDto.setRecordsTotal(editeRecipeComponentTableDtoPage.getTotalElements());
                editeRecipeCatalogTableResponseDto.setRecordsFiltered(editeRecipeComponentTableDtoPage.getTotalElements());
            }

            if (editeRecipeComponentTableRequestDto.getFindComponent().equals("") && editeRecipeComponentTableRequestDto.getCodeSearch() == 9) {
                catpastEntityPage = searchDataForTablesDao.getDataForEditeRecipeComponentTablePast(pageable);
                editeRecipeCatalogTableResponseDto.setRecordsTotal(catpastEntityPage.getTotalElements());
                editeRecipeCatalogTableResponseDto.setRecordsFiltered(catpastEntityPage.getTotalElements());
            }

            if (!editeRecipeComponentTableRequestDto.getFindComponent().equals("") && editeRecipeComponentTableRequestDto.getCodeSearch() == 9) {
                catpastEntityPage = searchDataForTablesDao.getDataForEditeRecipeComponentTableWithSearchPastName(editeRecipeComponentTableRequestDto.getFindComponent(), pageable);
                editeRecipeCatalogTableResponseDto.setRecordsTotal(catpastEntityPage.getTotalElements());
                editeRecipeCatalogTableResponseDto.setRecordsFiltered(catpastEntityPage.getTotalElements());
            }


            List<EditeRecipeComponentTableDto> editeRecipeComponentTableDtoList;

            if (editeRecipeComponentTableRequestDto.getCodeSearch() != 9) {
                editeRecipeComponentTableDtoList = editeRecipeComponentTableDtoPage.toList();
            } else {
                editeRecipeComponentTableDtoList = catpastEntityPage.toList().stream().map(elem -> {
                    return new EditeRecipeComponentTableDto(elem.getId(), elem.getDate(), elem.getNamepast());
                }).collect(Collectors.toList());
            }

            editeRecipeComponentTableDtoList.stream().peek(elem -> {
                if (elem.getDate() != null) {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String strDate = dateFormat.format(elem.getDate());
                    elem.setDateStr(strDate);
                }
            }).collect(Collectors.toList());


            editeRecipeCatalogTableResponseDto.setData(editeRecipeComponentTableDtoList);
            editeRecipeCatalogTableResponseDto.setDraw(editeRecipeComponentTableRequestDto.getDraw());
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

    @Override
    public GetDataForProductInProductionTableRequestDto getDataForProductInProductionTable(DrawDto drawDto) {
        try {
            List<GetDataForProductInProductionTableDto> getDataForProductInProductionTableDtoList = searchDataForTablesDao.getDataForProductInProductionTable();
            getDataForProductInProductionTableDtoList.stream().peek(elem -> {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String datecrStr = dateFormat.format(elem.getDatecr());
                String dateplStr = dateFormat.format(elem.getDatepl());
                elem.setDatecrStr(datecrStr);
                elem.setDateplStr(dateplStr);
                String state = searchDataForTablesDao.getState(elem.getState());
                elem.setStateStr(state);
            }).collect(Collectors.toList());

            GetDataForProductInProductionTableRequestDto getDataForProductInProductionTable = new GetDataForProductInProductionTableRequestDto();
            getDataForProductInProductionTable.setDraw(drawDto.getDraw());
            getDataForProductInProductionTable.setData(getDataForProductInProductionTableDtoList);
            return getDataForProductInProductionTable;

        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl getDataForProductInProductionTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public AccountManagerTableDataResponseDto getDataForAccountManagerTable(AccountManagerTableRequestDto accountManagerTableRequestDto) {
        try {
            Pageable pageable = PageRequest.of(accountManagerTableRequestDto.getStart() / accountManagerTableRequestDto.getLength(), accountManagerTableRequestDto.getLength(), Sort.by("idpr").ascending());

            Page<AccountManagerTableDataDto> page;
            if (accountManagerTableRequestDto.getSearchValue().equals("")) {
                page = searchDataForTablesDao.getDataForAccountManagerTableWithoutParam(pageable);
            } else {
                page = searchDataForTablesDao.getDataForAccountManagerTableWithParam(pageable, accountManagerTableRequestDto.getSearchValue());
            }

            List<AccountManagerTableDataDto> accountManagerTableDataDtos = page.getContent()
                    .stream().peek(elem -> {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String datecrStr = dateFormat.format(elem.getDatecr());
                        elem.setStrDate(datecrStr);
                    }).collect(Collectors.toList());

            AccountManagerTableDataResponseDto accountManagerTableDataResponseDto = new AccountManagerTableDataResponseDto();
            accountManagerTableDataResponseDto.setDraw(accountManagerTableRequestDto.getDraw());
            accountManagerTableDataResponseDto.setRecordsTotal(page.getTotalElements());
            accountManagerTableDataResponseDto.setRecordsFiltered(page.getTotalElements());
            accountManagerTableDataResponseDto.setData(accountManagerTableDataDtos);
            return accountManagerTableDataResponseDto;
        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl getDataForAccountManagerTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public EditeCatalogRecipeResponseDto getDataForEditeCatalogRecipe(EditeRecipeCatalogTableRequestDto editeRecipeCatalogTableRequestDto) {
        try {
            List<EditeCatalogRecipeTableDto> editeCatalogRecipeTableDtos = searchDataForTablesDao.getDataForEditeCatalogTable(editeRecipeCatalogTableRequestDto.getIdCat());
            EditeCatalogRecipeResponseDto editeCatalogRecipeResponseDto = new EditeCatalogRecipeResponseDto();
            editeCatalogRecipeResponseDto.setDraw(editeRecipeCatalogTableRequestDto.getDraw());
            editeCatalogRecipeResponseDto.setData(editeCatalogRecipeTableDtos);
            return editeCatalogRecipeResponseDto;
        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl getDataForEditeCatalogRecipe : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public GetDataForSelectedRowEditeCatalogRecipeTableResponseDto getDataForSelectedRowEditeRecipeCatalogTable(Integer id) {
        try {
            GetDataForSelectedRowEditeCatalogRecipeTableResponseDto getDataForSelectedRowEditeRecipeTableResponseDto = searchDataForTablesDao.getDataForSelectedRowEditeRecipeCatalogTable(id);
            if (getDataForSelectedRowEditeRecipeTableResponseDto.getPastdate() != null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat timeFormat = new SimpleDateFormat("hh:mm");
                String strDate = dateFormat.format(getDataForSelectedRowEditeRecipeTableResponseDto.getPastdate());
                String strTime = timeFormat.format(getDataForSelectedRowEditeRecipeTableResponseDto.getPastdate());
                getDataForSelectedRowEditeRecipeTableResponseDto.setDateString(strDate + "T" + strTime);
            }
            return getDataForSelectedRowEditeRecipeTableResponseDto;
        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl getDataForSelectedRowEditeRecipeCatalogTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public EditeDissolversResponceDto getDataForDissolversTable(EditeDissolversTableRequestDto editeDissolversTableRequestDto) {
        try {
            List<CateqDto> cateqDtos = searchDataForTablesDao.getDataForDissolversTable();
            EditeDissolversResponceDto editeDissolversResponceDto = new EditeDissolversResponceDto();
            editeDissolversResponceDto.setData(cateqDtos);
            editeDissolversResponceDto.setDraw(editeDissolversTableRequestDto.getDraw());
            return editeDissolversResponceDto;
        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl getDataForDissolversTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public CateqDto getDataForSelectedRowDissolversTable(Integer id) {
        try {
            Optional<CateqEntity> cateqEntityOptional = searchDataForTablesDao.getDataForSelectedRowDissolversTable(id);
            if (cateqEntityOptional.isPresent()) {
                CateqEntity cateqEntity = cateqEntityOptional.get();
                return new CateqDto(cateqEntity.getId(), cateqEntity.getEq(), cateqEntity.getCode());
            }

        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl getDataForSelectedRowDissolversTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public EditeCatalogTableResponseDto searchDataForEditeComponentTable(EditeRecipeComponentTableRequestDto editeRecipeTableRequestDto) {
        try {
            EditeCatalogTableResponseDto editeCatalogTableResponseDto = new EditeCatalogTableResponseDto();
            Pageable pageable = PageRequest.of(editeRecipeTableRequestDto.getStart() / editeRecipeTableRequestDto.getLength(), editeRecipeTableRequestDto.getLength(), Sort.by("id").ascending());

            Page<ComponentTableDto> editeComponentTableDtoPage = null;

            if (editeRecipeTableRequestDto.getFindComponent().equals("")) {
                editeComponentTableDtoPage = searchDataForTablesDao.getDataForEditeComponentTable(editeRecipeTableRequestDto.getCodeSearch(), pageable);
                editeCatalogTableResponseDto.setRecordsTotal(editeComponentTableDtoPage.getTotalElements());
                editeCatalogTableResponseDto.setRecordsFiltered(editeComponentTableDtoPage.getTotalElements());
            }
            if (!editeRecipeTableRequestDto.getFindComponent().equals("")) {
                editeComponentTableDtoPage = searchDataForTablesDao.getDataForEditeComponentTableWithSearch(editeRecipeTableRequestDto.getCodeSearch(), editeRecipeTableRequestDto.getFindComponent(), pageable);
                editeCatalogTableResponseDto.setRecordsTotal(editeComponentTableDtoPage.getTotalElements());
                editeCatalogTableResponseDto.setRecordsFiltered(editeComponentTableDtoPage.getTotalElements());
            }

            List<ComponentTableDto> componentTableDtoList = editeComponentTableDtoPage.toList().stream()
                    .peek(elem -> {
                        if (elem.getDate() != null) {
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            String strDate = dateFormat.format(elem.getDate());
                            elem.setDateStr(strDate);
                        }

                    }).collect(Collectors.toList());
            editeCatalogTableResponseDto.setData(componentTableDtoList);
            editeCatalogTableResponseDto.setDraw(editeRecipeTableRequestDto.getDraw());
            return editeCatalogTableResponseDto;

        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl searchDataForEditeComponentTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public ComponentTableDto getDataSelectedComponent(Integer id) {
        try {
            ComponentTableDto componentTableDto = searchDataForTablesDao.getDataSelectedComponent(id);
            if (componentTableDto.getDate() != null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strDate = dateFormat.format(componentTableDto.getDate());
                componentTableDto.setDateStr(strDate);
            }
            return componentTableDto;
        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl getDataSelectedComponent : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public GetDataForProductInProductionTableRequestDto getDataForTesterTable(DrawDto drawDto) {
        try {
            List<GetDataForProductInProductionTableDto> getDataForProductInProductionTableDtoList = searchDataForTablesDao.getDataForTesterTable();
            getDataForProductInProductionTableDtoList.stream().peek(elem -> {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String datecrStr = dateFormat.format(elem.getDatecr());
                String dateplStr = dateFormat.format(elem.getDatepl());
                elem.setDatecrStr(datecrStr);
                elem.setDateplStr(dateplStr);
                String state = searchDataForTablesDao.getState(elem.getState());
                elem.setStateStr(state);
            }).collect(Collectors.toList());

            GetDataForProductInProductionTableRequestDto getDataForProductInProductionTable = new GetDataForProductInProductionTableRequestDto();
            getDataForProductInProductionTable.setDraw(drawDto.getDraw());
            getDataForProductInProductionTable.setData(getDataForProductInProductionTableDtoList);
            return getDataForProductInProductionTable;
        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl getDataForTesterTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public CommentToStageDto getCommentToStage(CommentToStageDto commentToStageDto) {
        try {
            CommentToStageDto comment = new CommentToStageDto();
            Optional<CommentToStageEntity> commentToStageEntityOptional = searchDataForTablesDao.getCommentToStage(commentToStageDto);
            if (commentToStageEntityOptional.isPresent()) {
                CommentToStageEntity commentToStageEntity = commentToStageEntityOptional.get();
                comment.setComment(commentToStageEntity.getComment());
                comment.setId(commentToStageEntity.getId());
                comment.setIdStage(commentToStageEntity.getIdStage());
                comment.setIdMain(commentToStageEntity.getIdMain());
            }
            return comment;

        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl getCommentToStage : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public CheckTakeTemplateDto checkTakeTemplate(Integer id) {
        try {
            Optional<MainEntity> mainEntityOptional = searchDataForTablesDao.getMainEntityById(id);
            if (mainEntityOptional.isPresent()) {
                MainEntity mainEntity = mainEntityOptional.get();
                if (mainEntity.getState() == 6) {
                    Optional<RawEntity> rawEntityOptional = searchDataForTablesDao.checkTakeTemplate(id);
                    if (rawEntityOptional.isPresent()) {
                        RawEntity rawEntity = rawEntityOptional.get();
                        Calendar calendar = Calendar.getInstance();
                        int monthNow = calendar.get(Calendar.MONTH) + 1;
                        int yearNow = calendar.get(Calendar.YEAR);
                        int dateNow = calendar.get(Calendar.DATE) - 1;
                        String strDate = String.valueOf(dateNow) + "." + String.valueOf(monthNow) + "." + String.valueOf(yearNow);
                        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(strDate);
                        Date dateStart = rawEntity.getDatestart();
                        if (dateStart.after(date) && rawEntity.getFacttimemix() == 0) {
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            CheckTakeTemplateDto checkTakeTemplateDto = new CheckTakeTemplateDto();
                            checkTakeTemplateDto.setTime(dateFormat.format(dateStart));
                            checkTakeTemplateDto.setId(rawEntity.getId());
                            return checkTakeTemplateDto;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl checkTakeTemplate : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }

    @Override
    public ProtocolDto getDataForProtocol(Integer id) {
        try {
            SecurityContextImpl ct = (SecurityContextImpl) request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
            Optional<MainEntity> mainEntityOptional = searchDataForTablesDao.getMainEntityById(id);
            if (mainEntityOptional.isPresent()) {
                MainEntity mainEntity = mainEntityOptional.get();
                ProtocolDto protocolDto = new ProtocolDto();
                protocolDto.setBrend(mainEntity.getBrend());
                protocolDto.setNameprod(mainEntity.getNameprod());
                String userFio = searchDataForTablesDao.getUserFio(ct.getAuthentication().getName());
                protocolDto.setLabFio(userFio);
                LocalDate localDate = LocalDate.now().plusMonths(6);
                Date futureDate = java.util.Date.from(localDate.atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                protocolDto.setFutureDate(dateFormat.format(futureDate));
                return protocolDto;
            }
            return null;
        } catch (Exception e) {
            log.error("Error SearchDataForTablesServiceImpl getDataForProtocol : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return null;
    }
}
