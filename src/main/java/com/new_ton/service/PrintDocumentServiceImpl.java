package com.new_ton.service;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.springframework.stereotype.Service;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.print.PrinterJob;
import java.io.File;

@Log4j2
@Service
public class PrintDocumentServiceImpl implements PrintDocumentService {


    public boolean printDocument(String documentPath) {
        try {
            log.info("Start print document path : " + documentPath);
            PDDocument document = PDDocument.load(new File(documentPath));
            PrintService printServices = PrintServiceLookup.lookupDefaultPrintService();
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPageable(new PDFPageable(document));
            job.setPrintService(printServices);
            job.print();
            document.close();
            log.info("End print document path : " + documentPath);
            return true;
        } catch (Exception e) {
            log.error("Error printDocument : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
            return false;
        }
    }
}
