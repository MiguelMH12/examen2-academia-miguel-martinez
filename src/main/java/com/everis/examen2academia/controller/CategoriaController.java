package com.everis.examen2academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.examen2academia.model.Producto;
import com.everis.examen2academia.service.ProductoService;

@RestController
@RequestMapping("categorias")
public class CategoriaController {
	
	@Autowired
	private ProductoService productoServicio;
	
	@GetMapping("/")
	public List<Producto> listar(){
		return productoServicio.listar();
	}
	
	@PostMapping("/")
	public Producto insertar(@RequestBody Producto producto) {
		return productoServicio.insertar(producto);
	}

}
