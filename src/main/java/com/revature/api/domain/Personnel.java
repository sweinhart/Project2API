package com.revature.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "personnel")
public class Personnel {
	
	@Id @GeneratedValue(generator="personnel_id_seq", strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Pattern(regexp="[a-zA-Z]+\\s[a-zA-Z]*")
	@javax.validation.constraints.Size(min=2, max=20)
	@NotBlank
	private String name;
	
	@Pattern(regexp="[0-9][0-9][0-9]\\-[0-9][0-9][0-9]\\-[0-9][0-9][0-9][0-9]")
	@NotBlank
	private String phone;
	
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	@NotBlank
	private String email;
	
	@Pattern(regexp="[a-zA-Z]+")
	@javax.validation.constraints.Size(min=2, max=15)
	@NotBlank
	private String skills;
	
	@Pattern(regexp="[0-9][0-9][0-9]\\-[0-9][0-9]\\-[0-9][0-9][0-9][0-9]")
	@NotBlank
	private String ss;	
	
	@Pattern(regexp="[a-zA-Z]+")
	@javax.validation.constraints.Size(min=2, max=15)
	@NotBlank
	private String client;		
	


	public Personnel(Integer id,
			@Pattern(regexp = "[a-zA-Z]+\\s[a-zA-Z]*") @Size(min = 2, max = 20) @NotBlank String name,
			@Pattern(regexp = "[0-9][0-9][0-9]\\-[0-9][0-9][0-9]\\-[0-9][0-9][0-9][0-9]") @NotBlank String phone,
			@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") @NotBlank String email,
			@Pattern(regexp = "[a-zA-Z]+") @Size(min = 2, max = 15) @NotBlank String skills,
			@Pattern(regexp = "[0-9][0-9][0-9]\\-[0-9][0-9]\\-[0-9][0-9][0-9][0-9]") @NotBlank String ss,
			@Pattern(regexp = "[a-zA-Z]+") @Size(min = 2, max = 15) @NotBlank String client) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.skills = skills;
		this.ss = ss;
		this.client = client;
	}

	public Personnel() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}
	

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}
	
	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}








		
		
	
	
	
}
