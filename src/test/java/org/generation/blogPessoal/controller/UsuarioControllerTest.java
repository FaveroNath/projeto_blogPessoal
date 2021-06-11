package org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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
	private @Autowired UsuarioRepository repo;
	
	private Usuario usuario;
	private Usuario usuarioPut;
	

	
	@BeforeAll
	public void star() {
		usuario = new Usuario("Nathalia Teste","Nath_Fav","teste123");
		usuarioPut = new Usuario("NathTeste","testeNath","teste456");
		repo.save(usuario);
	}
	
	@Test
	void returnListUserWithStatus200() {
		ResponseEntity<String> ans =  testRestTemplate.withBasicAuth("root", "root")
				.exchange("/usuarios/buscar", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, ans.getStatusCode());
	}
	
	@Test 
	void returnStatus201SeUserCadastrado() {
		HttpEntity<Usuario> request  = new HttpEntity<Usuario>(usuario);
		ResponseEntity<Usuario> ans = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, request, Usuario.class);
		assertEquals(HttpStatus.CREATED, ans.getStatusCode());
	}
	
	@Disabled
	@Test
	void returnStatus200SeUserAlterado() {
		HttpEntity<Usuario> request  = new HttpEntity<Usuario>(usuarioPut);
		ResponseEntity<Usuario> ans = testRestTemplate.withBasicAuth("root", "root").exchange("/usuarios/alterar/37", HttpMethod.PUT, request, Usuario.class);
		assertEquals(HttpStatus.OK, ans.getStatusCode());
	}
}
