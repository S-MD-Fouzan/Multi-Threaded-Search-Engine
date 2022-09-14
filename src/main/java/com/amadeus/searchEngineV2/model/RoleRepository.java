package com.amadeus.searchEngineV2.model;


import org.springframework.data.jpa.repository.JpaRepository;

import com.amadeus.searchEngineV2.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
	Role findByName(String name);

}
