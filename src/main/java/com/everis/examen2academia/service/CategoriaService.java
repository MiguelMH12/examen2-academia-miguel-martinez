package com.everis.examen2academia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.examen2academia.model.Categoria;
import com.everis.examen2academia.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepositorio;
	
	public List<Categoria> listar(){
		return categoriaRepositorio.findAll();
	}
	
	public Categoria insertar(Categoria categoria) {
		return categoriaRepositorio.save(categoria);
	}
}
