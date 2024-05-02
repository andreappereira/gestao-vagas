package com.andreappereira.main.candidate.useCases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.andreappereira.main.exceptions.JobNotFoundExcepetion;
import com.andreappereira.main.exceptions.UserNotFoundException;
import com.andreappereira.main.modules.candidate.entities.ApplyJobEntity;
import com.andreappereira.main.modules.candidate.entities.CandidateEntity;
import com.andreappereira.main.modules.candidate.repositories.ApplyJobRepository;
import com.andreappereira.main.modules.candidate.repositories.CandidateRepository;
import com.andreappereira.main.modules.candidate.useCases.ApplyJobCandidateUseCase;
import com.andreappereira.main.modules.job.JobEntity;
import com.andreappereira.main.modules.job.repositories.JobRepository;



@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {
    
    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    CandidateRepository candidateRepository;

    @Mock
    JobRepository jobRepository;

    @Mock
    ApplyJobRepository applyJobRepository;
    
    @Test
    @DisplayName("Should not be able to apply for a job if candidate does not found")
    public void should_not_be_apply_job_with_candidate_not_found(){
        try{
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e){
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply for a job if job does not found")
    public void should_not_be_apply_job_with_job_not_found(){
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try{
            applyJobCandidateUseCase.execute(idCandidate, null);
        } catch (Exception e){
            assertThat(e).isInstanceOf(JobNotFoundExcepetion.class);
        }
    }

    @Test
    @DisplayName("Should be able to create a new apply job")
    public void should_be_able_to_create_a_new_apply_job() {
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder()
        .candidateId(idCandidate)
        .jobId(idJob)
        .build();

        var applyJobCreated = ApplyJobEntity.builder()
        .id(UUID.randomUUID())
        .build();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }
    
}
