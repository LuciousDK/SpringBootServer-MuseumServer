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
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET,"/administrators/**","/administrator/**").authenticated()
			.antMatchers(HttpMethod.GET, "/**").permitAll()
			.antMatchers(HttpMethod.PUT,"/**").authenticated()
			.antMatchers(HttpMethod.POST,"/**").authenticated()
			.antMatchers(HttpMethod.DELETE,"/**").authenticated()
			.and()
			.httpBasic()
			.and()
			.logout();
	}
	
	
	@Configuration
	public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {
	    @Override
	    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/img/**").addResourceLocations("file:img/");
	        registry.addResourceHandler("/video/**").addResourceLocations("file:video/");
	        registry.addResourceHandler("/audio/**").addResourceLocations("file:audio/");
	    }
	}
	
}
