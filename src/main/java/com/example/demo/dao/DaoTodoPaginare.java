package com.example.demo.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Todo;

@Repository
public interface DaoTodoPaginare extends PagingAndSortingRepository<Todo, Integer>{

}
