package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.CompanyDto;
import com.ideas2it.hirezy.model.Company;

public class CompanyMapper {
    public static Company ConvertDtoToEntity(CompanyDto companyDto) {
        return Company.builder()
                .companyName(companyDto.getCompanyName())
                .description(companyDto.getDescription())
                .companyType(companyDto.getCompanyType())
                .industryType(companyDto.getIndustryType())
                .build();
    }
    public static CompanyDto ConvertEntityToDto(Company company) {
        return CompanyDto.builder()
                .Id(company.getId())
                .companyName(company.getCompanyName())
                .description(company.getDescription())
                .companyType(company.getCompanyType())
                .industryType(company.getIndustryType())
                .build();

    }
}
