package com.new_ton.service;

import com.new_ton.domain.dto.ProductTableDto;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Log4j2
@Service
public class ProductTableExelServiceImpl implements ProductTableExelService {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<ProductTableDto> productTableDtoList;

    public ProductTableExelServiceImpl(List<ProductTableDto> productTableDtoList) {
        this.productTableDtoList = productTableDtoList;
        this.workbook = new XSSFWorkbook();
    }

    public void writeHeaderLine() {
        try {
            sheet = workbook.createSheet("Таблица продукта");
            Row row = sheet.createRow(0);
            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(16.0D);
            style.setFont(font);
            createCell(row, 0, "Id продукта", style);
            createCell(row, 1, "Дата создания", style);
            createCell(row, 2, "Дата план", style);
            createCell(row, 3, "Дата производства", style);
            createCell(row, 4, "Бренд", style);
            createCell(row, 5, "Название продукта", style);
            createCell(row, 6, "Спецификация", style);
            createCell(row, 7, "Процент", style);
            createCell(row, 8, "Вес", style);
        } catch (Exception e) {
            log.error("Error writeHeaderLine : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }

    }

    public void createCell(Row row, int columnCount, Object value, CellStyle style) {
        try {
            sheet.autoSizeColumn(columnCount);
            Cell cell = row.createCell(columnCount);
            if (value instanceof Integer) {
                cell.setCellValue((double) (Integer) value);
            } else if (value instanceof Date) {
                Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String stringValue = formatter.format(value);
                cell.setCellValue(stringValue);
            } else if (value instanceof Double) {
                cell.setCellValue(String.valueOf(value));
            } else {
                cell.setCellValue((String) value);
            }

            cell.setCellStyle(style);
        } catch (Exception e) {
            log.error("Error createCell : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }

    }

    public void writeDataLines() {
        try {
            int rowCount = 1;
            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setFontHeight(14.0D);
            style.setFont(font);

            for (ProductTableDto ptd : productTableDtoList) {
                Row row = sheet.createRow(rowCount++);
                int columnCount = 0;
                int var9 = columnCount + 1;
                this.createCell(row, columnCount, ptd.getIdpr(), style);
                this.createCell(row, var9++, ptd.getDatecr(), style);
                this.createCell(row, var9++, ptd.getDatepl(), style);
                this.createCell(row, var9++, ptd.getDatemade(), style);
                this.createCell(row, var9++, ptd.getBrend(), style);
                this.createCell(row, var9++, ptd.getNameprod(), style);
                this.createCell(row, var9++, ptd.getSp(), style);
                this.createCell(row, var9++, ptd.getPercent(), style);
                this.createCell(row, var9++, ptd.getMass(), style);
            }
        } catch (Exception e) {
            log.error("Error createCell : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }

    }

    public void export(HttpServletResponse response) {
        try {
            writeHeaderLine();
            writeDataLines();
            ServletOutputStream outputStream = response.getOutputStream();

            try {
                workbook.write(outputStream);
                workbook.close();
            } catch (Throwable var6) {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Exception e) {
            log.error("Error export : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
    }
}
