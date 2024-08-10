package com.dux.software.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    public String login(String username, String password);
}
