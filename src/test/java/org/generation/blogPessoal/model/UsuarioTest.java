package org.generation.blogPessoal.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


//Para não usar a porta da nossa aplicação ele pega uma porta aleatória.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioTest {
	Usuario usuario;
	
	@Autowired
	private  ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	@BeforeEach
	public void start() {
		usuario = new Usuario("Maria Clara","Clarinha","123456");
	}
	
	@Test
	public void testValidationAtributes() {
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		System.out.println(violations.toString());
		assertTrue(violations.isEmpty());
	}
	
	
	 /**
	  * Testo se são exibidas mensagens de erro caso o usuário deixe nulo os valores de senha e de usuario.
	  */
	@Test
	public void testValidationOfAttributesNull() {
		usuario.setNome(null);
		usuario.setSenha(null);
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		System.out.println("Menssagem de erro: "+violations.toString());
		assertFalse(violations.isEmpty());
	}
	
	/**
	 * Testa o tamanho dos valores passados para ver se está de acordo com o tamanho permitido.
	 */
	@Test
	public void testValidationsOfAttributesSize() {
		usuario.setNome("a");
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
		System.out.print("Menssagem de erro: " + violations.toString());
		assertFalse(violations.isEmpty());
	}
}
