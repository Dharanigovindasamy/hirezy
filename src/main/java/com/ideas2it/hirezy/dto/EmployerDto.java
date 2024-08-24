package com.ideas2it.hirezy.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployerDto {
    private Long Id;

    private String name;

    private String companyName;

    private String description;

    private String industryType;

    private String companyType;
}