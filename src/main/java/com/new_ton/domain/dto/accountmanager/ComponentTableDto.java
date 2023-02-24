package com.new_ton.domain.dto.accountmanager;

import lombok.Data;

import java.util.Date;

@Data
public class ComponentTableDto {

    private Integer id;
    private Integer code1c;
    private String nameraw;
    private Integer code;
    private Integer part;
    private Date date;
    private String dateStr;

    public ComponentTableDto(Integer id, Integer code1c, String nameraw, Integer code, Integer part, Date date) {
        this.id = id;
        this.code1c = code1c;
        this.nameraw = nameraw;
        this.code = code;
        this.part = part;
        this.date = date;
    }
}
