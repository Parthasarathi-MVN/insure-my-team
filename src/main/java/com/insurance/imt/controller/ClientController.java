package com.insurance.imt.controller;

import java.util.List;
import java.util.Optional;

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
import com.insurance.imt.model.Client;
import com.insurance.imt.service.ClientService;

@RestController
@RequestMapping("api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/test")
    public String testMethod() {
        return "YES";
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {

        List<Client> allClients = clientService.getAllClients();
        return new ResponseEntity<List<Client>>(allClients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {

        Optional<Client> client = clientService.getClientById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(client.orElseThrow(DataNotFoundException::new));

    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        Client savedClient = clientService.addClient(client);
        return new ResponseEntity<Client>(savedClient, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody Client client) {

        Client updatedClient = clientService.updateClient(id, client);
        return new ResponseEntity<Client>(updatedClient, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Long id) {

        clientService.deleteClient(id);
        return new ResponseEntity<String>("Client Deleted", HttpStatus.OK);

    }

}
