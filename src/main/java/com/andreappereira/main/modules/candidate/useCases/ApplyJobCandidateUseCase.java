package com.andreappereira.main.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andreappereira.main.exceptions.JobNotFoundExcepetion;
import com.andreappereira.main.exceptions.UserNotFoundException;
import com.andreappereira.main.modules.candidate.entities.ApplyJobEntity;
import com.andreappereira.main.modules.candidate.repositories.ApplyJobRepository;
import com.andreappereira.main.modules.candidate.repositories.CandidateRepository;
import com.andreappereira.main.modules.job.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {
    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    ApplyJobRepository applyJobRepository;


    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {

        this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        this.jobRepository.findById(idJob)
        .orElseThrow(() -> {
            throw new JobNotFoundExcepetion();
        });


        var applyJob = ApplyJobEntity.builder()
        .candidateId(idCandidate)
        .jobId(idJob)
        .build();

        applyJob = this.applyJobRepository.save(applyJob);

        return applyJob;

    }

}
