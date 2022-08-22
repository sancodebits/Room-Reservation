

package com.sancode.miniproject.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(
//			  new AntPathRequestMatcher("/bookings"),
//			  new AntPathRequestMatcher("/bookings/**"),
//			  new AntPathRequestMatcher("/rooms/**"),
//			  new AntPathRequestMatcher("/rooms"),
//			  new AntPathRequestMatcher("/layouts/**"),
//			  new AntPathRequestMatcher("/layouts"),
//			  new AntPathRequestMatcher("/admin"),
//			  new AntPathRequestMatcher("/equipments/**"),
//			  new AntPathRequestMatcher("/equipments"),
//			  new AntPathRequestMatcher("/FoodAndDrinks/**"),
//			  new AntPathRequestMatcher("/FoodAndDrinks")
			  new AntPathRequestMatcher("/adamya")
			  );
	
	@Autowired
	private UserDetailsService myUserDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	 @Override
	 public void configure(final WebSecurity webSecurity) {
		  webSecurity.ignoring().antMatchers("/login");
	 }
	 
	 
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
		 http.sessionManagement()
		   .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		   .and()
		   .exceptionHandling()
		   .and()
		   .addFilterBefore((Filter) jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
		   .authorizeRequests().
		   antMatchers("/login").permitAll()
		   .requestMatchers(PROTECTED_URLS)
		   .authenticated()
		   .and()
		   .csrf().disable()
		   .formLogin();
	
	}
	

}