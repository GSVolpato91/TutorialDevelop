package com.techacademy;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    /** authentication and authorization settings */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
            .loginProcessingUrl("/login")    // Send username and password to
            .loginPage("/login")             // Login Screen
            .defaultSuccessUrl("/user/list") // Redirect to after successful login
            .failureUrl("/login?error")       // Redirect on login failure
            .permitAll()                     // Login screen accessible without login
        ).logout(logout -> logout
            .logoutSuccessUrl("/login")       // Redirect to after logout
        ).authorizeHttpRequests(auth -> auth
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll()                 // css, etc. can be accessed without logging in
            .anyRequest().authenticated()    // Others require login
        );

        return http.build();
    }

    /** used to compare hashed passwords */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}