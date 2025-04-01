package ru.vasilev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
					.requestMatchers("/h2-console/**").permitAll()
					.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults())
			.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
		return http.build();
	}
	
	@Bean
	InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
		UserDetails user = User.withUsername("user")
				.password(encoder.encode("password"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}