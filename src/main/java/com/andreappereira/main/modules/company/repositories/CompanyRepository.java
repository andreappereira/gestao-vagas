package com.andreappereira.main.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andreappereira.main.modules.company.CompanyEntity;


public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByUsernameOrDocument(String username, String document);

    Optional<CompanyEntity> findByUsername(String username);
}
