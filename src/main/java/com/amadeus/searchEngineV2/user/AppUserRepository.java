package com.amadeus.searchEngineV2.user;


import org.springframework.data.jpa.repository.JpaRepository;
import com.amadeus.searchEngineV2.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Long>{
	
	AppUser findByUsername(String username);

}

