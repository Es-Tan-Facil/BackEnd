package esTanFacil.backend.model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CKmTest {

    @Test
    void getId() {
        CKm totalKm = new CKm(1L,60);

        Long totalKmId = totalKm.getId();

        assertNotNull(totalKmId);
        assertEquals(1L, totalKmId);
    }

    @Test
    void getTotalKm() {
        CKm totalKm = new CKm(1L,60);

        int totalKmNumber = totalKm.getTotalKm();

        assertNotNull(totalKmNumber);
        assertEquals(60, totalKmNumber);
    }
}