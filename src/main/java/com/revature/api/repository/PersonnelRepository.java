package com.revature.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.api.domain.Personnel;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Integer>{
	
	@Transactional
	List<Personnel> findByClient(String client);
}
