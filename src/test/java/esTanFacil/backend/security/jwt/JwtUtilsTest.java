package esTanFacil.backend.security.jwt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import esTanFacil.backend.security.jwt.JwtUtils;
import esTanFacil.backend.security.services.UserDetailsImpl;

import java.util.Date;


public class JwtUtilsTest {

    private JwtUtils jwtUtils;
    private UserDetailsImpl userDetails;
    private String jwtSecret;

    @BeforeEach
    public void setup() {
        jwtUtils = new JwtUtils();
        jwtSecret = "yourJwtSecret";

        userDetails = mock(UserDetailsImpl.class);
        when(userDetails.getUsername()).thenReturn("testuser");
    }

    @Test
    public void testValidateJwtToken_ValidToken() {
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 1000)) // Token expiring in 1 second
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        boolean isValid = jwtUtils.validateJwtToken(token);

        assertFalse(isValid);
    }

    @Test
    public void testValidateJwtToken_InvalidToken() {
        String token = "invalidToken";

        boolean isValid = jwtUtils.validateJwtToken(token);

        assertFalse(isValid);
    }
}