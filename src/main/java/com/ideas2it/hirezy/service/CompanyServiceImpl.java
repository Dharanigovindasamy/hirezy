package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.CompanyDto;
import com.ideas2it.hirezy.mapper.CompanyMapper;
import com.ideas2it.hirezy.model.Company;
import com.ideas2it.hirezy.repository.CompanyRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    public static final Logger logger = LogManager.getLogger();
    @Autowired
    CompanyRepository companyRepository;
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = CompanyMapper.ConvertDtoToEntity(companyDto);
//        if(companyRepository.existsByCompanyName(company.getCompanyName())) {
//            logger.warn("trying to add a duplicate entry for an existing company");
//        }
        logger.info("Company has been successfully created");
        return CompanyMapper.ConvertEntityToDto(companyRepository.save(company));
    }
    public List<CompanyDto> getAllCompanies() {
        List<CompanyDto> result = new ArrayList<>();
        List<Company> companies = companyRepository.findByIsDeletedFalse();
        if(companies.isEmpty()) {
            logger.warn("companies are not present ");
        } else {
            for (Company company : companies) {
                result.add(CompanyMapper.ConvertEntityToDto(company));
            }
        }
        return result;
    }

    public void removeCompany(int id) {
        Company company = companyRepository.findByIsDeletedFalseAndId(id);
        company.setDeleted(true);
        companyRepository.save(company);
        logger.info("company has been removed of id ..{}",id);

    }

    public CompanyDto updateCompany(int id, CompanyDto companyDto)  {
        Company convertCompany = CompanyMapper.ConvertDtoToEntity(companyDto);
        Company existingCompany =  companyRepository.findByIsDeletedFalseAndId(id);
        existingCompany.setCompanyName(convertCompany.getCompanyName());
        logger.info("company details has been updated of id..{}",id);
        return CompanyMapper.ConvertEntityToDto(companyRepository.save(existingCompany));
    }

    public CompanyDto getCompanyById(int id) {
        Company company = companyRepository.findByIsDeletedFalseAndId(id);
//        if(null == company) {
//            throw new EmployeeException("Department Not found with Id : " + 1);
//        }
        return CompanyMapper.ConvertEntityToDto(company);
    }
}
