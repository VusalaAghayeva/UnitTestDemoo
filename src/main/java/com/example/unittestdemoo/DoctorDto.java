package com.example.unittestdemoo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorDto {
    private String finCode;
    private String name;
    private String surname;
}
