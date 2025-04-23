package com.product.api.entity;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {
	
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty("id")
  @Column(name = "id")
  private Integer id;


  @JsonProperty("name")
  @Column(name = "name")
  private String name;
  
  @JsonProperty("lastName")
  @Column(name = "lastName")
  private String lastName;
  
  @JsonProperty("username")
  @Column(name = "username")
  private String username;
  
  @JsonProperty("email")
  @Column(name = "email")
  private String email;
  
  @JsonProperty("roles")
  @Column(name = "roles")
  private Set<String> roles;
  
  @JsonProperty("isActive")
  @Column(name = "isActive")
  private Boolean isActive;

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

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
