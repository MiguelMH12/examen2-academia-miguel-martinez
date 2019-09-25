package com.everis.examen2academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.examen2academia.model.Producto;
import com.everis.examen2academia.service.ProductoService;

@RestController
@RequestMapping("productos")
public class ProductoController {
	
	@Autowired
	private ProductoService productoServicio;
	
	@GetMapping("/")
	public List<Producto> listar(){
		return productoServicio.listar();
	}
	
	@GetMapping("/{cadena}")
	public List<Producto> buscar(@PathVariable String cadena){
		return productoServicio.buscar(cadena);
	}
	
	@PostMapping("/")
	public Producto insertar(@RequestBody Producto producto) {
		return productoServicio.insertar(producto);
	}
	
	@PutMapping("/")
	public Producto actualizar(@RequestBody Producto producto) {
		return productoServicio.actualizar(producto);
	}
	
	@DeleteMapping("/{id}")
	public boolean borrar(@PathVariable int id) {
		return productoServicio.borrar(id);
	}

}
