package com.devsuperior.rsdemo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
	@GetMapping("/")
	public String home(Authentication authentication) {
		return "Welcome Home! \n\n" +
				"user: " + authentication.getName() + "\n" +
				"authorities: " + authentication.getAuthorities();
	}

}
