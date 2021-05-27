package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem){
		return ResponseEntity.status(201).body(repository.save(postagem));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> putPostagem(@PathVariable(value= "id") long id, @RequestBody Postagem postagem){
		Optional<Postagem> postExistente = repository.findById(id);
		if(postExistente.isPresent()) {
			Postagem newPost = postExistente.get(); //Puxo o post antigo por conta do Id e só altero o titulo e post"
			newPost.setTitulo(postagem.getTitulo());
			newPost.setTexto(postagem.getTexto());
			return ResponseEntity.status(200).body(repository.save(newPost));
		}
		return ResponseEntity.status(204).build();		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Postagem> delete(@PathVariable long id) {
		Optional<Postagem> post = repository.findById(id);
		if(post.isPresent()){
			repository.deleteById(id);
			return ResponseEntity.status(202).build();
		} 
		return ResponseEntity.status(204).build();
		
	}
}
 