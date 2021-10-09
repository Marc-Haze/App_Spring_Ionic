package com.marcos.libros.entity.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.marcos.libros.entity.dao.ILibroDao;
import com.marcos.libros.entity.models.Libro;

@Service
public class LibroServiceImpl implements ILibroService{

	@Autowired
	private ILibroDao libroDao;
	
	@Override
	public Libro get(long id) {
		return libroDao.findById(id).get();
	}

	@Override
	public List<Libro> getAll() {
		return (List<Libro>) libroDao.findAll();
	}

	@Override
	public void add(Libro libro) {
		libroDao.save(libro);
		
	}

	@Override
	public void put(Libro libro, long id) {
			libroDao.findById(id).ifPresent((x) -> {
				libro.setId(id);
				libroDao.save(libro);
			});
	}

	@Override
	public void delete(long id) {
		libroDao.deleteById(id);
		
	}
	
	@Override
	public Optional<Libro> getOne(long id) {
		return libroDao.findById(id);
	}

}