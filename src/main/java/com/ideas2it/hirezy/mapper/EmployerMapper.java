package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.EmployerDto;
import com.ideas2it.hirezy.model.Employer;

/**
 * Mapper for converting between Employer entity and EmployerDTO.
 * @Author kishore
 */
public class EmployerMapper {
    public static Employer convertDtoToEntity(EmployerDto employerDto) {
        return Employer.builder()
                .name(employerDto.getName())
                .companyName(employerDto.getCompanyName())
                .description(employerDto.getDescription())
                .companyType(employerDto.getCompanyType())
                .industryType(employerDto.getIndustryType())
                .build();
    }
    public static EmployerDto convertEntityToDto(Employer employer) {
        return EmployerDto.builder()
                .Id(employer.getId())
                .name(employer.getName())
                .companyName(employer.getCompanyName())
                .description(employer.getDescription())
                .companyType(employer.getCompanyType())
                .industryType(employer.getIndustryType())
                .build();

    }
}
