package com.russ.done.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.russ.done.entity.RequestObject;
import com.russ.done.entity.User;
import com.russ.done.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping("/addUser1")
//	@RequestMapping(path="/addUser1",method=RequestMethod.POST,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})	
	public 	User addUser(@ModelAttribute RequestObject user) {
		return service.saveUser1(user);
		}
	
	@PostMapping("/addUsers")
	public List<User> addProduct(@RequestBody List<User> user) {
		return service.saveUsers(user);
	}
	
	@GetMapping("/users")
	public List<User> findAllUsers(){
		return service.getUsers();
	}
	
	@GetMapping("/userById/{id}")
	public User findUserById(@PathVariable  int id) {
		return service.getUserById(id); 
	}
	

	@PutMapping("/update")
	public User updateUser(@RequestBody User user) {
		return service.updateUser(user);
		}
	
	
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable int id) {
		return service.deleteUser(id);
	}

}
