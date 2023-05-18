package esTanFacil.backend.service;

import esTanFacil.backend.model.CKm;
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

public class CServiceKmTest {

    @Mock
    private IKm iKm;

    @Mock
    private CServiceDonation cServiceDonation;

    @InjectMocks
    private CServiceKm cServiceKm;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateTotalKmDonated_emptyList() {
        int totalKmDonated = 10;
        List<CKm> totalKmList = new ArrayList<>();
        when(cServiceDonation.sumKmDonated()).thenReturn(totalKmDonated);
        when(iKm.findAll()).thenReturn(totalKmList);

        cServiceKm.updateTotalKmDonated();

        verify(iKm, times(1)).save(any(CKm.class));
    }

    @Test
    public void testUpdateTotalKmDonated_existingList() {
        int totalKmDonated = 20;
        List<CKm> totalKmList = new ArrayList<>();
        totalKmList.add(new CKm(1L, 10));
        when(cServiceDonation.sumKmDonated()).thenReturn(totalKmDonated);
        when(iKm.findAll()).thenReturn(totalKmList);

        cServiceKm.updateTotalKmDonated();

        CKm updatedTotalKm = totalKmList.get(0);
        updatedTotalKm.setTotalKm(totalKmDonated);
        verify(iKm, times(1)).save(updatedTotalKm);
    }

    @Test
    public void testReadTotalKms() {
        int totalKm = 30;
        Optional<CKm> optionalCKm = Optional.of(new CKm(1L, totalKm));
        when(iKm.findById(1L)).thenReturn(optionalCKm);

        int result = cServiceKm.readTotalKms();

        assertEquals(totalKm, result);
    }
}