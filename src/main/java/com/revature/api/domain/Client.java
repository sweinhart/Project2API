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
@Table(name = "clients")
public class Client {
	
	@Id @GeneratedValue(generator="clients_id_seq", strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Pattern(regexp="[a-zA-Z\\s]+")
	@javax.validation.constraints.Size(min=2, max=20)
	@NotBlank
	private String name;
	
	@Pattern(regexp="[0-9][0-9][0-9]\\-[0-9][0-9][0-9]\\-[0-9][0-9][0-9][0-9]")
	@NotBlank
	private String phone;
	
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	@NotBlank
	private String email;

	public Client(@Pattern(regexp = "[a-zA-Z]+\\s[a-zA-Z]*") @Size(min = 2, max = 15) @NotBlank String name,
			@Pattern(regexp = "[0-9][0-9][0-9]\\-[0-9][0-9][0-9]\\-[0-9][0-9][0-9][0-9]") @NotBlank String phone,
			@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") @NotBlank String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public Client() {
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

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + "]";
	}
	
	
}
