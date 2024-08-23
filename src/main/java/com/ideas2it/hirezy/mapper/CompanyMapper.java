package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.CompanyDto;
import com.ideas2it.hirezy.model.Company;
import com.ideas2it.hirezy.model.User;
import org.modelmapper.ModelMapper;

public class CompanyMapper {
    Company company = new Company();
    CompanyDto companyDto = new CompanyDto();

    public static Company ConvertDtoToEntity(CompanyDto companyDto) {
        return Company.builder()
                .companyName(companyDto.getCompanyName())
                .description(companyDto.getDescription())
                .location(companyDto.getLocation())
                .website(companyDto.getWebsite())
                .build();
    }
    public static CompanyDto ConvertEntityToDto(Company company) {
        return CompanyDto.builder()
                .Id(company.getId())
                .companyName(company.getCompanyName())
                .description(company.getDescription())
                .location(company.getLocation())
                .website(company.getWebsite())
                .build();

    }
//    public static Company ConvertDtoToEntity(CompanyDto companyDto) {
//        return Company.builder().build();
//    }
}
