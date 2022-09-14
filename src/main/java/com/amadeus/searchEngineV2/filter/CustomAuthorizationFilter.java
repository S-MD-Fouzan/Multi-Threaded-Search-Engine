package com.amadeus.searchEngineV2.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import static java.util.Arrays.stream;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getServletPath().equals("/api/login")) {
			
			filterChain.doFilter(request, response);
			
		}
		else {
			String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
			if(authHeader !=null && authHeader.startsWith("Bearer ")) {
				try {
					
					
					
				String token = authHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(token);
				String username = decodedJWT.getSubject();
				String roles[] = decodedJWT.getClaim("roles").asArray(String.class);
				
				
				Collection<SimpleGrantedAuthority> authorities =new ArrayList<SimpleGrantedAuthority>();
				stream(roles).forEach(role->{
					authorities.add(new SimpleGrantedAuthority(role));
				});
				
				
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(username,null,authorities);
				SecurityContextHolder.getContext().setAuthentication(authToken);
				filterChain.doFilter(request, response);
				
				
				
				}
				catch(Exception e) {
					
					response.setHeader("error", e.getMessage());
					Map<String,String> map=new HashMap<String,String>();
					map.put("error_message", e.getMessage());
					response.setContentType(MediaType.APPLICATION_JSON_VALUE);
					new ObjectMapper().writeValue(response.getOutputStream(), map);
					
				}
			}
			else {
				filterChain.doFilter(request, response);
			}
		}
		
	}

}
