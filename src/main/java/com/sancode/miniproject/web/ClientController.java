package com.sancode.miniproject.web;
///*******************************************************************************
// *
//
//
//package com.adobe.miniproject.web;
//
//import java.net.URI;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.adobe.miniproject.domain.Client;
//import com.adobe.miniproject.service.ClientService;
//
//@RestController
//public class ClientController {
//	
//	ClientService service;
//	
//	@Autowired
//	public void setService(ClientService service) {
//		this.service = service;
//	}
//	
//	@PostMapping("/clients")
//	public ResponseEntity addNewClient(@RequestBody Client toBeAdded) {
//		try {
//			int id = service.addNewClient(toBeAdded);
//			HttpHeaders headers = new HttpHeaders();
//			headers.setLocation(URI.create("/clients/"+id));
//			return new ResponseEntity<Client>(toBeAdded,headers,HttpStatus.CREATED);
//		} catch(IllegalArgumentException e){
//			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
//		}
//	}
//	
//	@GetMapping("/clients/{id}")
//	public ResponseEntity<Client> getClientById(@PathVariable("id") int id){
//		Client c = service.findById(id);
//		if(c != null) {
//			return new ResponseEntity<Client>(c, HttpStatus.OK);
//		}
//		
//		else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//}
