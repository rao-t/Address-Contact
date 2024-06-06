package com.example.demo.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.Entity.Contact;
import com.example.demo.Repository.ContactRepository;
import com.example.demo.Repository.ContactRepository2;

public class ServiceTest {

    @InjectMocks
    private ContactService contactService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactRepository2 contactRepository2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddContact() {
        Contact contact = new Contact();
        contact.setId(1);
        contact.setName("John Doe");
        contact.setEmail("john.doe@example.com");
        contact.setPhone("1234567890");

        when(contactRepository.save(contact)).thenReturn(contact);

        Contact createdContact = contactService.addContact(contact);
        assertNotNull(createdContact);
        assertEquals("John Doe", createdContact.getName());
    }

    @Test
    public void testCreateContact() {
        ResponseEntity<String> mockResponse = new ResponseEntity<>("Contact Created", HttpStatus.OK);
        when(contactRepository2.mockCreateContact()).thenReturn(mockResponse);

        String response = contactService.createContact();
        assertEquals("Contact Created", response);
    }

    @Test
    public void testFindContact() {
        ResponseEntity<String> mockResponse = new ResponseEntity<>("Contact Found", HttpStatus.OK);
        when(contactRepository2.mockFindContact()).thenReturn(mockResponse);

        String response = contactService.findContact();
        assertEquals("Contact Found", response);
    }
    
}
