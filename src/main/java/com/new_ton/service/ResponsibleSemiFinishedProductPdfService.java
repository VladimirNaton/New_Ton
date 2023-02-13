package com.new_ton.service;

import com.itextpdf.text.DocumentException;
import com.new_ton.domain.dto.productionpage.CreateResponsibleSemiFinishedProductDto;

import javax.print.PrintException;
import java.io.IOException;

public interface ResponsibleSemiFinishedProductPdfService {
    boolean responsibleSemiFinishedProduct(CreateResponsibleSemiFinishedProductDto createResponsibleSemiFinishedProductDto) throws DocumentException, IOException, PrintException;
}
