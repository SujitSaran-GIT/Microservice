package com.jobms.job.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jobms.job.Job;
import com.jobms.job.JobRepository;
import com.jobms.job.JobService;
import com.jobms.job.dto.JobCompanyDTO;
import com.jobms.job.external.Company;
import com.jobms.job.external.Review;
import com.jobms.job.mapper.JobMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    // private List<Job> jobs = new ArrayList<>();
    @Autowired
    JobRepository jobRepository;
    // private Long nextId = 1l;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<JobCompanyDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobCompanyDTO> jobCompanyDTOs = new ArrayList<>();

        for (Job job : jobs) {
            JobCompanyDTO jobCompanyDTO;
            try {
                Company company = restTemplate.getForObject(
                        "http://COMPANYMS:8082/company/" + job.getCompanyId(),
                        Company.class);
                
                ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEWMS:8084/reviews?companyId="+job.getCompanyId(),HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {});

                List<Review> reviews = reviewResponse.getBody();

                
                jobCompanyDTO = JobMapper.mapToJobCompanyDTO(job, company, reviews);
                // jobCompanyDTO.setCompany(company);
            } catch (Exception e) {
                System.err.println("Error fetching company for job ID " + job.getId() + ": " + e.getMessage());
                jobCompanyDTO = JobMapper.mapToJobCompanyDTO(job, null, null);
            }

            jobCompanyDTOs.add(jobCompanyDTO);
        }

        return jobCompanyDTOs;
    }

    @Override
    public void createJob(Job job) {
        // job.setId(nextId++);
        // jobs.add(job);
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        // for (Job job : jobs){
        // if (job.getId().equals(id)){
        // return job;
        // }
        // }
        // return null;

        Optional<Job> jobOptional = jobRepository.findById(id);

        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            System.out.println(job.getTitle());

            try {
                Long companyId = job.getCompanyId();

                String companyUrl = "http://localhost:8082/company/" + companyId;

                Company company = restTemplate.getForObject(companyUrl, Company.class);

                if (company != null) {
                    System.out.println("Companyms: " + company.getName());
                } else {
                    System.out.println("Company not found for ID: " + companyId);
                }
            } catch (Exception e) {
                System.out.println("Error calling company service: " + e.getMessage());
            }
        }

        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        // Iterator<Job> iterator = jobs.iterator();
        // while (iterator.hasNext()){
        // Job job = iterator.next();
        // if (job.getId().equals(id)){
        // iterator.remove();
        // return true;
        // }
        // }
        // return false;
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        // Iterator<Job> iterator = jobs.iterator();
        // while(iterator.hasNext()){
        // Job job = iterator.next();
        // if (job.getId().equals(id)){
        // job.setTitle(updatedJob.getTitle());
        // job.setDescription(updatedJob.getDescription());
        // job.setMaxSalary(updatedJob.getMaxSalary());
        // job.setMinSalary(updatedJob.getMinSalary());
        // job.setLocation(updatedJob.getLocation());
        //
        // return true;
        // }
        // }
        // return false;

        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());

            jobRepository.save(job);
            return true;
        }
        return false;
    }

}
