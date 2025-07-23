package com.jobms.job;

import java.util.List;

import com.jobms.job.dto.JobCompanyDTO;

public interface JobService {
    List<JobCompanyDTO> findAll();
    void createJob(Job job);

    Job getJobById(Long id);
    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
