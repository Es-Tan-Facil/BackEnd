package esTanFacil.backend.controller;
import esTanFacil.backend.model.CDonations;
import esTanFacil.backend.service.CServiceDonation;
import esTanFacil.backend.service.CServiceKm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CDonationsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CServiceDonation cServiceDonation;

    @Mock
    private CServiceKm cServiceKm;

    @InjectMocks
    private CDonationsController cDonationsController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cDonationsController).build();
    }

    @Test
    public void testReadDonation() throws Exception {
        // Arrange
        List<CDonations> donationsList = Collections.singletonList(new CDonations());
        when(cServiceDonation.readDonation()).thenReturn(donationsList);

        // Act & Assert
        mockMvc.perform(get("/api/v1/donations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$.length()").value(1));

        // Verify that the service method was called
        verify(cServiceDonation).readDonation();
    }

    @Test
    public void testReadDonationId() throws Exception {
        // Arrange
        Long id = 1L;
        Optional<CDonations> donation = Optional.of(new CDonations());
        when(cServiceDonation.readDonationId(anyLong())).thenReturn(donation);

        // Act & Assert
        mockMvc.perform(get("/api/v1/donations/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());

        // Verify that the service method was called
        verify(cServiceDonation).readDonationId(id);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateDonation() throws Exception {
        // Arrange
        CDonations donation = new CDonations();

        // Act & Assert
        mockMvc.perform(post("/api/v1/donations")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isOk());

        // Verify that the service methods were called
        verify(cServiceDonation).createDonation(any(CDonations.class));
        verify(cServiceKm).updateTotalKmDonated();
    }


}
