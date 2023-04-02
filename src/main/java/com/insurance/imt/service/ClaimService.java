package com.insurance.imt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.insurance.imt.Exceptions.DataNotFoundException;
import com.insurance.imt.Exceptions.NullInputDataException;
import com.insurance.imt.model.Claim;
import com.insurance.imt.repository.ClaimRepository;

@Service
public class ClaimService {

    @Autowired
    ClaimRepository claimRepository;

    public List<Claim> getAllClaims() {

        List<Claim> allClaims = claimRepository.findAll();
        System.out.println(allClaims.toString());
        return allClaims;
    }

    public Optional<Claim> getClaimById(@PathVariable("id") Long id) {

        if (id == null) {
            throw new NullInputDataException();
        }

        Optional<Claim> claim = claimRepository.findById(id);
        return claim;

    }

    public Claim addClaim(@RequestBody Claim claim) {

        if (claim == null) {
            throw new NullInputDataException();
        } else {
            Claim savedClaim = claimRepository.save(claim);
            return savedClaim;
        }

    }

    public Claim updateClaim(@PathVariable("id") Long id, @RequestBody Claim claim) {

        if (claim == null) {
            throw new NullInputDataException();
        } else {
            Claim claimFromDB = claimRepository.findById(id).orElseThrow(DataNotFoundException::new);
            claim.setId(claimFromDB.getId());
            Claim updatedClaim = claimRepository.save(claim);
            return updatedClaim;

        }

    }

    public void deleteClaim(@PathVariable("id") Long id) {

        claimRepository.deleteById(id);
        return;

    }

}
