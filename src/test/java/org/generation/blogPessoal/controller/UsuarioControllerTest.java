package org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {

	private @Autowired TestRestTemplate testRestTemplate;
	
	private Usuario usuario;
	private Usuario usuarioPut;
	

	
	@BeforeAll
	public void star() {
		usuario = new Usuario("Nathalia Teste","Nath_Fav","teste123");
		usuarioPut = new Usuario("NathTeste","testeNath","teste456");
	}
	
	@Test
	void returnListUserWithStatus200() {
		ResponseEntity<String> ans =  testRestTemplate.withBasicAuth("Nath_Fav", "teste123")
				.exchange("/usuarios/buscar", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, ans.getStatusCode());
	}
	
	
}
