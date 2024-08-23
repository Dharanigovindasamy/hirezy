package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.EmployerDto;
import com.ideas2it.hirezy.mapper.EmployerMapper;
import com.ideas2it.hirezy.model.Employer;
import com.ideas2it.hirezy.repository.EmployerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {
    public static final Logger logger = LogManager.getLogger();
    @Autowired
    EmployerRepository employerRepository;
    public EmployerDto createCompany(EmployerDto employerDto) {
        Employer employer = EmployerMapper.ConvertDtoToEntity(employerDto);
//        if(companyRepository.existsByCompanyName(company.getCompanyName())) {
//            logger.warn("trying to add a duplicate entry for an existing company");
//        }
        logger.info("Company has been successfully created");
        return EmployerMapper.ConvertEntityToDto(employerRepository.save(employer));
    }
    public List<EmployerDto> getAllCompanies() {
        List<EmployerDto> result = new ArrayList<>();
        List<Employer> companies = employerRepository.findByIsDeletedFalse();
        if(companies.isEmpty()) {
            logger.warn("companies are not present ");
        } else {
            for (Employer employer : companies) {
                result.add(EmployerMapper.ConvertEntityToDto(employer));
            }
        }
        return result;
    }

    public void removeCompany(int id) {
        Employer employer = employerRepository.findByIsDeletedFalseAndId(id);
        employer.setDeleted(true);
        employerRepository.save(employer);
        logger.info("company has been removed of id ..{}",id);

    }

    public EmployerDto updateCompany(int id, EmployerDto employerDto)  {
        Employer convertEmployer = EmployerMapper.ConvertDtoToEntity(employerDto);
        Employer existingEmployer =  employerRepository.findByIsDeletedFalseAndId(id);
        existingEmployer.setCompanyName(convertEmployer.getCompanyName());
        logger.info("company details has been updated of id..{}",id);
        return EmployerMapper.ConvertEntityToDto(employerRepository.save(existingEmployer));
    }

    public EmployerDto getCompanyById(int id) {
        Employer employer = employerRepository.findByIsDeletedFalseAndId(id);
//        if(null == company) {
//            throw new EmployeeException("Department Not found with Id : " + 1);
//        }
        return EmployerMapper.ConvertEntityToDto(employer);
    }
}
