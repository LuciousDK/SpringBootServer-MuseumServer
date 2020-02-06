package com.museumserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.museumserver.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/exhibitions","/artworks","/media","/beacons","/exhibition/**","/artwork/**","/media/**","/beacon/**").permitAll()
			.antMatchers(HttpMethod.PUT,"/exhibition/**","/artwork/**","/media/**","/beacon/**").authenticated()
			.antMatchers(HttpMethod.POST,"/exhibition/**","/artwork/**","/media/**","/beacon/**").authenticated()
			.antMatchers(HttpMethod.DELETE,"/exhibition/**","/artwork/**","/media/**","/beacon/**").authenticated()
			.antMatchers(HttpMethod.GET,"/administrators/**","/administrator/**").authenticated()
			.and()
			.httpBasic()
			.and()
			.logout();
	}
	
}
