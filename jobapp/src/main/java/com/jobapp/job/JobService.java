package com.jobapp.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    Job createJob(Job job);
    Job getJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJobById(Long id, Job updatedJob);
}
