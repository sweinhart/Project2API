package com.revature.api.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.api.domain.Client;
import com.revature.api.domain.Personnel;
import com.revature.api.service.ClientService;
import com.revature.api.service.PersonnelService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	PersonnelService personnelService;

	HttpServletResponse response;
	
	@GetMapping
	public List<Client> getAll() {
		return clientService.getAllClients();
	}
	
	@GetMapping("/{name}")
	public Client getClient(@PathVariable("name") String name) {
		return clientService.getClient(name);
	}
	
	@GetMapping("/{name}/employees")
	public List<Personnel> getClientEmployees(@PathVariable("name") String name) {
		return personnelService.getEmployed(name);
	}
	
	@PostMapping("/{name}/hire/{id}")
	public String hire(@PathVariable("name") String name, @PathVariable("id") Integer id, 
			@RequestParam("username") String user, @RequestParam("password") String pass) 
					throws IOException {
		if (user.equals("admin") && pass.equals("admin")){
			personnelService.hire(name, id);
			Personnel p = personnelService.getPersonnelDetails(id);
			return p.getName() + " has been hired by " + name;
		}
		else throw new IOException();
	}
	
	@PostMapping("/{name}/release/{id}")
	public String release(@PathVariable("name") String name, @PathVariable("id") Integer id, 
			@RequestParam("username") String user, @RequestParam("password") String pass) 
					throws IOException{
		if (user.equals("admin") && pass.equals("admin")){
			personnelService.release(id);
			Personnel p = personnelService.getPersonnelDetails(id);
			return p.getName() + " has been released by " + name;
		}
		else throw new IOException();
	}
	
	@PostMapping("/apply")
	public String release( @RequestParam("username") String user,
			@RequestParam("password") String pass, @RequestParam("name") String name, @RequestParam("phone") String phone, 
			@RequestParam("email") String email) throws IOException{
		if (user.equals("admin") && pass.equals("admin")){
			Client temp = clientService.getClient(name);
			if (temp != null)
				throw new IOException();
			Client client = new Client(name, phone, email);
			clientService.saveNewClient(client);
			return name + " can now hire employees.";
		}
		else throw new IOException();
	}

	@ExceptionHandler(IOException.class)
	@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
	public void ioProblem() {
		response.setStatus(Response.SC_UNAUTHORIZED);
		//return response.getOutputStream().toString();
	}
}
