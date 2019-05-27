package com.revature.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.api.domain.Personnel;
import com.revature.api.repository.PersonnelRepository;

@Service
public class PersonnelService {
	
	@Autowired
	PersonnelRepository personnelRepository;
	
	public void setPersonnelRepository(PersonnelRepository personnelRepository) {
		this.personnelRepository = personnelRepository;
	}
	
	@Transactional
	public Personnel getPersonnelDetails(Integer id){
		Personnel p = this.personnelRepository.findById(id).get();
		return p;
	}

	@Transactional
	public List<Personnel> getUnemployed() {
		return this.personnelRepository.findByClient("Unemployed");
	}
	
	@Transactional
	public List<Personnel> getEmployed(String client) {
		return this.personnelRepository.findByClient(client);
	}
	
	@Transactional
	public void hire(String client, Integer id){
		Personnel p = this.personnelRepository.findById(id).get();
		p.setClient(client);
		this.personnelRepository.save(p);
	}
	
	@Transactional
	public void release(Integer id){
		Personnel p = this.personnelRepository.findById(id).get();
		p.setClient("Unemployed");
		this.personnelRepository.save(p);
	}
}
