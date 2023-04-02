package com.insurance.imt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.insurance.imt.Exceptions.NullInputDataException;
import com.insurance.imt.model.Client;
import com.insurance.imt.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        List<Client> allClients = clientRepository.findAll();
        return allClients;

    }

    public Optional<Client> getClientById(@PathVariable("id") Long id) {

        if (id == null) {
            throw new NullInputDataException();
        }

        Optional<Client> client = clientRepository.findById(id);
        return client;

    }

    public Client addClient(@RequestBody Client client) {

        if (client == null) {
            throw new NullInputDataException();
        } else {
            Client savedClient = clientRepository.save(client);
            return savedClient;

        }

    }

    public Client updateClient(@PathVariable("id") Long id, @RequestBody Client client) {

        if (client == null) {
            throw new NullInputDataException();
        } else {
            Client clientFromDB = clientRepository.findById(id).orElse(null);
            client.setId(clientFromDB.getId());
            Client updatedClient = clientRepository.save(client);
            return updatedClient;

        }

    }

    public void deleteClient(@PathVariable("id") Long id) {

        clientRepository.deleteById(id);
        return;

    }

}
