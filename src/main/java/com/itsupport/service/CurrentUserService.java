package com.itsupport.service;

import com.itsupport.model.User;
import com.itsupport.model.enums.UserRole;
import com.itsupport.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

@Service
@RequiredArgsConstructor
public class CurrentUserService {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || 
            authentication instanceof AnonymousAuthenticationToken) {
            // For development/testing, return a default admin user
            return userRepository.findByUsername("admin")
                .orElseGet(() -> createDefaultAdminUser());
        }

        Object principal = authentication.getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return userRepository.findByUsername(username)
            .orElseGet(() -> createDefaultAdminUser());
    }

    private User createDefaultAdminUser() {
        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword("$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG"); // admin123
        adminUser.setRole(UserRole.ADMIN);
        return userRepository.save(adminUser);
    }
} 