package com.example.demo.Controller;

import com.example.demo.Entity.Contact;
import com.example.demo.Service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContactController.class)
public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;

    private Contact mockContact;

    @BeforeEach
    public void setUp() {
  
        mockContact = new Contact();
        mockContact.setId(1);
        mockContact.setName("John Doe");
        mockContact.setEmail("john.doe@example.com");
        mockContact.setPhone("1234567890");

        // Mocking the methods of contactService
        Mockito.when(contactService.addContact(Mockito.any(Contact.class))).thenReturn(mockContact);
        Mockito.when(contactService.createContact()).thenReturn("Contact Created");
        Mockito.when(contactService.findContact()).thenReturn("Contact Found");
        Mockito.when(contactService.getContactById(1)).thenReturn(mockContact);
    }

    @Test
    public void testAddContact() throws Exception {
        mockMvc.perform(post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"phone\":\"1234567890\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Contact Created"));
    }

    @Test
    public void testGetContactById() throws Exception {
        mockMvc.perform(get("/api/contacts/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Contact Found"));
    }

    @Test
    public void testViewBook() throws Exception {
        mockMvc.perform(get("/api/contacts/view/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"John Doe\",\"phone\":\"1234567890\",\"email\":\"john.doe@example.com\"}"));
    }

    @Test
    public void testAddContactFail() throws Exception {
        Mockito.when(contactService.createContact()).thenReturn(null);

        mockMvc.perform(post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"phone\":\"1234567890\"}"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Failed to create contact"));
    }

    @Test
    public void testGetContactByIdFail() throws Exception {
        Mockito.when(contactService.findContact()).thenReturn(null);

        mockMvc.perform(get("/api/contacts/1"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Failed to find contact"));
    }
}
