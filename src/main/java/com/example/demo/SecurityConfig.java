package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        // Use BCrypt for strong, salted password hashing
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disabling CSRF for simplicity in this example
            .authorizeHttpRequests((requests) -> requests
                // Allow access to static resources, signup, and login pages for everyone
                .requestMatchers("/signup/**", "/login/**", "/css/**", "/js/**", "/images/**").permitAll()
                // Any other request must be authenticated (user must be logged in)
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                // Use our custom login page
                .loginPage("/login")
                // The URL to submit the username and password to
                .loginProcessingUrl("/login")
                // The page to go to after a successful login
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout((logout) -> logout
                // The URL that triggers logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // Invalidate session and clear security context
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                // The page to go to after logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );
        return http.build();
    }
}