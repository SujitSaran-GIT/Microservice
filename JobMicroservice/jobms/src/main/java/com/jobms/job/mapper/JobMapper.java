package com.jobms.job.mapper;

import java.util.List;

import com.jobms.job.Job;
import com.jobms.job.dto.JobCompanyDTO;
import com.jobms.job.external.Company;
import com.jobms.job.external.Review;

public class JobMapper {
    
    public static JobCompanyDTO mapToJobCompanyDTO(
        Job job,
        Company company,
        List<Review> reviews
    ){
        JobCompanyDTO jobCompanyDTO = new JobCompanyDTO();
        jobCompanyDTO.setId(job.getId());
        jobCompanyDTO.setTitle(job.getTitle());
        jobCompanyDTO.setDescription(job.getDescription());
        jobCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobCompanyDTO.setMinSalary(job.getMinSalary());
        jobCompanyDTO.setLocation(job.getLocation());
        jobCompanyDTO.setCompany(company);
        jobCompanyDTO.setReview(reviews);

        return jobCompanyDTO;
    }
}
