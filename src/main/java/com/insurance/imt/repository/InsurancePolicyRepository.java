package com.insurance.imt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.imt.model.InsurancePolicy;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {

}
