package com.new_ton.service;


import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ColumnNameServiceImpl implements ColumnNameService {

    public String getColumnNameCalibrationTable(String columnNumber) {
        try {
            String columnName = null;
            if (columnNumber != null) {
                switch (columnNumber) {
                    case "0":
                        columnName = "date";
                        break;
                    case "1":
                        columnName = "nw";
                        break;
                    case "2":
                        columnName = "plmass";
                        break;
                    case "3":
                        columnName = "factmass";
                        break;
                    case "4":
                        columnName = "operfio";
                        break;
                }
            } else {
                columnName = "date";
            }

            return columnName;
        } catch (Exception e) {
            log.error("Error getColumnNameCalibrationTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
            return null;
        }
    }

    public String getColumnNameProductTable(String columnNumber) {
        try {
            String columnName = null;

            switch (columnNumber) {
                case "0":
                    columnName = "idpr";
                    break;
                case "1":
                    columnName = "datecr";
                    break;
                case "2":
                    columnName = "datepl";
                    break;
                case "3":
                    columnName = "datemade";
                    break;
                case "4":
                    columnName = "brend";
                    break;
                case "5":
                    columnName = "nameprod";
                    break;
                case "6":
                    columnName = "sp";
                    break;
                case "7":
                    columnName = "percent";
                    break;
                case "8":
                    columnName = "mass";
                    break;
            }
            return columnName;
        } catch (Exception e) {
            log.error("Error getColumnNameProductTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
            return null;
        }
    }

    @Override
    public String getColumnNameByTechnologistPageLeftDataTable(String columnNumber) {
        String columnName = "";
        try {
            switch (columnNumber) {
                case "0":
                    columnName = "idpr";
                    break;
                case "1":
                    columnName = "brend";
                    break;
                case "2":
                    columnName = "nameprod";
                    break;
            }
        } catch (Exception e) {
            log.error("Error ColumnNameServiceImpl getColumnNameByTechnologistPageLeftDataTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
        return columnName;
    }
}
