package org.generation.blogPessoal.repository;




import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;


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
		Usuario user = new Usuario("Maria Silva","Maria123","senha123");
		if(repo.findByUsuario(user.getUsuario()) != null) repo.save(user);
		
		user = new Usuario("Jonatas joão","joazinho","senha321");
		if(repo.findByUsuario(user.getUsuario()) != null)  repo.save(user);
		
		user = new Usuario("Nathalia Teste","nathFav","senha456");
		if(repo.findByUsuario(user.getUsuario()) != null) repo.save(user);
		
		user = new Usuario("Maisa souza","+aaaa123","snh123");
		if(repo.findByUsuario(user.getUsuario()) != null) repo.save(user);
	}
	
	//testa se o usuário com o user "nathFav" foi cadastrado.
	@Test
	public void findByUsuarioRetornaUsuario() throws Exception {
		Usuario testUser = repo.findByUsuario("nathFav").get();
		assertTrue(testUser.getUsuario().equals("nathFav"));
	}
	
	//Testa se existe 2 usuarios que contém "M" em seus nomes.
	@Test
	public void findAllByNomeContainingIgnoreCaseTesteRetirnaLista() {
		List<Usuario> testList = repo.findAllByNomeContainingIgnoreCase("M");
		assertEquals(2, testList.size());
	}
	
	//E verifica se os 4 usuarios foram cadastrados.
	@Test
	public void findAllList() {
		List<Usuario> testList = repo.findAll();
		assertEquals(4, testList.size());
	}
	
	
	@AfterAll
	public void end() {
		repo.deleteAll();
	}

}
