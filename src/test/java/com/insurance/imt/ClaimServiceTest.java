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
import com.insurance.imt.model.InsurancePolicy;
import com.insurance.imt.repository.ClaimRepository;
import com.insurance.imt.service.ClaimService;

// @RunWith()
@SpringBootTest
public class ClaimServiceTest {

    @Autowired
    private ClaimService claimService;

    @MockBean
    private ClaimRepository claimRepository;

    @Test
    void testAddClaim() {

        Claim claim = new Claim(1L, "123456789012", "description", LocalDate.now(), true, new InsurancePolicy());
        when(claimRepository.save(claim)).thenReturn(claim);
        assertEquals(claim, claimService.addClaim(claim));

    }

    @Test
    void testDeleteClaim() {

        Claim claim = new Claim(1L, "123456789012", "description", LocalDate.now(), true, new InsurancePolicy());
        claimService.deleteClaim(1L);
        verify(claimRepository, times(1)).deleteById(claim.getId());

    }

    @Test
    void testGetAllClaims() {

        List<Claim> claimList = new ArrayList<>();
        claimList.add(
                new Claim(1L, "123456789012", "this is description", LocalDate.now(), false, new InsurancePolicy()));
        claimList.add(
                new Claim(2L, "654854756323", "this is description 2", LocalDate.now(), true, new InsurancePolicy()));
        claimList.add(
                new Claim(2L, "123657459856", "this is description 3", LocalDate.now(), false, new InsurancePolicy()));

        when(claimRepository.findAll()).thenReturn(claimList);

        assertEquals(3, claimService.getAllClaims().size());

    }

    @Test
    void testGetClaimById() {

        Claim claim = new Claim(1L, "123456789012", "this is description", LocalDate.now(), true,
                new InsurancePolicy());

        when(claimRepository.findById(1L))
                .thenReturn(Optional.of(new Claim(1L, "123456789012", "this is description", LocalDate.now(), true,
                        new InsurancePolicy())));

        assertEquals(Optional.of(claim).toString(), claimService.getClaimById(1L).toString());

    }

    @Test
    void testUpdateClaim() {

        Claim claimFromDB = new Claim();

        claimFromDB.setId(1L);
        when(claimRepository.findById(1L)).thenReturn(Optional.of(claimFromDB));

        Claim claim = new Claim(1L, "123456789055", "description", LocalDate.now(), true, new InsurancePolicy());
        when(claimRepository.save(claim)).thenReturn(claim);

        assertEquals(claim, claimService.updateClaim(1L, claim));

    }
}
