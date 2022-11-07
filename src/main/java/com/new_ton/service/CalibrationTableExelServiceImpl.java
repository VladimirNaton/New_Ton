package com.new_ton.service;

import com.lider.domain.dto.CalibrationTableDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CalibrationTableExelServiceImpl implements CalibrationTableExelService {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<CalibrationTableDto> calibrationTableDtoList;

    public CalibrationTableExelServiceImpl(List<CalibrationTableDto> calibrationTableDtoList) {
        this.calibrationTableDtoList = calibrationTableDtoList;
        this.workbook = new XSSFWorkbook();
    }

    public void writeHeaderLine() {
        this.sheet = this.workbook.createSheet("Журнал взвешиваний");
        Row row = this.sheet.createRow(0);
        CellStyle style = this.workbook.createCellStyle();
        XSSFFont font = this.workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16.0D);
        style.setFont(font);
        this.createCell(row, 0, "Дата/время", style);
        this.createCell(row, 1, "№ весов", style);
        this.createCell(row, 2, "Вес план.", style);
        this.createCell(row, 3, "Вес факт.", style);
        this.createCell(row, 4, "Пользователь", style);
    }

    public void createCell(Row row, int columnCount, Object value, CellStyle style) {
        this.sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((double)(Integer)value);
        } else if (value instanceof Date) {
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String stringValue = formatter.format(value);
            cell.setCellValue(stringValue);
        } else {
            cell.setCellValue((String)value);
        }

        cell.setCellStyle(style);
    }

    public void writeDataLines() {
        int rowCount = 1;
        CellStyle style = this.workbook.createCellStyle();
        XSSFFont font = this.workbook.createFont();
        font.setFontHeight(14.0D);
        style.setFont(font);
        Iterator var4 = this.calibrationTableDtoList.iterator();

        while(var4.hasNext()) {
            CalibrationTableDto ctd = (CalibrationTableDto)var4.next();
            Row row = this.sheet.createRow(rowCount++);
            int columnCount = 0;
            int var8 = columnCount + 1;
            this.createCell(row, columnCount, ctd.getDate(), style);
            this.createCell(row, var8++, ctd.getNw(), style);
            this.createCell(row, var8++, ctd.getPlmass(), style);
            this.createCell(row, var8++, ctd.getFactmass(), style);
            this.createCell(row, var8++, ctd.getOperfio(), style);
        }

    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
