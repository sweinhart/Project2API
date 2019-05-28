package com.revature.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import com.revature.api.exceptions.DataNotFoundException;
import com.revature.api.exceptions.UnauthorizedException;
import com.revature.api.service.PersonnelService;

@RestController
@RequestMapping("/EmploymentAgencyAPI/available")
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
			@RequestParam("password") String pass) throws UnauthorizedException, DataNotFoundException {
		if (user.equals("admin") && pass.equals("admin")){
			try{
			Personnel p = personnelService.getPersonnelDetails(id);
			return p;
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
			throw new DataNotFoundException();
		}			
		else
			throw new UnauthorizedException();
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
}
