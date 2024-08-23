package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.CompanyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
     CompanyDto createCompany(CompanyDto companyDto);

     public List<CompanyDto> getAllCompanies();

     public void removeCompany(int id);

     public CompanyDto updateCompany(int id, CompanyDto companyDto);

     public CompanyDto getCompanyById(int id);
}
