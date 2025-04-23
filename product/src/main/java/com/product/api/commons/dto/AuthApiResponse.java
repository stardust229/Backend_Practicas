package com.product.api.commons.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/*import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;*/

/*@Slf4j
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor*/
@JsonInclude(Include.NON_EMPTY)
public class AuthApiResponse {
	
	private String token;
	private String dateTime;
	private List<String> details;
	private List<UsuarioResponse> users;
	
	@Override
    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
        	//log.error("Excepción atrapada al serializar objeto: " + e.getMessage());
        	return super.toString(); 
        }
    }

	public void addUser(UsuarioResponse usuario) {
		if(this.users == null)
			this.users = new ArrayList<>();
		
		this.users.add(usuario);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public List<UsuarioResponse> getUsers() {
		return users;
	}

	public void setUsers(List<UsuarioResponse> users) {
		this.users = users;
	}

}