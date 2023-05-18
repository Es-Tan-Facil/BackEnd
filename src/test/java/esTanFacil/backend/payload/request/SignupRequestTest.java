package esTanFacil.backend.payload.request;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;

public class SignupRequestTest {

    @Test
    public void testNotBlankUsername() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("");
        signupRequest.setEmail("test@example.com");
        signupRequest.setPassword("password");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);

        assertEquals(2, violations.size());
        ConstraintViolation<SignupRequest> violation = violations.iterator().next();
        assertEquals("username", violation.getPropertyPath().toString());
    }

    @Test
    public void testSizeConstraintUsername() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("us");
        signupRequest.setEmail("test@example.com");
        signupRequest.setPassword("password");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);

        assertEquals(1, violations.size());
        ConstraintViolation<SignupRequest> violation = violations.iterator().next();
        assertEquals("username", violation.getPropertyPath().toString());
        assertEquals("el tamaño debe estar entre 3 y 20", violation.getMessage());
    }

    @Test
    public void testNotBlankEmail() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("username");
        signupRequest.setEmail("");
        signupRequest.setPassword("password");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);

        assertEquals(1, violations.size());
        ConstraintViolation<SignupRequest> violation = violations.iterator().next();
        assertEquals("email", violation.getPropertyPath().toString());
        assertEquals("no debe estar vacío", violation.getMessage());
    }

    @Test
    public void testSizeConstraintEmail() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("username");
        signupRequest.setEmail("test@example.com".repeat(10)); // 500 characters
        signupRequest.setPassword("password");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);

        assertEquals(2, violations.size());
        ConstraintViolation<SignupRequest> violation = violations.iterator().next();
        assertEquals("email", violation.getPropertyPath().toString());
    }

    @Test
    public void testInvalidEmail() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("username");
        signupRequest.setEmail("invalid-email");
        signupRequest.setPassword("password");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);

        assertEquals(1, violations.size());
        ConstraintViolation<SignupRequest> violation = violations.iterator().next();
        assertEquals("email", violation.getPropertyPath().toString());
        assertEquals("debe ser una dirección de correo electrónico con formato correcto", violation.getMessage());
    }

    @Test
    public void testNotBlankPassword() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("username");
        signupRequest.setEmail("test@example.com");
        signupRequest.setPassword("");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);

        assertEquals(2, violations.size());
        ConstraintViolation<SignupRequest> violation = violations.iterator().next();
        assertEquals("password", violation.getPropertyPath().toString());
    }

    @Test
    public void testSizeConstraintPassword() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("username");
        signupRequest.setEmail("test@example.com");
        signupRequest.setPassword("pass");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);

        assertEquals(1, violations.size());
        ConstraintViolation<SignupRequest> violation = violations.iterator().next();
        assertEquals("password", violation.getPropertyPath().toString());
        assertEquals("el tamaño debe estar entre 6 y 40", violation.getMessage());
    }

    @Test
    public void testValidSignupRequest() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("username");
        signupRequest.setEmail("test@example.com");
        signupRequest.setPassword("password");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<SignupRequest>> violations = validator.validate(signupRequest);

        assertTrue(violations.isEmpty());
    }
}