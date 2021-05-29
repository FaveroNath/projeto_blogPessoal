package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.generation.blogPessoal.service.TemaService;
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
@CrossOrigin(origins =  "*", allowedHeaders = "*")
@RequestMapping("/tema")
public class TemaController {
	private @Autowired TemaRepository repository;
	private @Autowired TemaService service;
	
	@GetMapping
	public  ResponseEntity<List<Tema>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	@GetMapping("/{id}")
	public  ResponseEntity<Tema> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> 	ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(404).build());
	}
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> getByName(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(nome));
	}
	@PostMapping
	public ResponseEntity<Tema> postTema(@RequestBody Tema tema){
		//return ResponseEntity.status(201).body(repository.save(tema));
		return service.salvarTema(tema).map(respOptional -> ResponseEntity.status(201).body(respOptional))
				.orElse(ResponseEntity.status(400).build());
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Tema> putTema(@PathVariable long id, @RequestBody Tema tema){
		return service.alterarTema(id, tema)
				.map(respOptional -> ResponseEntity.status(201).body(respOptional)).
				orElse(ResponseEntity.status(204).build());
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Tema> deleteTema(@PathVariable long id){
		Optional<Tema> temaExistente = repository.findById(id);
		if(temaExistente.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(202).build();
		}
		return ResponseEntity.status(204).build();
	}
}
