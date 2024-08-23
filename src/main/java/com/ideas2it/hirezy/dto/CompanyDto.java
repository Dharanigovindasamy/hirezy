package com.ideas2it.hirezy.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CompanyDto {
    private Long Id;

    private String companyName;

    private String description;

    private String location;

    private String website;
}