package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.JobApplicationDto;
import com.ideas2it.hirezy.model.JobApplication;

/**
 * <p>
 *     This class can convert job application dto to entity and vice-versa
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */
public class JobApplicationMapper {

    /**
     * <p>
     *     This class used for converting job application information given from user
     *     and convert into job application entity
     * </p>
     *
     * @param jobApplicationDto - job Application Dto given from user
     * @return JobApplication - dto user data into entity form
     */
    public static JobApplication mapToJobApplication(JobApplicationDto jobApplicationDto) {
        return JobApplication.builder()
                .status(jobApplicationDto.getStatus())
                .AppliedDate(jobApplicationDto.getAppliedDate()).build();
    }

    /**
     * <p>
     *     This class used for converting job application entity from the table into the job application Dto the send user
     * </p>
     *
     * @param jobApplication - job application entity
     * @return JobApplicationDto - job Application Dto given to end user
     */
    public static  JobApplicationDto mapToJobApplicationDto(JobApplication jobApplication) {
        return JobApplicationDto.builder()
                .status(jobApplication.getStatus()).
                AppliedDate(jobApplication.getAppliedDate()).build();
    }
}
