package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DaoTodo;
import com.example.demo.dao.DaoTodoPaginare;
import com.example.demo.dao.DaoUser;
import com.example.demo.model.Todo;
import com.example.demo.model.User;

@RestController
@RequestMapping("/rest/todos")
public class ControllerTodo {
	
	@Autowired
	private DaoTodo daoTodo;
	
	@Autowired 
	private DaoTodoPaginare daoTodoPaginare;
	
	@Autowired
	private DaoUser daoUser;
	
	
	@PostMapping("/save/{idUser}")
	public Todo saveTodo(@RequestBody Todo todo, @PathVariable("idUser")int idUser) {
		System.out.println("ID USER ASOCIERE: " + idUser);
		User userAsociat = daoUser.findById(idUser).get();
		todo.setUserAsociat(userAsociat);
		return daoTodo.save(todo);
	}
	
	@GetMapping("/all")
	public Iterable<Todo> findAll(){
		return daoTodo.findAll();
	}
	
	@GetMapping("/all-paginated/{nrPaginii}")
	public Page<Todo> findAllPaginated(@PathVariable("nrPaginii") int nrPagina){
		
		Pageable pageable = PageRequest.of(nrPagina, 5);
		Page<Todo> pagina = this.daoTodoPaginare.findAll(pageable);
		return pagina;
	}
	
	
	@DeleteMapping("/delete/{idul}")
	public Todo deleteATodo(@PathVariable("idul") int idOfTodoToDelete) {
		Todo forDeletion = daoTodo.findById(idOfTodoToDelete).get();
		daoTodo.delete(forDeletion);
		return forDeletion;
	}
	
	@PutMapping("/update/{idUser}")
	public Todo updateATodo(@RequestBody Todo todoUpdatat, @PathVariable("idUser") int idUser) {
		User userDejaAsociat = daoUser.findById(idUser).get();
		todoUpdatat.setUserAsociat(userDejaAsociat);
		System.out.println("USER ASOCIAT: " + idUser);
		return daoTodo.save(todoUpdatat);
	}

	
	@PutMapping("/update-fara-id-user")
	public Todo updateATodoWithoutUserId(@RequestBody Todo todoUpdatat) {
		
		User userulExistent = null;
		Todo todoulVechi = daoTodo.findById(todoUpdatat.getId()).get();
		userulExistent = todoulVechi.getUserAsociat();
		
		todoUpdatat.setUserAsociat(userulExistent);
		daoTodo.save(todoUpdatat);
		
		return daoTodo.save(todoUpdatat);
	}
}
