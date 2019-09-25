package com.everis.examen2academia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.examen2academia.model.Producto;
import com.everis.examen2academia.repository.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository productoRepositorio;
	
	public List<Producto> listar(){
		return productoRepositorio.findAll();
	}
	
	public List<Producto> buscar(String cadena) {
		List<Producto> productos = productoRepositorio.findAll();
		List<Producto> encontradas = new ArrayList<Producto>();
		for(Producto productoBuscado: productos) {
			if(productoBuscado.getNombre().indexOf(cadena) > -1) {
				encontradas.add(productoBuscado);
			}
		}
		return encontradas;
	}
	
	
	public Producto insertar(Producto producto) {
		return productoRepositorio.save(producto);
	}
	
	public Producto actualizar(Producto producto) {
		return productoRepositorio.save(producto);
	}
	
	public boolean borrar(int id) {
		if(productoRepositorio.existsById(id)) {
			productoRepositorio.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}
	

}
