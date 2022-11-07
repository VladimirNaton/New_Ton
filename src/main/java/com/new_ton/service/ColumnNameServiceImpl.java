package com.new_ton.service;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ColumnNameServiceImpl implements ColumnNameService {
    private static final Logger log = LoggerFactory.getLogger(ColumnNameServiceImpl.class);

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
        } catch (Exception var3) {
            log.error("Error getColumnNameCalibrationTable : {}, {}", ExceptionUtils.getMessage(var3), ExceptionUtils.getMessage(var3.getCause()));
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
        } catch (Exception var3) {
            log.error("Error getColumnNameProductTable : {}, {}", ExceptionUtils.getMessage(var3), ExceptionUtils.getMessage(var3.getCause()));
            return null;
        }
    }
}
