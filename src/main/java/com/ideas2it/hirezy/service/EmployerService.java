package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.EmployerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployerService {
     /**
      * This method will create a company in the portal
      * @param employerDto
      */
     EmployerDto createCompany(EmployerDto employerDto);

     /**
      *This method gets all the companies that are present
      */
     public List<EmployerDto> getAllCompanies();

     /**
      * This method is used to delete an company from the portal
      * @param id
      */
     public void removeCompany(int id);

     /**
      * This method is used to alter the company details in the portal
      * @param id
      * @param employerDto
      * @return
      */
     public EmployerDto updateCompany(int id, EmployerDto employerDto);

     /**
      * This method is used to get the company based on the id
      * @param id
      * @return
      */
     public EmployerDto getCompanyById(int id);
}
