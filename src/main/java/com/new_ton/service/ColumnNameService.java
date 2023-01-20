package com.new_ton.service;


public interface ColumnNameService {

    String getColumnNameCalibrationTable(String columnNumber);

    String getColumnNameProductTable(String columnNumber);

    String getColumnNameByTechnologistPageLeftDataTable(String columnNumber);

    String getColumnNameByTechnologistPageRightDataTable(String columnNumber);
}
