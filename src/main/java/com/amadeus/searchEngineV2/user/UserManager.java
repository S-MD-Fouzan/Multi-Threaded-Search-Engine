package com.amadeus.searchEngineV2.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amadeus.searchEngineV2.user.AppUserRepository;
import com.amadeus.searchEngineV2.model.RoleRepository;
import com.amadeus.searchEngineV2.email.EmailService;
import com.amadeus.searchEngineV2.model.AppUser;
import com.amadeus.searchEngineV2.model.Role;

@Service
@Transactional
public class UserManager implements UserDetailsService {
	
	@Autowired
	private AppUserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private EmailService emailService;
	
	//user addition
	public AppUser saveUser(AppUser user) {
		user.setPassword(encoder.encode(user.getPassword()));
		this.emailService.sendSimpleEmail(getLoggedInUser().getUsername(), "User added", "Dear Admin, new user has been added into the database.");
		return this.userRepository.save(user);
	}
	
	public AppUser getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    
		    return this.userRepository.findByUsername(currentUserName);
		}
		
		throw new RuntimeException("No User");
	}
	
	//adding role to user
	public void AddRoleToUser(String username,String rolename) {
		
		AppUser user = this.userRepository.findByUsername(username);
		Role role = this.roleRepository.findByName(rolename);
		user.getRoles().add(role);
		this.emailService.sendSimpleEmail(getLoggedInUser().getUsername(), "Role added to User", "Dear Admin, new role has been added to "+username+" .");
		
	}
	
	public AppUser getUser(String username) {
		return this.userRepository.findByUsername(username);
	}
	public List<AppUser> getUsers() {
		return this.userRepository.findAll();
	}

	//adding new role
	public Role saveRole(Role role) {
		this.emailService.sendSimpleEmail(getLoggedInUser().getUsername(), "New Role Addition", "Dear Admin, new Role has been added.");
		return this.roleRepository.save(role);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = this.userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		user.getRoles().forEach(role ->{
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
				authorities);
	}
	public long getSearchesMade() {
		return getLoggedInUser().getNumOfsearches();
	}

}
