package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DaoUser;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Todo;
import com.example.demo.model.User;

@RestController
@RequestMapping("/rest/users")
//@CrossOrigin("http://localhost:5500")
public class ControllerUser {

	@Autowired
	private DaoUser daoUser;
	
	@PostMapping("/save")
	public User saveUser(@RequestBody UserDto userDto) {
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setDob(userDto.getDob());
		daoUser.save(user);
		return user;
	}
	
	@GetMapping("/by-id/{id}")
	public User getUserByIdJustOne(@PathVariable("id") int id) {
		return daoUser.findById(id).get();
	}
	
	@GetMapping("/all")
	public Iterable<User> findAll(){
		return daoUser.findAll();
	}
	
	@DeleteMapping("/delete/{idul}")
	public User deteleAUser(@PathVariable("idul") int idOfUserToDelete) {
		User forDeletion = daoUser.findById(idOfUserToDelete).get();
		daoUser.delete(forDeletion);
		return forDeletion;
	}
	
	@PutMapping("/update")
	public User updateAUser(@RequestBody User userUpdatatFaraPassword) {
		
		User userVechiCuUsernameNou = daoUser.findById(userUpdatatFaraPassword.getId()).get(); // SELECT * FROM users where id = userUpdatat.getId()
		userVechiCuUsernameNou.setUsername(userUpdatatFaraPassword.getUsername());
		return daoUser.save(userVechiCuUsernameNou); // UPDATE users SET username = userUpdatat.getUsername(), password = userUpdatat.getPassword() WHERE id = userUpdatat.getId()
	}
}
