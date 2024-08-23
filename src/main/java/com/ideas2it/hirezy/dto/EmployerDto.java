package com.ideas2it.hirezy.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmployerDto {
    private Long Id;

    private String companyName;

    private String description;

    private String industryType;

    private String companyType;
}