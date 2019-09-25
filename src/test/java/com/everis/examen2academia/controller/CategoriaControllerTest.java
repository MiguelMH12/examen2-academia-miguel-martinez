package com.everis.examen2academia.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
public class CategoriaControllerTest {
	
	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testListar() throws Exception {
		String url = "/categorias/";
		MvcResult resultado = mvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
		int status = resultado.getResponse().getStatus();
		assertTrue(status == 200);
		String jsonRespuesta = resultado.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		List<Categoria> categorias =  mapper.readValue(jsonRespuesta, new TypeReference<List<Categoria>>() {});
		assertNotNull(categorias);
		assertTrue(categorias.size() > 0);
		for(Categoria categoria : categorias) {
			assertTrue(categoria.getCategoria() != null);
		}
	}

	@Test
	public void testInsertar() throws Exception {
		String url = "/categorias/";
		Categoria categoria = new Categoria();
		categoria.setCategoria("rock");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(categoria);
		MvcResult resultado = mvc.perform(MockMvcRequestBuilders.post(url).
				contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(json)).andReturn();
		
		int status = resultado.getResponse().getStatus();
		assertTrue(status == 200);
		String jsonRespuesta = resultado.getResponse().getContentAsString();  
		Categoria categoriaInsertada = mapper.readValue(jsonRespuesta, Categoria.class);
		assertTrue(categoriaInsertada.getIdCategoria() > 0);

	}

}
