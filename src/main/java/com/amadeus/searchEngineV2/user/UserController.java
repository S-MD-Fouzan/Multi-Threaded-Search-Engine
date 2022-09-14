package com.amadeus.searchEngineV2.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amadeus.searchEngineV2.model.AppUser;
import com.amadeus.searchEngineV2.model.Role;
import com.amadeus.searchEngineV2.model.RoleToUser;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserManager userManager;
	
	@GetMapping("/users")
	public ResponseEntity<List<AppUser>> getUsers(){
		return ResponseEntity.ok().body(this.userManager.getUsers());
	}
	
	@PostMapping("/user/save")
	public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user){
		return ResponseEntity.ok().body(this.userManager.saveUser(user));
	}
	
	@PostMapping("/role/save")
	public ResponseEntity<Role> saveUser(@RequestBody Role role){
		return ResponseEntity.ok().body(this.userManager.saveRole(role));
	}
	
	@PostMapping("/role/addtouser")
	public ResponseEntity<Role> addRoleToUser(@RequestBody RoleToUser form){
		this.userManager.AddRoleToUser(form.getUsername(), form.getRolename());
		return ResponseEntity.ok().build();
	}
}