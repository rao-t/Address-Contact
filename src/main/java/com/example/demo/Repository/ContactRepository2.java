package com.example.demo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ContactRepository2 {

    @Autowired
    RestTemplate restTemplate;
    

    public ResponseEntity<String> mockCreateContact() {
	
		
		String url = "http://localhost:9090/createContactSample";
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,null,String.class);
		  if (response.getStatusCode() == HttpStatus.OK) {
	            return ResponseEntity.ok(response.getBody());
	        } else {
	            return ResponseEntity.status(response.getStatusCode()).body("Failed to create contact");
	        }
	

		}

		public ResponseEntity<String> mockFindContact() {
	
		
		String url = "http://localhost:9090/getContactSample";
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,null,String.class);
		  if (response.getStatusCode() == HttpStatus.OK) {
	            return ResponseEntity.ok(response.getBody());
	        } else {
	            return ResponseEntity.status(response.getStatusCode()).body("Failed to find contact");
	        }
		}

}
