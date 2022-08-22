

package com.sancode.miniproject.web;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sancode.miniproject.domain.Admin;
import com.sancode.miniproject.security.JwtUtil;
import com.sancode.miniproject.service.AdminService;
import com.sancode.miniproject.service.MyUserDetailsService;

@RestController

// @RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService ;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private MyUserDetailsService userDetailsService;

	
	
	
	//post /login - 200 or unathorized
	@PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody Map<String,String> admin) throws Exception{
		String emailId=admin.get("username");
		String password=admin.get("password");
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(emailId, password)
			);
		}
		catch (BadCredentialsException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(emailId);

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(jwt);
	}

    
  
	  
	  // Post /admin -to add a new admin 201 or bad request
	  @PostMapping("/admin")
	  public ResponseEntity saveNewAdmin(@RequestBody Admin toBeAdded) {
		  try {
			  int adminId=adminService.addNewAdmin(toBeAdded);
			  toBeAdded.setAdminId(adminId);
			  HttpHeaders header=new HttpHeaders();
			  header.setLocation(URI.create("/admin/"+adminId));
			  return new ResponseEntity(toBeAdded,header,HttpStatus.CREATED);
		  }
		  catch(IllegalArgumentException e) {
			  return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		  }
	  }
	  
	  //Get /admin - to get all admin details- 200+json body
	  @GetMapping("/admin")
	  public List<Admin> getAll(){
		  
		  return adminService.getAllAdmin();
	  }
	  
//	  //Put /admin/{adminId} - 200 or bad request
	  @PutMapping("/admin/{adminId}")
	  public ResponseEntity updateUserById(@RequestBody Admin toBeUpdated ,@PathVariable(value = "adminId") int adminId) {
		  try {
			  Admin updatedAdmin=adminService.updateAdmin(toBeUpdated, adminId);
			  return new ResponseEntity<>(updatedAdmin,HttpStatus.OK);
			  
		  }
		  catch(IllegalArgumentException e) {
			  return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	      }
	  }
	  
	  
	  

}