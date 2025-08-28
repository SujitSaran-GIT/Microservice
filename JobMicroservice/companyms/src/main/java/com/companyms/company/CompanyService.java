package com.companyms.company;

import java.util.List;

import com.companyms.company.dto.ReviewMessage;

public interface CompanyService {
    void createCompany(Company company);
    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);
    boolean deleteCompany(Long id);
    Company getCompanyById(Long id);
    void updateCompanyRating(ReviewMessage reviewMessage);
}
