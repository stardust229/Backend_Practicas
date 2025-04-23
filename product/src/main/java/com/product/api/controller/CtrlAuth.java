package com.product.api.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.auth.UsernameOrEmailAuthenticationToken;
import com.product.api.commons.dto.AuthApiResponse;
import com.product.api.dto.in.DtoLoginIn;
import com.product.api.entity.User;
import com.product.config.jwt.JwtUtil;
import com.product.util.Globals;

import jakarta.validation.Valid;

//Autenticación de usuarios registrados. Operaciones para autenticar usuarios que existen en el sistema
//@Slf4j
@RestController
public class CtrlAuth{

	private static final String REQUEST_LOG = "Request: {}";
	private static final String RESPONSE_LOG = "Response: {}";
	private static final String ENDPOINT_AUTH_USER = "/login";
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	// Autentica un usuario y devuelve un JWT con el ROL registrado si las credenciales son válidas
	@PostMapping(value = ENDPOINT_AUTH_USER)
	public ResponseEntity<AuthApiResponse> autenticaUsuario(@Valid @RequestBody DtoLoginIn request)  {
		
		AuthApiResponse response = new AuthApiResponse();
		
		HashMap<String, String> elementosAutenticacion = new HashMap<String, String>();
		
		if(StringUtils.hasLength(request.getUsername()))
			elementosAutenticacion.put("username", request.getUsername());
		if(StringUtils.hasLength(request.getEmail()))
			elementosAutenticacion.put("correo", request.getEmail());
		
		Authentication authenticate = authenticationManager.authenticate(new UsernameOrEmailAuthenticationToken(elementosAutenticacion, request.getPassword()));
		
		response.setDetails(Arrays.asList("Autenticación exitosa"));
		
		String jwt = jwtUtil.generateToken((User) authenticate.getPrincipal());
		
		response.setToken(jwt);
		response.setDateTime(Globals.formatDate(new Date()));
		
		return new ResponseEntity<>(response, HttpStatus.OK);      
	}

}
