package com.companyms.company;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double rating;

    // @JsonIgnore
    // @OneToMany(mappedBy = "company")
    // private List<Job> jobs;

    // @OneToMany(mappedBy = "company")
    // private List<Review> reviews;

    public Company() {
    }

    // public List<Review> getReviews() {
    //     return reviews;
    // }

    // public void setReviews(List<Review> reviews) {
    //     this.reviews = reviews;
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    // public List<Job> getJobs() {
    //     return jobs;
    // }

    // public void setJobs(List<Job> jobs) {
    //     this.jobs = jobs;
    // }
}
