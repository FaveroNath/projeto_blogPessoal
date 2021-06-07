package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	private @Autowired UsuarioService usuariosService;
	private @Autowired UsuarioRepository repository;
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user){
		return usuariosService.logar(user)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuariosService.cadastrarUsuario(usuario));
	}
	@GetMapping("/buscar")
	public ResponseEntity<Object> buscar(){
		List<Usuario> users = repository.findAll();
		if(users.isEmpty()) {
			return ResponseEntity.status(200).body("A lista está vazia");
		}else {
			return ResponseEntity.status(200).body(users);
		}
	}
	@GetMapping("/buscar/id")
	public ResponseEntity<Usuario> buscaId(@RequestParam("id") long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(200).build());
	}
	@GetMapping("/buscar/user")
	public ResponseEntity<Usuario> buscaUser(@RequestParam("usuario") String usuario){
		return repository.findByUsuario(usuario).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(200).build());
	}
	@GetMapping("/buscar/users/{nome}")
	public ResponseEntity<Object> buscaUsers(@PathVariable String nome){
		List<Usuario> users = repository.findAllByNomeContainingIgnoreCase(nome);
		if(users.isEmpty()) {
			return ResponseEntity.status(200).body("A lista está vazia");
		}else {
			return ResponseEntity.status(200).body(users);
		}
	}
	
	@PutMapping("/alterar/{id}")
	public ResponseEntity<Usuario> put(@PathVariable long id, @RequestBody Usuario user){
		return usuariosService.putUser(id, user)
				.map(resp -> ResponseEntity.ok(repository.save(resp))).orElse(ResponseEntity.status(204).build());
	}
	@DeleteMapping("/deletar")
	public ResponseEntity<Object> deleteUser(@RequestParam("id") long id){
		Optional<Usuario> user = repository.findById(id);
		if(user.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(200).body("Deletado com sucesso");
		} else {
			return ResponseEntity.status(200).body("Não existe usuario com esse id");
		}
	}
	
}
