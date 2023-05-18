package esTanFacil.backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void getId() {
        User user = new User();
        user.setId(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    void getUsername() {
        User user = new User();
        user.setUsername("example");
        assertEquals("example", user.getUsername());
    }

    @Test
    void getEmail() {
        User user = new User();
        user.setEmail("example@example.com");
        assertEquals("example@example.com", user.getEmail());
    }

    @Test
    void getPassword() {
        User user = new User();
        user.setPassword("password");
        assertEquals("password", user.getPassword());
    }

    @Test
    void getRoles() {
        User user = new User();
        Role role = new Role();
        role.setId(1);
        role.setName(ERole.ADMIN);
        user.getRoles().add(role);

        assertTrue(user.getRoles().contains(role));
    }
}