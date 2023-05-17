package esTanFacil.backend.security.services;

import esTanFacil.backend.model.ERole;
import esTanFacil.backend.model.Role;
import esTanFacil.backend.model.User;
import esTanFacil.backend.security.services.UserDetailsImpl;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserDetailsImplTest {

    @Test
    public void testUserDetailsImpl() {
        // Crear un usuario de prueba
        User user = new User();
        user.setId(1L);
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        Role role = new Role();
        role.setName(ERole.ROLE_USER);
        user.setRoles(Collections.singleton(role));

        // Mock del método getRoles() del usuario
        User mockedUser = mock(User.class);
        when(mockedUser.getRoles()).thenReturn(Collections.singleton(role));

        // Construir el UserDetailsImpl utilizando el método build()
        UserDetails userDetails = UserDetailsImpl.build(mockedUser);

        // Verificar los detalles del UserDetails
        assertEquals(user.getId(), ((UserDetailsImpl) userDetails).getId());
        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getEmail(), ((UserDetailsImpl) userDetails).getEmail());
        assertEquals(user.getPassword(), userDetails.getPassword());

        List<GrantedAuthority> expectedAuthorities = user.getRoles().stream()
                .filter(Objects::nonNull) // Filtrar roles nulos
                .map(r -> new SimpleGrantedAuthority(r.getName() != null ? r.getName().toString() : ""))
                .collect(Collectors.toList());

        assertEquals(expectedAuthorities, userDetails.getAuthorities());
        assertEquals(true, userDetails.isAccountNonExpired());
        assertEquals(true, userDetails.isAccountNonLocked());
        assertEquals(true, userDetails.isCredentialsNonExpired());
        assertEquals(true, userDetails.isEnabled());
    }

}
