package com.new_ton.domain.dto.productionpage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalibrationTableDto {
    private int id;
    private String date;
    private String nw;
    private String plmass;
    private String factmass;
    private String operfio;
}
