package esTanFacil.backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void getId() {
        Role role = new Role();
        role.setId(1);
        assertEquals(1, role.getId());
    }

    @Test
    void getName() {
        Role role = new Role();
        role.setName(ERole.ADMIN);
        assertEquals(ERole.ADMIN, role.getName());
    }
}