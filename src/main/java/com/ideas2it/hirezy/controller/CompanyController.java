package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.CompanyDto;
import com.ideas2it.hirezy.service.CompanyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/companies")
public class CompanyController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private CompanyService companyService;

    /**
     * <p>
     *This method is used to create a company into the repository
     * </p>
     */
    @PostMapping()
    public ResponseEntity<CompanyDto> addCompany( @RequestBody CompanyDto companyDto){
        CompanyDto savedCompany = companyService.createCompany(companyDto);
        return new ResponseEntity<>((savedCompany), HttpStatus.CREATED);
    }/**
     * <p>
     *This method is used to show all the company in the repository
     * </p>
     */
    @GetMapping()
    public ResponseEntity<List<CompanyDto>>  DisplayAllCompanies() {
        List<CompanyDto> companies  = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);

    }
    /**
     * <p>
     *This method is used to show a specific company in the repository
     * @param companyId - unique identifier of the company
     * </p>
     */
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> displayCompany(@PathVariable("id") int companyId) {
        CompanyDto showcompanyDto = companyService.getCompanyById(companyId);
        return new ResponseEntity<>((showcompanyDto),HttpStatus.OK);
    }
    /**
     * <p>
     *This method is used to create a company into the repository
     * @param companyId -unique identifier of company
     * </p>
     */
    @PutMapping("{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable("id") int companyId,@RequestBody CompanyDto companyDto) {
        CompanyDto updateCompanyDto =  companyService.updateCompany(companyId,companyDto);
        logger.info("the details of the company have been updated of id..{}",companyId);
        return new ResponseEntity<>((updateCompanyDto),HttpStatus.OK);
    }
    /**
     * <p>
     *This method is used to delete a company from the repository
     * @param companyId
     * </p>
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") int companyId) {
        companyService.removeCompany(companyId);
        logger.info("company of this id  has been deleted..{}",companyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
