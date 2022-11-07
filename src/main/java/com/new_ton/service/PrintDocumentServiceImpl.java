package com.new_ton.service;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.print.PrinterJob;
import java.io.File;

@Service
public class PrintDocumentServiceImpl implements PrintDocumentService {

    private static final Logger log = LoggerFactory.getLogger(PrintDocumentServiceImpl.class);

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
        } catch (Exception var7) {
            log.error("Error printDocument : {}, {}", ExceptionUtils.getMessage(var7), ExceptionUtils.getMessage(var7.getCause()));
            return false;
        }
    }
}
