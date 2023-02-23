package com.new_ton.domain.dto.accountmanager;

import lombok.Data;


@Data
public class CateqDto {
    private int id;
    private String eq;
    private Integer code;

    public CateqDto(int id, String eq, Integer code) {
        this.id = id;
        this.eq = eq;
        this.code = code;
    }
}
