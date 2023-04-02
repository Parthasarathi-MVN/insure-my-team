package com.insurance.imt.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name cannot not be blank")
    @Size(min = 1, max = 15, message = "Name must be between 1-15 characters")
    private String name;

    @NotBlank(message = "Date of Birth cannot not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Address cannot not be blank")
    @Size(max = 50)
    private String address;

    @NotBlank(message = "Contact Number cannot not be blank")
    @Size(min = 10, max = 10, message = "Contact Number must be of 10 numbers")
    // @Pattern(regexp = )
    private String contactNumber;

    @OneToMany(mappedBy = "client")
    private List<InsurancePolicy> insurancePolicies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<InsurancePolicy> getInsurancePolicies() {
        return insurancePolicies;
    }

    public void setInsurancePolicies(List<InsurancePolicy> insurancePolicies) {
        this.insurancePolicies = insurancePolicies;
    }

    public Client() {
    }

    public Client(long id,
            @NotBlank(message = "Name cannot not be blank") @Size(min = 1, max = 15, message = "Name must be between 1-15 characters") String name,
            @NotBlank(message = "Date of Birth cannot not be blank") LocalDate dateOfBirth,
            @NotBlank(message = "Address cannot not be blank") @Size(max = 50) String address,
            @NotBlank(message = "Contact Number cannot not be blank") @Size(min = 10, max = 10, message = "Contact Number must be of 10 numbers") String contactNumber,
            List<InsurancePolicy> insurancePolicies) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.contactNumber = contactNumber;
        this.insurancePolicies = insurancePolicies;
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", address=" + address
                + ", contactNumber=" + contactNumber + ", insurancePolicies=" + insurancePolicies + "]";
    }

}
