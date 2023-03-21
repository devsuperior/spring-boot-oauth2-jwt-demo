package com.devsuperior.asdemo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("test")
public class TestConfig {

	@Bean
	@Order(1)
	public SecurityFilterChain h2ConsoleSecurityFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher(PathRequest.toH2Console())
			.csrf().disable()
			.headers().frameOptions().disable()
			.and()
			.authorizeHttpRequests().anyRequest().permitAll();
		return http.build();
	}
}
