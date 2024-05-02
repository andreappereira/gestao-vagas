package com.andreappereira.main.modules.job.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreappereira.main.modules.job.JobEntity;
import com.andreappereira.main.modules.job.repositories.JobRepository;


@Service
public class ListAllJobsByFilterUseCase {
    @Autowired
    JobRepository jobRepository;

    public List<JobEntity> handleFilter(String search) {
        return this.jobRepository.findByDescriptionContainingIgnoreCase(search);
    }
}
