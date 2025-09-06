package com.coffee.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

		http
			.authorizeHttpRequests((authorize) -> authorize
				.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults())
			.formLogin(Customizer.withDefaults())
			.authenticationManager(setUpAuthentication())
		;
		return http.build();
	}

	private UserDetailsService userDetailsService () {
		String password = "Nachan";
		Argon2PasswordEncoder encoder = 
			new Argon2PasswordEncoder(16, 32, 1, 60000, 10);
		//
		//
		// String encrypted = encoder.encode(password);
		// System.out.println("Encrypted password is " + encrypted);

		UserDetails userDetail = User
			.withUsername("Mitsuru")
			.password(encoder.encode(password))
			.roles("Admin")
			.build();

		return new InMemoryUserDetailsManager(userDetail);
	}

	private AuthenticationManager setUpAuthentication(){
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

		ProviderManager providerManager = new ProviderManager(daoAuthenticationProvider);
		providerManager.setEraseCredentialsAfterAuthentication(false);
		return providerManager;	
	}

	private PasswordEncoder passwordEncoder(){
		return new Argon2PasswordEncoder(16, 32, 1, 60000, 10);
	}
}
