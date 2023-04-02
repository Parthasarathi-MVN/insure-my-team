package com.insurance.imt.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Claim Number cannot not be blank")
    @Size(min = 12, max = 12, message = "Claim Number must be of 12 numbers")
    private String claimNumber;

    @NotBlank(message = "Description cannot not be blank")
    @Size(max = 100, message = "Description Number must be of 100 characters")
    private String description;

    @NotBlank(message = "Claim Date cannot not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate claimDate;

    private Boolean claimStatus;

    @OneToOne
    private InsurancePolicy insurancePolicy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public Boolean getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(Boolean claimStatus) {
        this.claimStatus = claimStatus;
    }

    public InsurancePolicy getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }

    public Claim() {
    }

    public Claim(long id,
            @NotBlank(message = "Claim Number cannot not be blank") @Size(min = 12, max = 12, message = "Claim Number must be of 12 numbers") String claimNumber,
            @NotBlank(message = "Description cannot not be blank") @Size(max = 100, message = "Description Number must be of 100 characters") String description,
            @NotBlank(message = "Claim Date cannot not be blank") LocalDate claimDate, Boolean claimStatus,
            InsurancePolicy insurancePolicy) {
        this.id = id;
        this.claimNumber = claimNumber;
        this.description = description;
        this.claimDate = claimDate;
        this.claimStatus = claimStatus;
        this.insurancePolicy = insurancePolicy;
    }

    @Override
    public String toString() {
        return "Claim [id=" + id + ", claimNumber=" + claimNumber + ", description=" + description + ", claimDate="
                + claimDate + ", claimStatus=" + claimStatus + ", insurancePolicy=" + insurancePolicy + "]";
    }

}
