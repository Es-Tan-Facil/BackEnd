package esTanFacil.backend.security.services;

import esTanFacil.backend.model.User;
import esTanFacil.backend.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void testLoadUserByUsername_ExistingUser_ReturnsUserDetails() {
        // Mock de UserRepository
        User user = new User();
        user.setUsername("john_doe");
        user.setPassword("password");

        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.of(user));

        // Llamar al método del servicio
        UserDetails userDetails = userDetailsService.loadUserByUsername("john_doe");

        // Verificar los detalles del UserDetails
        assertNotNull(userDetails);
        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());

        verify(userRepository, times(1)).findByUsername("john_doe");
    }

    @Test
    void testLoadUserByUsername_NonExistingUser_ThrowsUsernameNotFoundException() {
        // Mock de UserRepository
        when(userRepository.findByUsername("non_existing_user")).thenReturn(Optional.empty());

        // Llamar al método del servicio y verificar que se arroje UsernameNotFoundException
        assertThrows(UsernameNotFoundException.class, () ->
                userDetailsService.loadUserByUsername("non_existing_user"));

        verify(userRepository, times(1)).findByUsername("non_existing_user");
    }
}