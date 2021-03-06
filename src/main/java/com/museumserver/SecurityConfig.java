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

import com.museumserver.services.UserCredentialService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserCredentialService userDetailsService;
	
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
			.antMatchers(HttpMethod.GET, "/css/login.css/**","/assets/**","/js/**").permitAll()
			.antMatchers(HttpMethod.GET, "/api/artworks/**","/api/artwork/**","/api/beacons/**", "/api/beacon/**","/api/exhibitions/**","/api/exhibition/**",
					"/api/media/**","/api/medias/**","/img/**","/audio/**","/video/**").authenticated()
			.antMatchers(HttpMethod.GET, "/**").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.PUT,"/**").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.POST,"/**").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.DELETE,"/**").hasAuthority("ADMIN")
			.and()
			.formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
			.httpBasic()
			.and()
			.logout();
	}
	
	
	@Configuration
	public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {
	    @Override
	    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:img/assets/");
	        registry.addResourceHandler("/img/**").addResourceLocations("classpath:img/images/");
	        registry.addResourceHandler("/video/**").addResourceLocations("classpath:video/");
	        registry.addResourceHandler("/audio/**").addResourceLocations("classpath:audio/");
	        registry.addResourceHandler("/css/**").addResourceLocations("classpath:css/");
	        registry.addResourceHandler("/js/**").addResourceLocations("classpath:js/");
	    }
	}
	
}
