package com.revature.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.revature.api.domain.Personnel;
import com.revature.api.domain.PersonnelShort;
import com.revature.api.service.PersonnelService;

@RestController
@RequestMapping("/available")
public class PersonnelController {
	@Autowired
	PersonnelService personnelService;
	HttpServletResponse response;
	
	@GetMapping
	public List<PersonnelShort> getUnemployed() {
		List<Personnel> list = personnelService.getUnemployed();
		List<PersonnelShort> list1 = new ArrayList<PersonnelShort>();
		for (Personnel p : list){
			PersonnelShort person = new PersonnelShort();
			person.setId(p.getId());
			person.setName(p.getName());
			person.setPhone(p.getPhone());
			person.setEmail(p.getEmail());
			person.setSkills(p.getSkills());
			if (p.getClient().equals("Unemployed"))
				list1.add(person);
		}
		return list1;
	}
	
	@GetMapping("/{id}")
	public Personnel getPersonnelDetails(@PathVariable("id") Integer id, @RequestParam("username") String user,
			@RequestParam("password") String pass) throws IOException {
		if (user.equals("admin") && pass.equals("admin"))
			return personnelService.getPersonnelDetails(id);
		else
			throw new IOException();
	}
	
	@ExceptionHandler(IOException.class)
	@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
	public void ioProblem() {
		response.setStatus(Response.SC_UNAUTHORIZED);
		//return response.getOutputStream().toString();
	}
}
