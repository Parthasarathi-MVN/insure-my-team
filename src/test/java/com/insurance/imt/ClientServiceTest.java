package com.insurance.imt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.insurance.imt.model.Client;
import com.insurance.imt.model.InsurancePolicy;
import com.insurance.imt.repository.ClientRepository;
import com.insurance.imt.service.ClientService;

@SpringBootTest
public class ClientServiceTest {

        @Autowired
        private ClientService clientService;

        @MockBean
        private ClientRepository clientRepository;

        @Test
        void testAddClient() {

                Client client = new Client(1L, "TestName", LocalDate.now(), "Bangalore", "9876543234",
                                new ArrayList<InsurancePolicy>());
                when(clientRepository.save(client)).thenReturn(client);
                assertEquals(client, clientService.addClient(client));

        }

        @Test
        void testDeleteClient() {

                Client client = new Client(1L, "TestName", LocalDate.now(), "Bangalore", "9876543234",
                                new ArrayList<InsurancePolicy>());
                clientService.deleteClient(1L);
                verify(clientRepository, times(1)).deleteById(client.getId());

        }

        @Test
        void testGetAllClients() {

                List<Client> clientList = new ArrayList<>();
                clientList.add(new Client(1L, "TestName", LocalDate.now(), "Bangalore", "9876543234",
                                new ArrayList<InsurancePolicy>()));
                clientList.add(new Client(2L, "TestName2", LocalDate.now(), "Hyderabad", "5423658745",
                                new ArrayList<InsurancePolicy>()));
                clientList.add(new Client(3L, "TestName3", LocalDate.now(), "Mysore", "1236545789",
                                new ArrayList<InsurancePolicy>()));

                when(clientRepository.findAll()).thenReturn(clientList);

                assertEquals(3, clientService.getAllClients().size());

        }

        @Test
        void testGetClientById() {

                Client client = new Client(3L, "TestName3", LocalDate.now(), "Mysore", "1236545789",
                                new ArrayList<InsurancePolicy>());

                when(clientRepository.findById(3L))
                                .thenReturn(Optional
                                                .of(new Client(3L, "TestName3", LocalDate.now(), "Mysore", "1236545789",
                                                                new ArrayList<InsurancePolicy>())));

                assertEquals(Optional.of(client).toString(), clientService.getClientById(3L).toString());

        }

        @Test
        void testUpdateClient() {

                Client clientFromDB = new Client();

                clientFromDB.setId(1L);
                when(clientRepository.findById(1L)).thenReturn(Optional.of(clientFromDB));

                Client client = new Client(1L, "TestName", LocalDate.now(), "Bangalore", "9876543234",
                                new ArrayList<InsurancePolicy>());
                when(clientRepository.save(client)).thenReturn(client);

                assertEquals(client, clientService.updateClient(1L, client));

        }
}
