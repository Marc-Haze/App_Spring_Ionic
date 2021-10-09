package com.marcos.libros.controllers;
import com.marcos.libros.entity.models.Libro;
import com.marcos.libros.entity.services.ILibroService;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
public class LibroController {

	@Autowired
	ILibroService libroService;
	
	@GetMapping("/libro")
	public List<Libro> getAllLibros(){
		return libroService.getAll();				
	}	
	
	@GetMapping("/libro/{id}")
	public Libro getOne(@PathVariable(value = "id") long id) {
		Optional<Libro> b = libroService.getOne(id);
		if(b.isPresent()) {
			System.out.println("Se ha obtenido con éxito el libro con ID: "+id);
			return b.get();
		};
		
		return null;
	}
	
	@PostMapping("/libro")
	public void add(Libro libro) {
		System.out.println(libro.getTitle());
		libroService.add(libro);
	}
	
	@PostMapping(value="/libros", consumes="application/json")
	void addUsingJson(@RequestBody String libroString) {
		ObjectMapper om = new ObjectMapper();
		try {
			Libro libro = om.readValue(libroString, Libro.class);
			System.out.println(libro.getTitle());
			libroService.add(libro);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@DeleteMapping("/libro/{id}")
	public void delete(@PathVariable(value = "id") long id) {
		System.out.println("Se ha eliminado el libro con ID:"+id);
		libroService.delete(id);
	}
	
	@PutMapping("/libro/{id}")
	public void put(Libro libro,@PathVariable(value = "id") long id) {
		libroService.put(libro, id);
	}
	
	
}