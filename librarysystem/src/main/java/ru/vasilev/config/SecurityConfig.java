package ru.vasilev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ru.vasilev.dao.UserDAO;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain security(HttpSecurity http) throws Exception{
		http
			.csrf(csrf -> csrf
				    .ignoringRequestMatchers("/", "/swagger-ui/**", "/api/v1/register", "/login")
					.disable());
//			.authorizeHttpRequests(request -> request
//					.requestMatchers("/", "/swagger-ui/**", "/h2-console/**", "/api/v1/register", "/login").permitAll()
//					.requestMatchers("/api/v1/users/**").hasRole("ADMIN")
//					.anyRequest().authenticated())
//			.httpBasic(Customizer.withDefaults())
//			.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
		return http.build();
	}
	
	@Bean
	UserDetailsService userDetailsService(UserDAO userDAO) {
		return username -> {			
			return userDAO.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
		};
	}
}