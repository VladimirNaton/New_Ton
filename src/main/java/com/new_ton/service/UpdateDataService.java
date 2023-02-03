package com.new_ton.service;

import com.new_ton.domain.dto.SendProductToTellerDto;

public interface UpdateDataService {
    boolean moveCatalogRowToMain(Integer id);

    boolean sentProductToTeller(SendProductToTellerDto sendProductToTellerDto);

    boolean deleteSelectedRowFromRecipeTable(Integer id);
}
