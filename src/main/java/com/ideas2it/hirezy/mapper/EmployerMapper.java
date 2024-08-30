package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.EmployerDto;
import com.ideas2it.hirezy.model.Employer;
import com.ideas2it.hirezy.model.User;

/**
 * Mapper for converting between Employer entity and EmployerDTO.
 * @author kishore
 */
public class EmployerMapper {

    /**
     * <p>
     *     This method used for convert employer Dto to entity
     * </p>
     * @param employerDto -{@link EmployerDto} employerDto send from user
     * @param user - user object enter from user
     * @return Employer - employer detail to the user
     */
    public static Employer convertDtoToEntity(EmployerDto employerDto, User user) {
        return Employer.builder()
                .name(employerDto.getName())
                .companyName(employerDto.getCompanyName())
                .description(employerDto.getDescription()).companyType(employerDto.getCompanyType())
                .industryType(employerDto.getIndustryType())
                .user(user)
                .gender(employerDto.getGender())
                .build();
    }

    /**
     * <p>
     *     This method used for convert employer entity to Dto
     * </p>
     * @param employer - employer details receive from user
     * @return EmployerDto - employer detail to the user
     */
    public static EmployerDto convertEntityToDto(Employer employer) {
        return EmployerDto.builder()
                .Id(employer.getId())
                .name(employer.getName())
                .companyName(employer.getCompanyName())
                .description(employer.getDescription())
                .companyType(employer.getCompanyType())
                .industryType(employer.getIndustryType())
                .userId(employer.getUser().getId())
                .gender(employer.getGender())
                .build();
    }

    /**
     * <p>
     *     This method used for convert employer Dto to entity
     * </p>
     * @param employerDto -{@link EmployerDto} employerDto send from user
     * @return Employer - employer detail to the user
     */
    public static Employer convertDtoToEntity(EmployerDto employerDto) {
        return Employer.builder()
                .name(employerDto.getName())
                .companyName(employerDto.getCompanyName())
                .description(employerDto.getDescription())
                .companyType(employerDto.getCompanyType())
                .industryType(employerDto.getIndustryType())
                .gender(employerDto.getGender())
                .build();
    }
}
