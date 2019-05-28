package com.revature.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.api.domain.Client;
import com.revature.api.domain.Personnel;
import com.revature.api.exceptions.DataNotFoundException;
import com.revature.api.exceptions.UnauthorizedException;
import com.revature.api.exceptions.InvalidRequestException;
import com.revature.api.service.ClientService;
import com.revature.api.service.PersonnelService;

@RestController
@RequestMapping("/EmploymentAgencyAPI/clients")
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
	public Client getClient(@PathVariable("name") String name) throws DataNotFoundException {
		Client c = clientService.getClient(name);
		if (c == null)
			throw new DataNotFoundException();
		else return c;
	}
	
	@GetMapping("/{name}/employees")
	public List<Personnel> getClientEmployees(@PathVariable("name") String name) throws DataNotFoundException {
		Client c = clientService.getClient(name);
		if (c == null)
			throw new DataNotFoundException();
		return personnelService.getEmployed(name);
	}
	
	@PostMapping("/{name}/hire/{id}")
	public String hire(@PathVariable("name") String name, @PathVariable("id") Integer id, 
			@RequestParam("username") String user, @RequestParam("password") String pass) 
					throws UnauthorizedException, InvalidRequestException {
		if (user.equals("admin") && pass.equals("admin")){
			Client c = clientService.getClient(name);
			if (c == null)
				throw new InvalidRequestException();
			Personnel p = personnelService.getPersonnelDetails(id);
			if (p.getClient().equals("Unemployed")){
				personnelService.hire(name, id);
				return p.getName() + " has been hired by " + name;
			}				
			else throw new InvalidRequestException();
		}
		else throw new UnauthorizedException();
	}
	
	@PostMapping("/{name}/release/{id}")
	public String release(@PathVariable("name") String name, @PathVariable("id") Integer id, 
			@RequestParam("username") String user, @RequestParam("password") String pass) 
					throws UnauthorizedException, InvalidRequestException{
		if (user.equals("admin") && pass.equals("admin")){
			Client c = clientService.getClient(name);
			if (c == null)
				throw new InvalidRequestException();
			Personnel p = personnelService.getPersonnelDetails(id);
			if (p.getClient().equals(name)){
				personnelService.release(id);
				return p.getName() + " has been released by " + name;
			}
			else throw new InvalidRequestException();
		}
		else throw new UnauthorizedException();
	}
	
	@PostMapping("/apply")
	public String release( @RequestParam("username") String user,
			@RequestParam("password") String pass, @RequestParam("name") String name, @RequestParam("phone") String phone, 
			@RequestParam("email") String email) throws InvalidRequestException, UnauthorizedException{
		if (user.equals("admin") && pass.equals("admin")){
			Client temp = clientService.getClient(name);
			if (temp != null)
				throw new InvalidRequestException();
			Client client = new Client(name, phone, email);
			clientService.saveNewClient(client);
			return name + " can now hire employees.";
		}
		else throw new UnauthorizedException();
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
	public String unauthorized() {
		return HttpStatus.UNAUTHORIZED.toString();
	}
	
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public String dataNotFound() {
		return HttpStatus.NOT_FOUND.toString();
	}
	
	@ExceptionHandler(InvalidRequestException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public String invalidRequest() {
		return HttpStatus.BAD_REQUEST.toString();
	}
}
