package esTanFacil.backend.model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CDonationsTest {

    @Test
    void getId() {
        CDonations donation = new CDonations(1L, "Juan Alberto", 5);

        Long donationId = donation.getId();

        assertNotNull(donationId);
        assertEquals(1L, donationId);
    }

    @Test
    void getName() {
        CDonations donation = new CDonations(1L, "Juan Alberto", 5);

        String donationName = donation.getName();

        assertNotNull(donationName);
        assertEquals("Juan Alberto", donationName);
    }

    @Test
    void getKmDonated() {
        CDonations donation = new CDonations(1L, "Juan Alberto", 5);

        int donationKM = donation.getKmDonated();

        assertNotNull(donationKM);
        assertEquals(5, donationKM);
    }
}