package esTanFacil.backend.controller;

import esTanFacil.backend.model.ERole;
import esTanFacil.backend.model.Role;
import esTanFacil.backend.payload.request.LoginRequest;
import esTanFacil.backend.payload.request.SignupRequest;
import esTanFacil.backend.payload.response.JwtResponse;
import esTanFacil.backend.payload.response.MessageResponse;
import esTanFacil.backend.repositories.RoleRepository;
import esTanFacil.backend.repositories.UserRepository;

import esTanFacil.backend.security.jwt.JwtUtils;
import esTanFacil.backend.security.services.UserDetailsImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder encoder;


    @Mock
    private JwtUtils jwtUtils;


    @InjectMocks
    private AuthController authController;

    @Test
    public void testAuthenticateUser() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("testpassword");

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        UserDetailsImpl userDetails = Mockito.mock(UserDetailsImpl.class);
        Mockito.when(authentication.getPrincipal()).thenReturn(userDetails);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        Mockito.when(userDetails.getAuthorities()).thenAnswer(invocation -> authorities);

        String jwt = "testjwt";
        Mockito.when(jwtUtils.generateJwtToken(authentication)).thenReturn(jwt);

        // Act
        ResponseEntity<?> response = authController.authenticateUser(loginRequest);

        // Assert
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertTrue(response.getBody() instanceof JwtResponse);
        JwtResponse jwtResponse = (JwtResponse) response.getBody();
        Assert.assertEquals(jwt, jwtResponse.getAccessToken());
        Assert.assertEquals(userDetails.getId(), jwtResponse.getId());
        Assert.assertEquals(userDetails.getUsername(), jwtResponse.getUsername());
        Assert.assertEquals(userDetails.getEmail(), jwtResponse.getEmail());
        Assert.assertEquals(authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()),
                jwtResponse.getRoles());
    }

    @Test
    public void testRegisterUserWithValidRequest() {
        SignupRequest request = new SignupRequest();
        request.setUsername("testuser");
        request.setEmail("testuser@example.com");
        request.setPassword("testpassword");
        Set<String> roles = new HashSet<>();
        roles.add("user");
        request.setRole(roles);

        when(userRepository.existsByUsername(request.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.of(new Role(ERole.ROLE_USER)));
        when(encoder.encode(request.getPassword())).thenReturn("encodedpassword");

        ResponseEntity<?> response = authController.registerUser(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User registered successfully!", ((MessageResponse) Objects.requireNonNull(response.getBody())).getMessage());
    }

    @Test
    public void testRegisterUserWithExistingUsername() {
        SignupRequest request = new SignupRequest();
        request.setUsername("testuser");
        request.setEmail("testuser@example.com");
        request.setPassword("testpassword");
        Set<String> roles = new HashSet<>();
        roles.add("user");
        request.setRole(roles);

        when(userRepository.existsByUsername(request.getUsername())).thenReturn(true);

        ResponseEntity<?> response = authController.registerUser(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Username is already taken!", ((MessageResponse) Objects.requireNonNull(response.getBody())).getMessage());
    }

    @Test
    public void testRegisterUserWithExistingEmail() {
        SignupRequest request = new SignupRequest();
        request.setUsername("testuser");
        request.setEmail("testuser@example.com");
        request.setPassword("testpassword");
        Set<String> roles = new HashSet<>();
        roles.add("user");
        request.setRole(roles);

        when(userRepository.existsByUsername(request.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        ResponseEntity<?> response = authController.registerUser(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Email is already in use!", ((MessageResponse) Objects.requireNonNull(response.getBody())).getMessage());
    }


}



