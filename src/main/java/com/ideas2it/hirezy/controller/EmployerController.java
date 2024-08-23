package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.EmployerDto;
import com.ideas2it.hirezy.service.EmployerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/companies")
public class EmployerController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private EmployerService employerService;

    /**
     * <p>
     *This method is used to create a employer into the repository
     * </p>
     */
    @PostMapping()
    public ResponseEntity<EmployerDto> addEmployer(@RequestBody EmployerDto employerDto){
        EmployerDto savedEmployer = employerService.createCompany(employerDto);
        return new ResponseEntity<>((savedEmployer), HttpStatus.CREATED);
    }
    
    /**
     * <p>
     *This method is used to show all the employer in the repository
     * </p>
     */
    @GetMapping()
    public ResponseEntity<List<EmployerDto>>  DisplayAllCompanies() {
        List<EmployerDto> companies  = employerService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);

    }
    
    /**
     * <p>
     *This method is used to show a specific employer in the repository
     * @param employerId - unique identifier of the employer
     * </p>
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployerDto> displayEmployer(@PathVariable("id") int employerId) {
        EmployerDto showemployerDto = employerService.getCompanyById(employerId);
        return new ResponseEntity<>((showemployerDto),HttpStatus.OK);
    }
    
    /**
     * <p>
     *This method is used to create a employer into the repository
     * @param employerId -unique identifier of employer
     * </p>
     */
    @PutMapping("{id}")
    public ResponseEntity<EmployerDto> updateEmployer(@PathVariable("id") int employerId, @RequestBody EmployerDto employerDto) {
        EmployerDto updateEmployerDto =  employerService.updateCompany(employerId, employerDto);
        logger.info("the details of the company have been updated of id..{}",employerId);
        return new ResponseEntity<>((updateEmployerDto),HttpStatus.OK);
    }
    
    /**
     * <p>
     *This method is used to delete a employer from the repository
     * @param employerId
     * </p>
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable("id") int employerId) {
        employerService.removeCompany(employerId);
        logger.info("employer of this id  has been deleted..{}",employerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
