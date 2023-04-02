package com.insurance.imt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.imt.Exceptions.DataNotFoundException;
import com.insurance.imt.Exceptions.NullInputDataException;
import com.insurance.imt.model.Claim;
import com.insurance.imt.service.ClaimService;

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

@RestController
@RequestMapping("api/claims")
public class ClaimController {

    @Autowired
    ClaimService claimService;

    @GetMapping()
    public ResponseEntity<List<Claim>> getAllClaims() {

        List<Claim> allClaims = claimService.getAllClaims();
        return new ResponseEntity<List<Claim>>(allClaims, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable("id") Long id) {

        if (id == null) {
            throw new NullInputDataException();
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(claimService.getClaimById(id).orElseThrow(DataNotFoundException::new));
        }

    }

    @PostMapping
    public ResponseEntity<Claim> addClaim(@RequestBody Claim claim) {

        Claim savedClaim = claimService.addClaim(claim);
        return new ResponseEntity<Claim>(savedClaim, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Claim> updateClaim(@PathVariable("id") Long id, @RequestBody Claim claim) {
        Claim updatedClaim = claimService.updateClaim(id, claim);
        return new ResponseEntity<Claim>(updatedClaim, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClaim(@PathVariable("id") Long id) {

        claimService.deleteClaim(id);
        return new ResponseEntity<String>("Client Deleted", HttpStatus.OK);
    }

}
