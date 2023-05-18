package esTanFacil.backend.controller;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestControllerTest {

    @InjectMocks
    private TestController testController;
    private MockMvc mockMvc;

    @Test
    public void testAllAccess() {
        String result = testController.allAccess();
        assertEquals("Public Content.", result);
    }



}
