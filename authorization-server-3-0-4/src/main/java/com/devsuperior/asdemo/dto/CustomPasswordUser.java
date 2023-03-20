package com.devsuperior.asdemo.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public record CustomPasswordUser(String username, Collection<? extends GrantedAuthority> authorities) {

}
