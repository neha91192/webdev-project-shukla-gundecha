package com.neu.webdev2018summer1.thefoodexplorer.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	CustomUserDetailsService userDetailService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Custom authenticator to check whether username and password is correct. A
	 * call to database is required which returns user credentials. If credentials
	 * match, it adds Roles in granted authority for further authorization check. If
	 * credentials do not match, it returns {@link BadCredentialsException}
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		boolean isAuthenticated = false;

		UserDetails userDetails = userDetailService.loadUserByUsername(username);
		if (passwordEncoder.matches(password, userDetails.getPassword())) {
			isAuthenticated = true;
		} else {
			isAuthenticated = false;
		}

		// check this combination in database
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths = (List<GrantedAuthority>) userDetails.getAuthorities();
		final UserDetails principal = new User(username, password, grantedAuths);
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
