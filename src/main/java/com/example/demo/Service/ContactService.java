package com.example.demo.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Contact;
import com.example.demo.Repository.ContactRepository;
import com.example.demo.Repository.ContactRepository2;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactRepository2 contactRepository2;

    public Contact addContact(Contact contact) {
       
        return contactRepository.save(contact);


    }

    // public List<Contact> getAllContacts() {
    //     return contactRepository.findAll();
    // }

    public Contact getContactById(int id) {
        return contactRepository.findById(id).orElse(null);
    }

public String createContact() {
		ResponseEntity<String> response = contactRepository2.mockCreateContact();
			
		if (response.getStatusCode() == HttpStatus.OK) {
            String responseData = response.getBody();
            return responseData;
        } else {
            return "Error";
        }
	}

	
	public String findContact() {
		ResponseEntity<String> response = contactRepository2.mockFindContact();
		
		if (response.getStatusCode() == HttpStatus.OK) {
            String responseData = response.getBody();
            return responseData;
        } else {
            return "Error";
        }
	}
}
