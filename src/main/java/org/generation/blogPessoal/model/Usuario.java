package org.generation.blogPessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_usuarios")
public class Usuario {
	
	
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
	
	private @NotNull(message="Não é permitido nulo") @Size(min = 2, max = 80) String nome;
	
	private @NotNull(message="Não é permitido nulo") @Size(min = 5, max = 80) String usuario;
	
	private @NotNull @Size(min = 5, max = 100) String senha;
	
	@OneToMany(mappedBy = "user", cascade =  CascadeType.ALL)
	@JsonIgnoreProperties("user")
	private List<Postagem> post;
	
	public Usuario(String nome, String usuario, String senha) {
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	
	public List<Postagem> getPost() {
		return post;
	}
	public void setPost(List<Postagem> post) {
		this.post = post;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
