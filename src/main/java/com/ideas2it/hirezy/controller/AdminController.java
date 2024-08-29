package com.ideas2it.hirezy.controller;


import com.ideas2it.hirezy.service.EmployeeService;
import com.ideas2it.hirezy.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployerService employerService;

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String,Map<String,Long>>>getCounts() {
        Long acticveEmployerCount = employerService.countActiveEmployers();
        Long deletedEmployerCount = employerService.countDeletedEmployers();
        Long acticveEmployeeCount = employeeService.countActiveEmployees();
        Long deletedEmployeeCount = employeeService.countDeletedEmployees();

        Map<String,Long> employerCounts = new HashMap<>();
        employerCounts.put("active",acticveEmployerCount);
        employerCounts.put("deleted",deletedEmployerCount);
        Map<String,Long> employeeCounts = new HashMap<>();
        employeeCounts.put("active",acticveEmployeeCount);
        employeeCounts.put("deleted",deletedEmployeeCount);
        Map<String,Map<String,Long>> counts =new HashMap<>();
        counts.put("employer",employerCounts);
        counts.put("employee",employeeCounts);
        return ResponseEntity.ok(counts);
    }
}
