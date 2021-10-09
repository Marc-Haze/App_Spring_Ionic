package com.marcos.libros.entity.services;

import java.util.List;
import java.util.Optional;

import com.marcos.libros.entity.models.Libro;

public interface ILibroService {

	public Libro get(long id);
	public List<Libro> getAll();
	public void add(Libro libro);
	public void put(Libro libro, long id);
	public void delete(long id);
	Optional<Libro> getOne(long id);

	
}