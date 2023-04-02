package com.insurance.imt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.insurance.imt.Exceptions.NullInputDataException;
import com.insurance.imt.model.InsurancePolicy;
import com.insurance.imt.repository.InsurancePolicyRepository;

@Service
public class InsurancePolicyService {

    @Autowired
    InsurancePolicyRepository policyRepository;

    public List<InsurancePolicy> getAllPolicies() {

        List<InsurancePolicy> allPolicies = policyRepository.findAll();
        return allPolicies;
    }

    public Optional<InsurancePolicy> getPolicyById(@PathVariable("id") Long id) {

        if (id == null) {
            throw new NullInputDataException();
        }
        Optional<InsurancePolicy> policy = policyRepository.findById(id);
        return policy;

    }

    public InsurancePolicy addPolicy(@RequestBody InsurancePolicy policy) {

        if (policy == null) {
            throw new NullInputDataException();
        } else {
            InsurancePolicy savedPolicy = policyRepository.save(policy);
            return savedPolicy;
        }
    }

    public InsurancePolicy updatePolicy(@PathVariable("id") Long id,
            @RequestBody InsurancePolicy policy) {

        if (policy == null) {
            throw new NullInputDataException();
        } else {
            InsurancePolicy policyFromDB = policyRepository.findById(id).orElse(null);
            policy.setId(policyFromDB.getId());
            InsurancePolicy updatedPolicy = policyRepository.save(policy);
            return updatedPolicy;
        }

    }

    public void deletePolicy(@PathVariable("id") Long id) {

        policyRepository.deleteById(id);
        return;

    }

}
