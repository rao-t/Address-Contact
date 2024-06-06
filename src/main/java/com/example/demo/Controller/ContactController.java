package com.example.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Contact;
import com.example.demo.Service.ContactService;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<?> addContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
        String response = contactService.createContact();
       
        if (response != null) {
            return ResponseEntity.ok(response); 
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create contact");
    }

    }

    // @GetMapping
    // public List<Contact> getAllContacts() {
    //     return contactService.getAllContacts();
    // }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable int id) {
        String response = contactService.findContact();
        if (response != null) {
            return ResponseEntity.ok(response); 
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to find contact"); 
        }
}

@GetMapping("/view/{id}")
    public Contact viewBook(@PathVariable Integer id) {
		return contactService.getContactById(id);
    	
    }
}
