package com.new_ton.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.new_ton.domain.dto.productionpage.CreateResponsibleSemiFinishedProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import javax.print.PrintException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RequiredArgsConstructor
@Log4j2
@Service
public class ResponsibleSemiFinishedProductPdfServiceImpl implements ResponsibleSemiFinishedProductPdfService {

    private final PrintDocumentService printDocumentService;


    @Override
    public boolean responsibleSemiFinishedProduct(CreateResponsibleSemiFinishedProductDto dto) throws DocumentException, IOException, PrintException {
        try {
            StringBuilder mainDirectory = new StringBuilder();
            mainDirectory.append("C:/NewTon");
            if (!Files.exists(Paths.get(mainDirectory.toString()))) {
                log.info("Create directory " + mainDirectory);
                Files.createDirectory(Paths.get(mainDirectory.toString()));
            }

            mainDirectory.append("/SemifinishedProductLabels");
            if (!Files.exists(Paths.get(mainDirectory.toString()))) {
                Files.createDirectory(Paths.get(mainDirectory.toString()));
                log.info("Create directory " + mainDirectory);
            }

            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = dateFormat.format(date);
            mainDirectory.append("/").append(currentDate);
            if (!Files.exists(Paths.get(mainDirectory.toString()))) {
                Files.createDirectory(Paths.get(mainDirectory.toString()));
                log.info("Create directory " + mainDirectory);
            }

            mainDirectory.append("/SemifinishedProductLabel_id_prod_").append(dto.getIdProd()).append("_path_").append(dto.getPath()).append(".pdf");
            File file = new File(mainDirectory.toString());
            if (file.exists()) {
                file.delete();
                log.info("Delete directory " + mainDirectory);
            }

            log.info("Start create document " + mainDirectory);
            URL FONT = this.getClass().getClassLoader().getResource("fonts/TimesNewRomanPsmt.ttf");
            BaseFont baseFont = BaseFont.createFont(FONT.toString(), "Identity-H", true);
            Font font1 = new Font(baseFont, 15.0F, 1);
            Font font2 = new Font(baseFont, 11.0F, 0);
            Font font3 = new Font(baseFont, 12.0F, 1);
            Font font4 = new Font(baseFont, 40.0F, 1);
            Font font5 = new Font(baseFont, 25.0F, 1);
            Font font6 = new Font(baseFont, 14.0F, 0);
            URL imageNewTon = this.getClass().getClassLoader().getResource("image/new_ton.png");
            Image image1 = Image.getInstance(imageNewTon.toString());
            URL imageAttention = this.getClass().getClassLoader().getResource("image/Attention.png");
            Image image2 = Image.getInstance(imageAttention.toString());
            URL imageFlammable = this.getClass().getClassLoader().getResource("image/Flammable.png");
            Image image3 = Image.getInstance(imageFlammable.toString());
            URL imageSquares = this.getClass().getClassLoader().getResource("image/Squares.png");
            Image image4 = Image.getInstance(imageSquares.toString());
            URL imageKg = this.getClass().getClassLoader().getResource("image/kg.png");
            Image image5 = Image.getInstance(imageKg.toString());
            PdfPTable table = new PdfPTable(new float[]{1.6F, 1.0F, 1.0F, 1.5F, 0.8F, 0.8F});
            table.setWidthPercentage(95.0F);
            PdfPCell cell = new PdfPCell();
            cell.setBorderWidthLeft(1.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(1.5F);
            cell.setImage(image1);
            cell.setFixedHeight(25.0F);
            cell.setHorizontalAlignment(1);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Відповідальний напівфабрикат", font3));
            cell.setFixedHeight(25.0F);
            cell.setColspan(3);
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(1.5F);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Код форми", font2));
            cell.setFixedHeight(25.0F);
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(1.5F);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Ф К5-24", font3));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(1.5F);
            cell.setBorderWidthTop(1.5F);
            cell.setFixedHeight(25.0F);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Торгова марка", font6));
            cell.setBorderWidthLeft(1.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(90.0F);
            cell.setRowspan(2);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dto.getBrand(), font4));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.0F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(45.0F);
            cell.setColspan(2);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Партія №", font2));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(45.0F);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dto.getBatchNumber(), font1));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(45.0F);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf(dto.getPath()), font1));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(1.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(45.0F);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell();
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.0F);
            cell.setBorderWidthTop(0.0F);
            cell.setFixedHeight(45.0F);
            cell.setImage(image3);
            cell.setHorizontalAlignment(2);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell();
            cell.setBorderWidthLeft(0.0F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.0F);
            cell.setFixedHeight(45.0F);
            cell.setImage(image2);
            cell.setHorizontalAlignment(0);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Пломба №", font2));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(45.0F);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell();
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(1.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(45.0F);
            cell.setColspan(2);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Найменування продукції", font6));
            cell.setBorderWidthLeft(1.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(60.0F);
            cell.setRowspan(2);
            cell.setHorizontalAlignment(0);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dto.getProductName(), font3));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setColspan(2);
            cell.setRowspan(2);
            cell.setFixedHeight(60.0F);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Результати та рекомендації \n згідно протоколу випробувань", font3));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(1.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(40.0F);
            cell.setColspan(3);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Розведення, л", font2));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(20.0F);
            cell.setHorizontalAlignment(0);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            String dilutionFull = dto.getDilution();
            int index = dilutionFull.indexOf("-");
            String dilution1 = dilutionFull.substring(0, index);
            String dilution2 = dilutionFull.substring(index + 1);
            cell = new PdfPCell(new Phrase(dilution1, font2));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(20.0F);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dilution2, font2));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(1.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(20.0F);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Дата виготовлення", font6));
            cell.setBorderWidthLeft(1.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(30.0F);
            cell.setHorizontalAlignment(0);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dto.getDateOfManufacture(), font5));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(30.0F);
            cell.setColspan(2);
            cell.setVerticalAlignment(5);
            cell.setHorizontalAlignment(1);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Щільність, г/см3", font2));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(30.0F);
            cell.setHorizontalAlignment(0);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dto.getDensity(), font2));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(1.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(30.0F);
            cell.setColspan(2);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Кількість", font6));
            cell.setBorderWidthLeft(1.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(40.0F);
            cell.setRowspan(2);
            cell.setHorizontalAlignment(0);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dto.getNumber() + " кг", font5));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(50.0F);
            cell.setColspan(2);
            cell.setRowspan(2);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("В'язкість, с", font2));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(25.0F);
            cell.setHorizontalAlignment(0);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dto.getViscosity(), font2));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(1.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(25.0F);
            cell.setColspan(2);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Кількість газу, г", font2));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(25.0F);
            cell.setHorizontalAlignment(0);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dto.getQuantity(), font2));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(1.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(25.0F);
            cell.setColspan(2);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Кінцевий термін \nпридатності", font6));
            cell.setBorderWidthLeft(1.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(60.0F);
            cell.setRowspan(2);
            cell.setHorizontalAlignment(0);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dto.getBestBeforeDate(), font5));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(60.0F);
            cell.setColspan(2);
            cell.setRowspan(2);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Тип клапану", font2));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(20.0F);
            cell.setHorizontalAlignment(0);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dto.getValveType(), font2));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(1.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(20.0F);
            cell.setColspan(2);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Тип \nрозпилювальної \nголівки", font2));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(40.0F);
            cell.setHorizontalAlignment(0);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dto.getSprayHeadType(), font2));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(1.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(40.0F);
            cell.setColspan(2);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Посада", font3));
            cell.setBorderWidthLeft(1.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(15.0F);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Підпис", font3));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(18.0F);
            cell.setColspan(2);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("П.І.Б", font3));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(18.0F);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Дата", font3));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(1.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(18.0F);
            cell.setColspan(2);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Начальник виробничої \n дільниці цеху №2", font2));
            cell.setBorderWidthLeft(1.5F);
            cell.setBorderWidthBottom(0.75F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(30.0F);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase());
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.75F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setColspan(2);
            cell.setFixedHeight(30.0F);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dto.getSectionManager(), font2));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.75F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(30.0F);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(dto.getDateToday(), font3));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.75F);
            cell.setBorderWidthRight(1.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setColspan(2);
            cell.setFixedHeight(30.0F);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Залишок", font5));
            cell.setBorderWidthLeft(1.5F);
            cell.setBorderWidthBottom(1.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(1.5F);
            cell.setHorizontalAlignment(1);
            cell.setVerticalAlignment(5);
            cell.setRowspan(3);
            cell.setFixedHeight(75.0F);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("  "));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(1.5F);
            cell.setFixedHeight(25.0F);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("  "));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(1.5F);
            cell.setFixedHeight(25.0F);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("  "));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(1.5F);
            cell.setFixedHeight(25.0F);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("  "));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(1.5F);
            cell.setBorderWidthTop(1.5F);
            cell.setColspan(2);
            cell.setFixedHeight(25.0F);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("  "));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(25.0F);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("  "));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(25.0F);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("  "));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(25.0F);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("   "));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(0.5F);
            cell.setBorderWidthRight(1.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setColspan(2);
            cell.setFixedHeight(25.0F);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("  "));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(1.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(25.0F);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("  "));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(1.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(25.0F);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("  "));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(1.5F);
            cell.setBorderWidthRight(0.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setFixedHeight(25.0F);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("   "));
            cell.setBorderWidthLeft(0.5F);
            cell.setBorderWidthBottom(1.5F);
            cell.setBorderWidthRight(1.5F);
            cell.setBorderWidthTop(0.5F);
            cell.setColspan(2);
            cell.setFixedHeight(25.0F);
            table.addCell(cell);
            Document document = new Document();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            document.add(table);
            document.close();
            log.info("End create document " + mainDirectory);

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(mainDirectory.toString());

                boolean var34;
                try {
                    fileOutputStream.write(byteArrayOutputStream.toByteArray());
                    var34 = printDocumentService.printDocument(mainDirectory.toString());
                } catch (Throwable var37) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable var36) {
                        var37.addSuppressed(var36);
                    }

                    throw var37;
                }

                fileOutputStream.close();
                return var34;
            } catch (Exception e) {
                log.error("Error save document : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
            }
        } catch (Exception e) {
            log.error("Error create document responsibleSemiFinishedProduct : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return false;
    }
}
