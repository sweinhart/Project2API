package com.revature.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.api.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
	Client findByName(String name);
}
