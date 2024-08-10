package com.dux.software;

import com.dux.software.model.Auth;
import com.dux.software.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByUsername("test")) {
            Auth user = new Auth();
            user.setUsername("test");
            user.setPassword(passwordEncoder.encode("12345"));
            userRepository.save(user);
        }
    }
}