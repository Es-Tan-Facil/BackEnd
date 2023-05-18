package esTanFacil.backend.security.jwt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import org.mockito.Mockito;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

public class AuthEntryPointJwtTest {

    @Test
    public void testCommence() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AuthenticationException authException = mock(AuthenticationException.class);

        AuthEntryPointJwt entryPoint = new AuthEntryPointJwt();

        entryPoint.commence(request, response, authException);

        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }
}