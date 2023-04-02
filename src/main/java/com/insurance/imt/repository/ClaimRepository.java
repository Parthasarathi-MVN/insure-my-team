package com.insurance.imt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.imt.model.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

}
