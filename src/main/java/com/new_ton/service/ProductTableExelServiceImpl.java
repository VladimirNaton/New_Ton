package com.new_ton.service;

import com.new_ton.domain.dto.ProductTableDto;
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

@Service
public class ProductTableExelServiceImpl implements ProductTableExelService {
    private static final Logger log = LoggerFactory.getLogger(ProductTableExelServiceImpl.class);
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<ProductTableDto> productTableDtoList;

    public ProductTableExelServiceImpl(List<ProductTableDto> productTableDtoList) {
        this.productTableDtoList = productTableDtoList;
        this.workbook = new XSSFWorkbook();
    }

    public void writeHeaderLine() {
        try {
            this.sheet = this.workbook.createSheet("Таблица продукта");
            Row row = this.sheet.createRow(0);
            CellStyle style = this.workbook.createCellStyle();
            XSSFFont font = this.workbook.createFont();
            font.setBold(true);
            font.setFontHeight(16.0D);
            style.setFont(font);
            this.createCell(row, 0, "Id продукта", style);
            this.createCell(row, 1, "Дата создания", style);
            this.createCell(row, 2, "Дата план", style);
            this.createCell(row, 3, "Дата производства", style);
            this.createCell(row, 4, "Бренд", style);
            this.createCell(row, 5, "Название продукта", style);
            this.createCell(row, 6, "Спецификация", style);
            this.createCell(row, 7, "Процент", style);
            this.createCell(row, 8, "Вес", style);
        } catch (Exception var4) {
            log.error("Error writeHeaderLine : {}, {}", ExceptionUtils.getMessage(var4), ExceptionUtils.getMessage(var4.getCause()));
        }

    }

    public void createCell(Row row, int columnCount, Object value, CellStyle style) {
        try {
            this.sheet.autoSizeColumn(columnCount);
            Cell cell = row.createCell(columnCount);
            if (value instanceof Integer) {
                cell.setCellValue((double)(Integer)value);
            } else if (value instanceof Date) {
                Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String stringValue = formatter.format(value);
                cell.setCellValue(stringValue);
            } else if (value instanceof Double) {
                cell.setCellValue(String.valueOf(value));
            } else {
                cell.setCellValue((String)value);
            }

            cell.setCellStyle(style);
        } catch (Exception var8) {
            log.error("Error createCell : {}, {}", ExceptionUtils.getMessage(var8), ExceptionUtils.getMessage(var8.getCause()));
        }

    }

    public void writeDataLines() {
        try {
            int rowCount = 1;
            CellStyle style = this.workbook.createCellStyle();
            XSSFFont font = this.workbook.createFont();
            font.setFontHeight(14.0D);
            style.setFont(font);
            Iterator var4 = this.productTableDtoList.iterator();

            while(var4.hasNext()) {
                ProductTableDto ptd = (ProductTableDto)var4.next();
                Row row = this.sheet.createRow(rowCount++);
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
        } catch (Exception var8) {
            log.error("Error createCell : {}, {}", ExceptionUtils.getMessage(var8), ExceptionUtils.getMessage(var8.getCause()));
        }

    }

    public void export(HttpServletResponse response) {
        try {
            this.writeHeaderLine();
            this.writeDataLines();
            ServletOutputStream outputStream = response.getOutputStream();

            try {
                this.workbook.write(outputStream);
                this.workbook.close();
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
        } catch (Exception var7) {
            log.error("Error export : {}, {}", ExceptionUtils.getMessage(var7), ExceptionUtils.getMessage(var7.getCause()));
        }

    }
}
