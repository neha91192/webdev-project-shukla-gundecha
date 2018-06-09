package com.example.poc.configuration;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class SuccessfulLoginRedirectionHandler extends SimpleUrlAuthenticationSuccessHandler
		implements AuthenticationSuccessHandler {

	public SuccessfulLoginRedirectionHandler() {
		super();
		setUseReferer(true);
	}

}
