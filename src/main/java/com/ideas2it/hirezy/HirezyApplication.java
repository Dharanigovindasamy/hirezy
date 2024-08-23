package com.ideas2it.hirezy;

import com.ideas2it.hirezy.controller.JobCategoryController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HirezyApplication {
	private static final Logger logger = LogManager.getLogger(JobCategoryController.class);

	public static void main(String[] args) {

		SpringApplication.run(HirezyApplication.class, args);
		logger.info("Application Started");
	}

}
