package com.andreappereira.main.modules.job.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreappereira.main.exceptions.CompanyNotFoundException;
import com.andreappereira.main.modules.company.repositories.CompanyRepository;
import com.andreappereira.main.modules.job.JobEntity;
import com.andreappereira.main.modules.job.repositories.JobRepository;

@Service
public class CreateJobUseCase {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity handleCreate(JobEntity job) {
        this.companyRepository.findById(job.getCompanyId())
        .orElseThrow(() -> {
            throw new CompanyNotFoundException();
        });

        return this.jobRepository.save(job);
    }
}
