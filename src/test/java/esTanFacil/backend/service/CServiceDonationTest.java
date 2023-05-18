package esTanFacil.backend.service;

import esTanFacil.backend.model.CDonations;
import esTanFacil.backend.repositories.IDonations;
import esTanFacil.backend.repositories.IKm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CServiceDonationTest {

    @Mock
    private IDonations iDonations;

    @Mock
    private IKm iKm;

    @InjectMocks
    private CServiceDonation cServiceDonation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDonation() {
        CDonations donation = new CDonations();
        cServiceDonation.createDonation(donation);
        verify(iDonations, times(1)).save(donation);
    }

    @Test
    public void testReadDonation() {
        List<CDonations> donationsList = new ArrayList<>();
        when(iDonations.findAll()).thenReturn(donationsList);

        List<CDonations> result = cServiceDonation.readDonation();

        assertEquals(donationsList, result);
    }

    @Test
    public void testReadDonationId() {
        Long id = 1L;
        CDonations donation = new CDonations();
        when(iDonations.findById(id)).thenReturn(Optional.of(donation));

        Optional<CDonations> result = cServiceDonation.readDonationId(id);

        assertEquals(Optional.of(donation), result);
    }

    @Test
    public void testUpdateDonation() {
        Long id = 1L;
        CDonations donation = new CDonations();
        donation.setId(id);
        cServiceDonation.updateDonation(donation, id);

        verify(iDonations).save(donation);
    }

    @Test
    public void testDeleteDonation() {
        Long id = 1L;
        cServiceDonation.deleteDonation(id);

        verify(iDonations).deleteById(id);
    }

    @Test
    public void testSumKmDonated() {
        List<CDonations> donationsList = new ArrayList<>();
        CDonations donation1 = new CDonations();
        donation1.setKmDonated(10);
        donationsList.add(donation1);

        CDonations donation2 = new CDonations();
        donation2.setKmDonated(20);
        donationsList.add(donation2);

        when(cServiceDonation.readDonation()).thenReturn(donationsList);

        int result = cServiceDonation.sumKmDonated();

        assertEquals(30, result);
    }
}