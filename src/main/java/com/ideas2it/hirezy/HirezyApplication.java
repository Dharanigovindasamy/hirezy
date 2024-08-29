package com.ideas2it.hirezy;

import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ideas2it.hirezy.config.UserAuthentication.AuthenticationService;
import com.ideas2it.hirezy.controller.JobCategoryController;
import com.ideas2it.hirezy.service.RoleService;
/**
 * <p>
 *     This is the main class which starts the application and initiate the users
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */
@SpringBootApplication
public class HirezyApplication {
	private static final Logger logger = LogManager.getLogger(JobCategoryController.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private AuthenticationService authenticationService;

	@PostConstruct
	public void initUsers() {
		roleService.addRoles();
		authenticationService.registerAdmin();
	}
	public static void main(String[] args) {

		SpringApplication.run(HirezyApplication.class, args);
		logger.info("Application Started");
	}

}
