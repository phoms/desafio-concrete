package com.desafioconcrete.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.desafioconcrete.dto.TelefoneDto;
import com.desafioconcrete.dto.UsuarioDto;

/**
 * Entidade para representar um usuário do sistema.
 * 
 * @author Pedro Henrique
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 5492802008085045171L;

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "CHAR(32)")
	private String id;
	@Column(name = "nome", nullable = false)
	private String nome;
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	@Column(name = "senha", nullable = false)
	private String senha;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario", cascade = CascadeType.PERSIST)
	private List<Telefone> telefones;

	@Column(name = "data_criacao", nullable = false)
	private LocalDateTime dataCriacao;
	@Column(name = "data_modificacao", nullable = false)
	private LocalDateTime dataModificacao;
	@Column(name = "ultimo_login", nullable = true)
	private LocalDateTime ultimoLogin;
	@Column(name = "token", nullable = false)
	private String token;

	/**
	 * Construtor default da classe.
	 */
	public Usuario() {
	}
	
	/**
	 * Construtor da classe.
	 * 
	 * @param dto
	 */
	public Usuario(UsuarioDto dto) {
		nome = dto.getNome();
		email = dto.getEmail();
		senha = dto.getSenha();
		token = dto.getToken();
		
		telefones = new ArrayList<>();
		for (TelefoneDto telefoneDto : dto.getTelefones()) {
			Telefone telefone = new Telefone(telefoneDto);
			telefone.setUsuario(this);
			telefones.add(telefone);
		}
	}
	
	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return telefones
	 */
	public List<Telefone> getTelefones() {
		return telefones;
	}

	/**
	 * @param telefones
	 */
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	/**
	 * @return ultimoLogin
	 */
	public LocalDateTime getUltimoLogin() {
		return ultimoLogin;
	}

	/**
	 * @param ultimoLogin
	 */
	public void setUltimoLogin(LocalDateTime ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	/**
	 * @return dataCriacao
	 */
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	/**
	 * @param dataCriacao
	 */
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	/**
	 * @return dataModificacao
	 */
	public LocalDateTime getDataModificacao() {
		return dataModificacao;
	}

	/**
	 * @param dataModificacao
	 */
	public void setDataModificacao(LocalDateTime dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	/**
	 * @return token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}
}