package com.russ.done.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.russ.done.entity.File;
import com.russ.done.entity.RequestObject;
import com.russ.done.entity.User;
import com.russ.done.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private FileService fileService;



	public User saveUser(User user) {
		return repository.save(user);
	}


	public User saveUser1(RequestObject userObj) {
		List<File> uploadFile = new ArrayList<>();
		User user = new User(0, userObj.getEmail(), userObj.getReason(),null);
		user= repository.save(user);
		try {
			uploadFile = fileService.uploadFileToFileSystem1(userObj.getFiles(),user);
		} catch (Exception e) {
			System.out.println("error occure during saving files :" + e);
		}

		 uploadFile = uploadFile.stream().map(e->{
			 e.setUser(null);
			 return e;
		 }).collect(Collectors.toList());
		 user.setFiles(uploadFile);
		  return user;

	}

	public List<User> saveUsers(List<User> user) {
		return repository.saveAll(user);
	}


	public List<User> getUsers() {
		return repository.findAll();
	}

	public User getUserById(Integer id) {
		
		User user = repository.findById(id).get();
	   return  user;
	}


	public String deleteUser(int id) {
		repository.deleteById(id);
		return "User Successfully Removed!! " + id;
	}

	public User updateUser(User user) {
		User existingUser = repository.findById(user.getId()).orElse(null);
		existingUser.setEmail(user.getEmail());
		existingUser.setReason(user.getReason());
		return existingUser;
	}

}
