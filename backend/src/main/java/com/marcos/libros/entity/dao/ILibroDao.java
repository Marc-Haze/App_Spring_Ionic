package com.marcos.libros.entity.dao;

import org.springframework.data.repository.CrudRepository;
import com.marcos.libros.entity.models.Libro;

public interface ILibroDao extends CrudRepository<Libro, Long>{
	
	
}