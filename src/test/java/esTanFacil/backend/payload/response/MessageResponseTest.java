package esTanFacil.backend.payload.response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MessageResponseTest {

    @Test
    public void testMessageResponse() {
        String message = "Test message";
        MessageResponse messageResponse = new MessageResponse(message);

        assertEquals(message, messageResponse.getMessage());
    }

    @Test
    public void testSetMessage() {
        String initialMessage = "Initial message";
        String newMessage = "New message";
        MessageResponse messageResponse = new MessageResponse(initialMessage);

        assertEquals(initialMessage, messageResponse.getMessage());

        messageResponse.setMessage(newMessage);

        assertEquals(newMessage, messageResponse.getMessage());
    }
}