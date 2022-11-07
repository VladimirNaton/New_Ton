package com.new_ton.domain.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DegreeOfGlossDto {
    private String nd;
    private String allvalues;
    private String dev;
    private String result;
}
