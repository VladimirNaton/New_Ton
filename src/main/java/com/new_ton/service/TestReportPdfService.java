package com.new_ton.service;

import com.itextpdf.text.DocumentException;
import com.new_ton.domain.dto.productionpage.PrintTestReportDto;

import java.io.IOException;

public interface TestReportPdfService {
    boolean createTestReportPdf(PrintTestReportDto printTestReportDto) throws DocumentException, IOException;
}
