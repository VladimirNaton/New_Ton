package com.new_ton.service;


import com.itextpdf.text.DocumentException;

import java.io.IOException;

public interface PrintDischargePageService {
    boolean printDischargePage(int id) throws DocumentException, IOException;
}
