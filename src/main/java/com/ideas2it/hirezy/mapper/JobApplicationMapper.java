package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.EmployeeDto;
import com.ideas2it.hirezy.dto.JobApplicationDto;
import com.ideas2it.hirezy.model.Employee;
import com.ideas2it.hirezy.model.JobApplication;
import com.ideas2it.hirezy.model.JobPost;

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
    public static JobApplication mapToJobApplication(JobApplicationDto jobApplicationDto, JobPost jobPost, Employee employee) {
        return JobApplication.builder()
                .id(jobApplicationDto.getId())
                .jobPost(jobPost)
                .employee(employee)
                .status(jobApplicationDto.getStatus())
                .appliedDate(jobApplicationDto.getAppliedDate()).build();
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
                .id(jobApplication.getId())
                .jobPostId(jobApplication.getJobPost().getId())
                .employeeId(jobApplication.getEmployee().getEmployeeId())
                .employee(EmployeeMapper.mapEntityToDto(jobApplication.getEmployee()))
                .status(jobApplication.getStatus())
                .AppliedDate(jobApplication.getAppliedDate()).build();
    }
}
