package com.new_ton.service;


import com.new_ton.dao.MainTableDao;
import com.new_ton.domain.dto.ProductTableDto;
import com.new_ton.domain.dto.ProductTableRequestDto;
import com.new_ton.domain.dto.ProductTableResponseDto;
import com.new_ton.domain.dto.ProductTableResponseEntityDto;
import com.new_ton.domain.entities.MainEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class ProductTableServiceImpl implements ProductTableService {

    private final MainTableDao mainTableDao;
    private final ColumnNameService columnNameService;


    public ProductTableResponseDto getProductTableDate(ProductTableRequestDto productTableRequestDto) {
        try {
            ProductTableResponseDto productTableResponseDto = new ProductTableResponseDto();
            String column = columnNameService.getColumnNameProductTable(productTableRequestDto.getOrderColumn());
            productTableRequestDto.setOrderColumn(column);
            ProductTableResponseEntityDto productTableResponseEntityDto = mainTableDao.getProductTableData(productTableRequestDto);
            List<MainEntity> mainEntityList = productTableResponseEntityDto.getMainEntityList();
            List<ProductTableDto> data = new ArrayList<>();

            for (MainEntity me : mainEntityList) {
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
        } catch (Exception e) {
            log.error("Error getProductTableDate : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
            return null;
        }
    }
}
