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

import com.insurance.imt.model.Claim;
import com.insurance.imt.model.Client;
import com.insurance.imt.model.InsurancePolicy;
import com.insurance.imt.repository.InsurancePolicyRepository;
import com.insurance.imt.service.InsurancePolicyService;

@SpringBootTest
public class InsurancePolicyServiceTest {

    @Autowired
    private InsurancePolicyService policyService;

    @MockBean
    private InsurancePolicyRepository policyRepository;

    @Test
    void testAddClient() {

        InsurancePolicy policy = new InsurancePolicy(1L, "456789321456", "TestPolicy", 5000000.00, 25000.00,
                LocalDate.now(), LocalDate.now(), new Claim(), new Client());

        when(policyRepository.save(policy)).thenReturn(policy);
        assertEquals(policy, policyService.addPolicy(policy));

    }

    @Test
    void testDeletePolicy() {

        InsurancePolicy policy = new InsurancePolicy(1L, "456789321456", "TestPolicy", 5000000.00, 25000.00,
                LocalDate.now(), LocalDate.now(), new Claim(), new Client());
        policyService.deletePolicy(1L);
        verify(policyRepository, times(1)).deleteById(policy.getId());

    }

    @Test
    void testGetAllClaims() {

        List<InsurancePolicy> policyList = new ArrayList<>();

        policyList.add(new InsurancePolicy(1L, "456789321456", "TestPolicy", 5000000.00, 25000.00,
                LocalDate.now(), LocalDate.now(), new Claim(), new Client()));
        policyList.add(new InsurancePolicy(2L, "456321759854", "TestPolicy2", 75000000.00, 30000.00,
                LocalDate.now(), LocalDate.now(), new Claim(), new Client()));
        policyList.add(new InsurancePolicy(3L, "123987456321", "TestPolicy3", 3000000.00, 16000.00,
                LocalDate.now(), LocalDate.now(), new Claim(), new Client()));

        when(policyRepository.findAll()).thenReturn(policyList);

        assertEquals(3, policyService.getAllPolicies().size());

    }

    @Test
    void testGetPolicyById() {

        InsurancePolicy policy = new InsurancePolicy(3L, "123987456321", "TestPolicy3", 3000000.00, 16000.00,
                LocalDate.now(), LocalDate.now(), new Claim(), new Client());

        when(policyRepository.findById(3L))
                .thenReturn(Optional.of(new InsurancePolicy(3L, "123987456321", "TestPolicy3", 3000000.00, 16000.00,
                        LocalDate.now(), LocalDate.now(), new Claim(), new Client())));

        assertEquals(Optional.of(policy).toString(), policyService.getPolicyById(3L).toString());

    }

    @Test
    void testUpdateClient() {

        InsurancePolicy policyFromDB = new InsurancePolicy();

        policyFromDB.setId(1L);
        when(policyRepository.findById(1L)).thenReturn(Optional.of(policyFromDB));

        InsurancePolicy policy = new InsurancePolicy(1L, "456789321456", "TestPolicy", 5000000.00, 25000.00,
                LocalDate.now(), LocalDate.now(), new Claim(), new Client());
        when(policyRepository.save(policy)).thenReturn(policy);

        assertEquals(policy, policyService.updatePolicy(1L, policy));

    }
}
