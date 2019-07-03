package br.com.financial_app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name="usuario")
public class Usuario extends EntidadeDominio {
	
	@Id
	@GeneratedValue
	@Column(name="usu_id")
	private long id;
	
	@Column(name="usu_login")
	private String login;
	
	@Column(name="usu_senha")
	private String senha;
	
	
	public Usuario() {
	}

	public Usuario(String nome, String senha) {
		this.login = nome;
		this.senha = senha;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String nome) {
		this.login = nome;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
