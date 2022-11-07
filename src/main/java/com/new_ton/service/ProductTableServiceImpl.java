package com.new_ton.service;


import com.lider.dao.MainTableDao;
import com.lider.domain.dto.ProductTableDto;
import com.lider.domain.dto.ProductTableRequestDto;
import com.lider.domain.dto.ProductTableResponseDto;
import com.lider.domain.dto.ProductTableResponseEntityDto;
import com.lider.domain.entities.MainEntity;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductTableServiceImpl implements ProductTableService {
    private static final Logger log = LoggerFactory.getLogger(ProductTableServiceImpl.class);
    private final MainTableDao mainTableDao;
    private final ColumnNameService columnNameService;

    public ProductTableServiceImpl(MainTableDao mainTableDao, ColumnNameService columnNameService) {
        this.mainTableDao = mainTableDao;
        this.columnNameService = columnNameService;
    }

    public ProductTableResponseDto getProductTableDate(ProductTableRequestDto productTableRequestDto) {
        try {
            ProductTableResponseDto productTableResponseDto = new ProductTableResponseDto();
            String column = this.columnNameService.getColumnNameProductTable(productTableRequestDto.getOrderColumn());
            productTableRequestDto.setOrderColumn(column);
            ProductTableResponseEntityDto productTableResponseEntityDto = this.mainTableDao.getProductTableData(productTableRequestDto);
            List<MainEntity> mainEntityList = productTableResponseEntityDto.getMainEntityList();
            List<ProductTableDto> data = new ArrayList();
            Iterator var7 = mainEntityList.iterator();

            while(var7.hasNext()) {
                MainEntity me = (MainEntity)var7.next();
                ProductTableDto productTableDto = new ProductTableDto();
                productTableDto.setIdpr(me.getIdpr());
                String pattern;
                SimpleDateFormat df;
                Date date;
                String dateAsString;
                if (me.getDatecr() != null) {
                    pattern = "yyyy-MM-dd HH:mm:ss";
                    df = new SimpleDateFormat(pattern);
                    date = me.getDatecr();
                    dateAsString = df.format(date);
                    productTableDto.setDatecr(dateAsString);
                }

                if (me.getDatepl() != null) {
                    pattern = "yyyy-MM-dd HH:mm:ss";
                    df = new SimpleDateFormat(pattern);
                    date = me.getDatepl();
                    dateAsString = df.format(date);
                    productTableDto.setDatepl(dateAsString);
                }

                if (me.getDatemade() != null) {
                    pattern = "yyyy-MM-dd HH:mm:ss";
                    df = new SimpleDateFormat(pattern);
                    date = me.getDatemade();
                    dateAsString = df.format(date);
                    productTableDto.setDatemade(dateAsString);
                }

                productTableDto.setBrend(me.getBrend());
                productTableDto.setNameprod(me.getNameprod());
                productTableDto.setSp(me.getSp());
                productTableDto.setPercent(me.getPercent());
                productTableDto.setMass(me.getMass());
                data.add(productTableDto);
            }

            productTableResponseDto.setData(data);
            if (productTableRequestDto.getRequestFlag().equals("request")) {
                productTableResponseDto.setDraw(productTableRequestDto.getDraw());
                productTableResponseDto.setRecordsTotal(productTableResponseEntityDto.getRecordsTotal());
                productTableResponseDto.setRecordsFiltered(productTableResponseEntityDto.getRecordsTotal());
            }

            return productTableResponseDto;
        } catch (Exception var14) {
            log.error("Error getProductTableDate : {}, {}", ExceptionUtils.getMessage(var14), ExceptionUtils.getMessage(var14.getCause()));
            return null;
        }
    }
}
