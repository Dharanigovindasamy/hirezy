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
    public ResponseEntity<Map<String,Long>>getCounts() {
        Long employerCount = employerService.countEmployers();
        Long employeeCount = employeeService.countEmployees();

        Map<String,Long> counts = new HashMap<>();
        counts.put(("employerCount"),employerCount);
        counts.put(("employeeCount"),employeeCount);
        return ResponseEntity.ok(counts);
    }
}
