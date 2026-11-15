package com.main.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.main.filter.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http,JwtAuthenticationFilter jwtFilter) throws Exception{
			http
			.csrf(csrf->csrf.disable())
			.formLogin(form-> form.disable())
			.httpBasic(basic-> basic.disable())
			 .cors(Customizer.withDefaults())
			.sessionManagement(session -> session
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            )
				.authorizeHttpRequests(auth->auth
					.requestMatchers( HttpMethod.POST,
									"/api/auth/register",
									"/api/auth/login").permitAll()
					
					.requestMatchers("/api/admin/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.GET, 
									"/api/questions",
									"/api/questions/{id}").permitAll()
					.requestMatchers("/api/code/template/**").permitAll()
					.anyRequest().authenticated()
					)
				
				.exceptionHandling(exception -> exception
		                .authenticationEntryPoint((request, response, ex) ->
		                    response.sendError(401, "Unauthorized")
		                )
		            )
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
			
			return http.build();
			
		}
	
	 @Bean
	    public CorsConfigurationSource corsConfigurationSource() {

	        CorsConfiguration config = new CorsConfiguration();

	        config.setAllowedOrigins(
	            List.of("http://localhost:5173")
	        );

	        config.setAllowedMethods(
	            List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
	        );

	        config.setAllowedHeaders(
	            List.of("Authorization", "Content-Type")
	        );

	        config.setAllowCredentials(false);
	        config.setMaxAge(3600L);

	        UrlBasedCorsConfigurationSource source =
	            new UrlBasedCorsConfigurationSource();

	        source.registerCorsConfiguration("/**", config);

	        return source;
	    }
}
