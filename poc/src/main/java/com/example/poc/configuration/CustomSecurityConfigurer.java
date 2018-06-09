package com.example.poc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity

public class CustomSecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomAuthenticationProvider authProvider;

	/**
	 * All resources that need login or not can be mentioned here. Additionally,
	 * Login and logout url can be mentioned here, if they are not mentioned, it
	 * takes default login form provided by spring framework.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/api/course").permitAll().antMatchers("/api/user/**").hasRole("USER")
				.and().formLogin().and().logout().permitAll();

	}

	/**
	 * Declares auth provider for custom security configurer
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

}
