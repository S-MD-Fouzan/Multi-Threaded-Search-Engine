package com.amadeus.searchEngineV2.config;

import java.lang.invoke.VarHandle.AccessMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.amadeus.searchEngineV2.filter.CustomAuthenticationFilter;
import com.amadeus.searchEngineV2.filter.CustomAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;

	private BCryptPasswordEncoder encoder;
	
	

	public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder encoder) {
		super();
		this.userDetailsService = userDetailsService;
		this.encoder = encoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		
		CustomAuthenticationFilter custom = new CustomAuthenticationFilter(authenticationManagerBean());
		custom.setFilterProcessesUrl("/api/login");
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/api/login/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/users/**").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/user/save/**").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/role/addtouser/**").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/role/save/**").hasAnyAuthority("ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/search/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/searchdrive/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/history/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/historybyname/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/history/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/deletehistory/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/getcount/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/searches/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/logs/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/log/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/logbytype/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/transactions/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/transactionsbyuser/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/getmost/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
		
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/logout/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN").and().logout();
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(custom);
		http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	

}
