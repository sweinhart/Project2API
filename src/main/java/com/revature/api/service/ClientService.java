package com.revature.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.api.domain.Client;
import com.revature.api.repository.ClientRepository;

@Service
public class ClientService {
	@Autowired
	ClientRepository clientRepository;
	
	public void setClientRepository(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	@Transactional
	public List<Client> getAllClients() {
		return this.clientRepository.findAll();
	}
	
	@Transactional
	public Client getClient(String name) {
		return this.clientRepository.findByName(name);
	}
	
	@Transactional
	public Client saveNewClient(Client c) {
		return clientRepository.save(c);
	}
	
	@Transactional
	public void deleteById(Integer id) {
		clientRepository.deleteById(id);
	}
}
