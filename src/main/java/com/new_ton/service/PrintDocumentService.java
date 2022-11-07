package com.new_ton.service;

import javax.print.PrintException;
import java.io.FileNotFoundException;

public interface PrintDocumentService {
    boolean printDocument(String documentPath) throws FileNotFoundException, PrintException;
}
