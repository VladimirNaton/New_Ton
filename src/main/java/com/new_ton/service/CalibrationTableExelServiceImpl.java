package com.new_ton.service;

import com.new_ton.domain.dto.CalibrationTableDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
        sheet = workbook.createSheet("Журнал взвешиваний");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16.0D);
        style.setFont(font);
        createCell(row, 0, "Дата/время", style);
        createCell(row, 1, "№ весов", style);
        createCell(row, 2, "Вес план.", style);
        createCell(row, 3, "Вес факт.", style);
        createCell(row, 4, "Пользователь", style);
    }

    public void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((double) (Integer) value);
        } else if (value instanceof Date) {
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String stringValue = formatter.format(value);
            cell.setCellValue(stringValue);
        } else {
            cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);
    }

    public void writeDataLines() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14.0D);
        style.setFont(font);

        for (CalibrationTableDto ctd: calibrationTableDtoList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            int var8 = columnCount + 1;
            createCell(row, columnCount, ctd.getDate(), style);
            createCell(row, var8++, ctd.getNw(), style);
            createCell(row, var8++, ctd.getPlmass(), style);
            createCell(row, var8++, ctd.getFactmass(), style);
            createCell(row, var8++, ctd.getOperfio(), style);
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
