package com.andreappereira.main.modules.job.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreappereira.main.modules.job.JobEntity;
import com.andreappereira.main.modules.job.repositories.JobRepository;

@Service
public class CreateJobUseCase {
    @Autowired
    private JobRepository jobRepository;

    public JobEntity handleCreate(JobEntity job) {
        return this.jobRepository.save(job);
    }
}
