package com.insurance.imt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.imt.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
