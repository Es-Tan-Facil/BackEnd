package esTanFacil.backend.payload.response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class JwtResponseTest {

    @Test
    public void testJwtResponse() {
        String accessToken = "dummyAccessToken";
        Long id = 1L;
        String username = "testuser";
        String email = "test@example.com";
        List<String> roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");

        JwtResponse jwtResponse = new JwtResponse(accessToken, id, username, email, roles);

        assertEquals(accessToken, jwtResponse.getAccessToken());
        assertEquals("Bearer", jwtResponse.getTokenType());
        assertEquals(id, jwtResponse.getId());
        assertEquals(username, jwtResponse.getUsername());
        assertEquals(email, jwtResponse.getEmail());
        assertEquals(roles, jwtResponse.getRoles());
    }

    @Test
    public void testSettersAndGetters() {
        JwtResponse jwtResponse = new JwtResponse(null, null, null, null, null);

        String accessToken = "dummyAccessToken";
        Long id = 1L;
        String username = "testuser";
        String email = "test@example.com";

        jwtResponse.setAccessToken(accessToken);
        jwtResponse.setTokenType("Bearer");
        jwtResponse.setId(id);
        jwtResponse.setUsername(username);
        jwtResponse.setEmail(email);

        assertEquals(accessToken, jwtResponse.getAccessToken());
        assertEquals("Bearer", jwtResponse.getTokenType());
        assertEquals(id, jwtResponse.getId());
        assertEquals(username, jwtResponse.getUsername());
        assertEquals(email, jwtResponse.getEmail());

    }
}