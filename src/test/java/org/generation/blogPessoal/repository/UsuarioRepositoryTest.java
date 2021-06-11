package org.generation.blogPessoal.repository;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //Nos permite usar a mesma instância de classe para os outros teste, reciclando.
public class UsuarioRepositoryTest {
	
	private @Autowired UsuarioRepository repo;
	
	@BeforeAll
	void start() {
		Usuario user = new Usuario("Maria Silva","Masil","senha123");
		if(repo.findByUsuario(user.getUsuario()) != null) 
					repo.save(user);
		
		user = new Usuario("Jonatas joão","joazinho","senha321");
		if(repo.findByUsuario(user.getUsuario()) != null) 
					repo.save(user);
		
		user = new Usuario("Nathalia Teste","nathFav","senha456");
		if(repo.findByUsuario(user.getUsuario()) != null) 
					repo.save(user);
		
		user = new Usuario("Maisa souza","+aaaa123","snh123");
		if(repo.findByUsuario(user.getUsuario()) != null) 
					repo.save(user);
	}
	
	@Test
	public void findByUsuarioRetornaUsuario() throws Exception {
		Usuario testUser = repo.findByUsuario("nathFav").get();
		assertTrue(testUser.getUsuario().equals("nathFav"));
	}
	
	@AfterAll
	public void end() {
		repo.deleteAll();
	}

}
