package com.new_ton.dao;

import java.util.List;

public interface PrintDao {
    List<Integer> getIdPrintTask();
    void changeCode(int id);
}
