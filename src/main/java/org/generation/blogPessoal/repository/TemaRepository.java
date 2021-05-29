package org.generation.blogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long> {
	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
	//FindByAlgumacoisa busca no banco esse algumacoisa precisa estar com o nome do atributo do banco
	public Optional<Tema> findByDescricao(String descricao);
}
