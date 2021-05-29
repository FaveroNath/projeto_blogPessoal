package org.generation.blogPessoal.service;

import java.util.Optional;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service 
public class TemaService {
	private @Autowired TemaRepository repository;
	
	/**
	 * Método para verificar se o tema que vai ser alterado realmente existe no banco.
	 * @param id do tema
	 * @param tema (descricao)
	 * @return Um optional vazio caso não encontrar o tema no banco.
	 */
	public Optional<Tema> alterarTema(long id, Tema tema){
		Optional<Tema> temaAntigo = repository.findById(id);
		if(temaAntigo.isPresent()) {
			Tema newTema = temaAntigo.get();
			newTema.setDescricao(tema.getDescricao());
			return Optional.ofNullable(repository.save(newTema));
		} 
		return Optional.empty();
	}
	
	/**
	 * Método que verifica se o tema a ser cadastrado já existe no banco.
	 * @param tema
	 * @return um Optional vazio caso esse tema já contenha no banco
	 */
	public Optional<Tema> salvarTema(Tema tema){
		Optional<Tema> temaExistente = repository.findByDescricao(tema.getDescricao());
		if(temaExistente.isPresent()) {
			return Optional.empty();
		}else {
			return Optional.ofNullable(repository.save(tema));
		}
	
	}
}
