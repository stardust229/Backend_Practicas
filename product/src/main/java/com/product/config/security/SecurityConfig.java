package com.product.config.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.product.api.auth.UsernameOrEmailAuthenticationProvider;
import com.product.config.jwt.JwtAuthFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtAuthFilter jwtFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfig corsConfig) throws Exception {
	
		http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(
				auth -> auth
				.requestMatchers("/error", "/swagger-ui/**", "/v3/api-docs/**", "/actuator/info", "/actuator/health", "/secured/usuario").permitAll()
				
				// Region
				.requestMatchers(HttpMethod.GET, "/region/active").hasAnyAuthority("ADMIN", "CUSTOMER")
				.requestMatchers("/region/**").hasAuthority("ADMIN")
								
				// Customer
				.requestMatchers(HttpMethod.GET, "/customer/*").hasAnyAuthority("ADMIN", "CUSTOMER")
				.requestMatchers("/customer/**").hasAuthority("ADMIN")
								
				// Customer images
				.requestMatchers("/customer-image/**").hasAnyAuthority("CUSTOMER")
				)
		.cors(cors -> cors.configurationSource(corsConfig))
		.httpBasic(Customizer.withDefaults())
		.formLogin(form -> form.disable())
		.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
			
		return http.build();
	}
	
	@Bean
    AuthenticationManager authenticationManager(UsernameOrEmailAuthenticationProvider provider) {
        return new ProviderManager(List.of(provider));
    }
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}		
	
	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.print(bCryptPasswordEncoder.encode("contrasenaSegura"));
	}

}