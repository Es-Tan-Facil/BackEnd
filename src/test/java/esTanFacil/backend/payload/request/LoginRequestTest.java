package esTanFacil.backend.payload.request;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class LoginRequestTest {

    @Test
    public void testNotBlankUsername() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("");
        loginRequest.setPassword("password");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);

        assertEquals(1, violations.size());
        ConstraintViolation<LoginRequest> violation = violations.iterator().next();
        assertEquals("username", violation.getPropertyPath().toString());
        assertEquals("no debe estar vacío", violation.getMessage());
    }

    @Test
    public void testNotBlankPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);

        assertEquals(1, violations.size());
        ConstraintViolation<LoginRequest> violation = violations.iterator().next();
        assertEquals("password", violation.getPropertyPath().toString());
        assertEquals("no debe estar vacío", violation.getMessage());
    }

    @Test
    public void testValidLoginRequest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);

        assertTrue(violations.isEmpty());
    }
}