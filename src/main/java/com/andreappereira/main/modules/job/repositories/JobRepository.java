package com.andreappereira.main.modules.job.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andreappereira.main.modules.job.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    
}
