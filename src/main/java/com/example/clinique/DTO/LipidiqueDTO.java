package com.example.clinique.DTO;

import lombok.Data;

@Data
public class LipidiqueDTO {
    private Long id;
    private double cholesterol;
    private double triglycerides;
    private double hdl;
    private double ldl;
    private double vldl;

}
