package edu.devry.cis470.tps.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component("AuthenticationSuccessHandler")
public class TPSAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request,
			final HttpServletResponse response,
			final Authentication authentication) throws IOException,
			ServletException {
		final UserDetails principle = (UserDetails) authentication
				.getPrincipal();

		for (final GrantedAuthority role : principle.getAuthorities()) {
			if ("ROLE_CLIENT".equals(role.getAuthority())) {
				response.sendRedirect("manage");
				return;
			}
			if ("ROLE_STAFF".equals(role.getAuthority())) {
				response.sendRedirect("profile");
				return;
			}
		}

		response.sendRedirect("/login");
	}

}
