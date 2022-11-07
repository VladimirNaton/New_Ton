package com.new_ton.service;


import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ColumnNameServiceImpl implements ColumnNameService {

    public ColumnNameServiceImpl() {
    }

    public String getColumnNameCalibrationTable(String columnNumber) {
        try {
            String columnName = null;

            if (columnNumber != null) {
                if (columnNumber.equals("0")) {
                    columnName = "date";
                }

                if (columnNumber.equals("1")) {
                    columnName = "nw";
                }

                if (columnNumber.equals("2")) {
                    columnName = "plmass";
                }

                if (columnNumber.equals("3")) {
                    columnName = "factmass";
                }

                if (columnNumber.equals("4")) {
                    columnName = "operfio";
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
            if (columnNumber.equals("0")) {
                columnName = "idpr";
            }

            if (columnNumber.equals("1")) {
                columnName = "datecr";
            }

            if (columnNumber.equals("2")) {
                columnName = "datepl";
            }

            if (columnNumber.equals("3")) {
                columnName = "datemade";
            }

            if (columnNumber.equals("4")) {
                columnName = "brend";
            }

            if (columnNumber.equals("5")) {
                columnName = "nameprod";
            }

            if (columnNumber.equals("6")) {
                columnName = "sp";
            }

            if (columnNumber.equals("7")) {
                columnName = "percent";
            }

            if (columnNumber.equals("8")) {
                columnName = "mass";
            }

            return columnName;
        } catch (Exception e) {
            log.error("Error getColumnNameProductTable : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
            return null;
        }
    }
}
