package com.insurance.imt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.imt.Exceptions.DataNotFoundException;
import com.insurance.imt.Exceptions.NullInputDataException;
import com.insurance.imt.model.InsurancePolicy;
import com.insurance.imt.service.InsurancePolicyService;

@RestController
@RequestMapping("api/policies")
public class InsurancePolicyController {

    @Autowired
    InsurancePolicyService policyService;

    @GetMapping()
    public ResponseEntity<List<InsurancePolicy>> getAllClaims() {

        List<InsurancePolicy> allPolicies = policyService.getAllPolicies();

        return new ResponseEntity<List<InsurancePolicy>>(allPolicies, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<InsurancePolicy> getPolicyById(@PathVariable("id") Long id) {
        if (id == null) {
            throw new NullInputDataException();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(policyService.getPolicyById(id).orElseThrow(DataNotFoundException::new));
        }
    }

    @PostMapping
    public ResponseEntity<InsurancePolicy> addClient(@RequestBody InsurancePolicy policy) {

        InsurancePolicy savedPolicy = policyService.addPolicy(policy);
        return new ResponseEntity<InsurancePolicy>(savedPolicy, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsurancePolicy> updateClient(@PathVariable("id") Long id,
            @RequestBody InsurancePolicy policy) {

        InsurancePolicy updatedPolicy = policyService.updatePolicy(id, policy);

        return new ResponseEntity<InsurancePolicy>(updatedPolicy, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePolicy(@PathVariable("id") Long id) {
        policyService.deletePolicy(id);
        return new ResponseEntity<String>("Policy Deleted", HttpStatus.OK);
    }

}
