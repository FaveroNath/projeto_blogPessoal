package org.generation.blogPessoal.service;

import java.util.Optional;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class PostagemService {
	private @Autowired PostagemRepository repository;
		
	/**
	 * Método que verifica antes se a postagem a ser alterada já foi previamente cadastrada.
	 * @param id 
	 * @param post
	 * @return retorna um optional vazio caso ele não exista no banco
	 */
	public Optional<Object> salvarPostagem(long id, Postagem post){
		Optional<Postagem> postExistente = repository.findById(id);
		if(postExistente.isPresent()) {
			Postagem newPost = postExistente.get(); //Puxo o post antigo por conta do Id e só altero o titulo e post
			newPost.setTitulo(post.getTitulo());
			newPost.setTexto(post.getTexto());
			return Optional.ofNullable(repository.save(newPost));
		}
		return Optional.empty();		
	}

	
}
