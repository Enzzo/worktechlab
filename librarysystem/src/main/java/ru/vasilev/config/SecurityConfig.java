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

import ru.vasilev.repository.UserRepository;

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
					.disable())
			.authorizeHttpRequests(request -> request
					.requestMatchers("/", 
							"/swagger-ui/**", "/h2-console/**", 
							"/api/v1/register", 
							"/login", "/register").permitAll()
					.requestMatchers("/api/v1/users/**").hasRole("ADMIN")
					.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults())
			.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
		return http.build();
	}
	
	@Bean
	UserDetailsService userDetailsService(UserRepository userDAO) {
		return username -> {			
			return userDAO.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
		};
	}
	
//	@Bean
//	UserDetailsService inMemoryUser(PasswordEncoder encoder) {
//		UserDetails user = User.builder()
//				.username("admin")
//				.password(encoder.encode("123"))
//				.roles("ADMIN")
//				.build();
//		return new InMemoryUserDetailsManager(user);
//	}
}