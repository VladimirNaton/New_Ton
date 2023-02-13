package com.new_ton.domain.dto.technologistdto;

import lombok.Data;

import java.util.Date;

@Data
public class EditeRecipeComponentTableDto {
    private Integer id;
    private Date date;
    private String nameraw;
    private String dateStr;

    public EditeRecipeComponentTableDto(Integer id, Date date, String nameraw) {
        this.id = id;
        this.date = date;
        this.nameraw = nameraw;
    }
}
