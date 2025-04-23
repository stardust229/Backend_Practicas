package com.product.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class DtoUserIn {

	@JsonProperty("name")
	@NotNull(message="El nombre es obligatorio")
	private String name;
	
	@NotNull(message="El apellido es obligatorio")
	@JsonProperty("lastName")
	private String lastName;
	
	@NotNull(message="El username es obligatorio")
	@JsonProperty("username")
	private String username;
	
	@NotNull(message="El email es obligatorio")
	@JsonProperty("email")
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
