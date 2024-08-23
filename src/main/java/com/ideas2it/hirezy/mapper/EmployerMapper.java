package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.EmployerDto;
import com.ideas2it.hirezy.model.Employer;

public class EmployerMapper {
    public static Employer ConvertDtoToEntity(EmployerDto employerDto) {
        return Employer.builder()
                .companyName(employerDto.getCompanyName())
                .description(employerDto.getDescription())
                .companyType(employerDto.getCompanyType())
                .industryType(employerDto.getIndustryType())
                .build();
    }
    public static EmployerDto ConvertEntityToDto(Employer employer) {
        return EmployerDto.builder()
                .Id(employer.getId())
                .companyName(employer.getCompanyName())
                .description(employer.getDescription())
                .companyType(employer.getCompanyType())
                .industryType(employer.getIndustryType())
                .build();

    }
}
