package com.amadeus.searchEngineV2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.amadeus.searchEngineV2.model.AppUser;
import com.amadeus.searchEngineV2.model.Role;
import com.amadeus.searchEngineV2.model.RoleRepository;
import com.amadeus.searchEngineV2.user.UserManager;



@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableAsync
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	CommandLineRunner run(UserManager userManager,RoleRepository roleRepo) {
//		return args -> {
//			
//			userManager.saveUser(new AppUser("Fouzan","fozan@ama.com","FZ123"));
//			userManager.saveUser(new AppUser("Fouzan Shaik","princefozan1234@gmail.com","fz@123"));
//			roleRepo.save(new Role("ROLE_USER"));
//			roleRepo.save(new Role("ROLE_ADMIN"));
//			userManager.AddRoleToUser("fozan@ama.com", "ROLE_ADMIN");
//			userManager.AddRoleToUser("princefozan1234@gmail.com", "ROLE_USER");
//			
//			
//		};
//	}
}
