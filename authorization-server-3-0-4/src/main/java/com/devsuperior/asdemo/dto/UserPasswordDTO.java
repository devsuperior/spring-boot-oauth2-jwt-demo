package com.devsuperior.asdemo.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public record UserPasswordDTO(String username, Collection<GrantedAuthority> authorities) {

}
