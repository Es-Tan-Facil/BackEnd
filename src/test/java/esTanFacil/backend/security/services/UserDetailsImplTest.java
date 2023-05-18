package esTanFacil.backend.security.services;


import esTanFacil.backend.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class UserDetailsImplTest {

    @Test
    public void testUserDetailsImpl() {
        // Create a mock User object
        User user = new User();
        user.setId(1L);
        user.setUsername("Carlos");
        user.setEmail("carlos@gmail..com");
        user.setPassword("HolaSoyPuebloDev");

        // Call the build method to create UserDetailsImpl
        UserDetails userDetails = UserDetailsImpl.build(user);

        // Assertions
        assertNotNull(userDetails);
        assertEquals(user.getId(), ((UserDetailsImpl) userDetails).getId());
        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getEmail(), ((UserDetailsImpl) userDetails).getEmail());
        assertEquals(user.getPassword(), userDetails.getPassword());
        assertEquals(0, userDetails.getAuthorities().size());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isEnabled());
        assertTrue(userDetails.equals(userDetails));
        assertFalse(userDetails.equals(null));
        assertFalse(userDetails.equals(new UserDetailsImpl(2L, "jane_doe", "jane@example.com",
                "password", Collections.emptyList())));
    }
}
