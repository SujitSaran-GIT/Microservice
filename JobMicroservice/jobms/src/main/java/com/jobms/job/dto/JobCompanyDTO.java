package com.jobms.job.dto;

import com.jobms.job.Job;
import com.jobms.job.external.Company;

public class JobCompanyDTO {
    private Job job;
    private Company company;

    public Job getJob() {
        return job;
    }
    public void setJob(Job job) {
        this.job = job;
    }
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company companies) {
        this.company = companies;
    }

    
}
