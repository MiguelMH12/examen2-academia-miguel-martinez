package com.everis.examen2academia.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.everis.examen2academia.Examen2AcademiaMiguelMartinezApplication;
import com.everis.examen2academia.model.Categoria;
import com.everis.examen2academia.model.Producto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Examen2AcademiaMiguelMartinezApplication.class)
@WebAppConfiguration
public class ProductoControllerTest {
	
		private MockMvc mvc;
		
		@Autowired
		private WebApplicationContext webApplicationContext;

		@Before
		public void setUp() {
			mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		}

	@Test
	public void testListar() throws Exception {
		String url = "/productos/";
		MvcResult resultado = mvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
		int status = resultado.getResponse().getStatus();
		assertTrue(status == 200);
		String jsonRespuesta = resultado.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		List<Producto> productos =  mapper.readValue(jsonRespuesta, new TypeReference<List<Producto>>() {});
		assertNotNull(productos);  
		assertTrue(productos.size() > 0);  
		for(Producto producto : productos) {
			assertTrue(producto.getNombre() != null);
		}
	}
	
	@Test
	public void testBuscar() throws Exception {
		String cadena = "Shirt";
		String url = "/productos/" + cadena;
		MvcResult resultado = mvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
		int status = resultado.getResponse().getStatus();
		assertTrue(status == 200);  
		String jsonRespuesta = resultado.getResponse().getContentAsString();  
		ObjectMapper mapper = new ObjectMapper();
		List<Producto> productos =  mapper.readValue(jsonRespuesta, new TypeReference<List<Producto>>() {});
		for(Producto producto : productos) {
			assertTrue(producto.getNombre().indexOf(cadena) > -1);
		} 
		
	}

	@Test
	public void testInsertar() throws Exception {
		String url = "/productos/";
		Producto producto = new Producto();
		Categoria categoria = new Categoria();
		
		producto.setNombre("Abercrombie Waller Shirt");
		producto.setPrecio(80.00);
		producto.setDescripcion("Beautiful Shirt");
		
		categoria.setIdCategoria(1);
		
		producto.setCategoria(categoria);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(producto);
		MvcResult resultado = mvc.perform(MockMvcRequestBuilders.post(url).
				contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(json)).andReturn();
		
		int status = resultado.getResponse().getStatus();
		assertTrue(status == 200);
		String jsonRespuesta = resultado.getResponse().getContentAsString();  
		Producto productoInsertado = mapper.readValue(jsonRespuesta, Producto.class);
		assertTrue(productoInsertado.getIdproducto() > 0);

	}

	@Test
	public void testActualizar() throws Exception {
		String url = "/productos/";
		Producto producto = new Producto();
		Categoria categoria = new Categoria();
		
		producto.setIdproducto(4);
		producto.setNombre("Actualizado");
		producto.setPrecio(123);
		producto.setDescripcion("Actualizado");
		categoria.setIdCategoria(1);
		
		producto.setCategoria(categoria);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(producto);
		
		MvcResult resultado = mvc.perform(MockMvcRequestBuilders.put(url).
				contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(json)).andReturn();
		
		int status = resultado.getResponse().getStatus();
		assertTrue(status == 200);  
		String jsonRespuesta = resultado.getResponse().getContentAsString();   
		Producto productoActualizado = mapper.readValue(jsonRespuesta, Producto.class);
		assertTrue(productoActualizado.getNombre().equals("Actualizado"));
		//assertTrue(jsonRespuesta.contains("id"));
	}

	@Test
	public void testBorrar() throws Exception {
		String url = "/productos/2";
		MvcResult resultado = mvc.perform(MockMvcRequestBuilders.delete(url)).andReturn();
		int status = resultado.getResponse().getStatus();
		assertTrue(status == 200);  
		String json = resultado.getResponse().getContentAsString();
		assertTrue(json.equals("true"));
	}

}
