package com.ideas2it.hirezy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JobSubCategoryDto {
    private Long id;
    private String name;
    private Long jobCategoryId;
}
