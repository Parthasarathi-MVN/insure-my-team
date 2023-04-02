package com.insurance.imt.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class InsurancePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Policy Number cannot not be blank")
    @Size(min = 12, max = 12, message = "Policy Number must be of 12 numbers")
    private String policyNumber;

    @NotBlank(message = "Policy Type cannot not be blank")
    private String policyType;

    @NotBlank(message = "Coverage Amount cannot not be blank")
    private Double coverageAmount;

    @NotBlank(message = "Premium Amount cannot not be blank")
    private Double premium;

    @NotBlank(message = "Start Date cannot not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @NotBlank(message = "End Date cannot not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    @OneToOne(mappedBy = "insurancePolicy")
    private Claim claim;

    @ManyToOne()
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public Double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(Double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }

    public InsurancePolicy() {
    }

    public InsurancePolicy(Long id,
            @NotBlank(message = "Policy Number cannot not be blank") @Size(min = 12, max = 12, message = "Policy Number must be of 12 numbers") String policyNumber,
            @NotBlank(message = "Policy Type cannot not be blank") String policyType,
            @NotBlank(message = "Coverage Amount cannot not be blank") Double coverageAmount,
            @NotBlank(message = "Premium Amount cannot not be blank") Double premium,
            @NotBlank(message = "Start Date cannot not be blank") LocalDate startDate,
            @NotBlank(message = "End Date cannot not be blank") LocalDate endDate, Claim claim, Client client) {
        this.id = id;
        this.policyNumber = policyNumber;
        this.policyType = policyType;
        this.coverageAmount = coverageAmount;
        this.premium = premium;
        this.startDate = startDate;
        this.endDate = endDate;
        this.claim = claim;
        this.client = client;
    }

    @Override
    public String toString() {
        return "InsurancePolicy [id=" + id + ", policyNumber=" + policyNumber + ", policyType=" + policyType
                + ", coverageAmount=" + coverageAmount + ", premium=" + premium + ", startDate=" + startDate
                + ", endDate=" + endDate + ", claim=" + claim + ", client=" + client + "]";
    }

}
