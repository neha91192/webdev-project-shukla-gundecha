package com.example.poc.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	/**
	 * Custom authenticator to check whether username and password is correct. A
	 * call to database is required which returns user credentials. If credentials
	 * match, it adds Roles in granted authority for further authorization check. If
	 * credentials do not match, it returns {@link BadCredentialsException}
	 * 
	 * 
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		boolean isAuthenticated = false;

		if (name.toString().equals("neha") && !password.toString().equals("neha")) {

		} else {
			isAuthenticated = true;
		}

		// check this combination in database
		final List<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
		final UserDetails principal = new User(name, password, grantedAuths);
		Authentication auth = null;
		if (isAuthenticated) {
			auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
		} else {
			auth = new UsernamePasswordAuthenticationToken(principal, password);
			// auth.setAuthenticated(false);
			throw new BadCredentialsException("Invalid login");
		}
		return auth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
