package br.com.financial_app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="categoria")
public class Categoria extends EntidadeDominio {
	@Id
	@GeneratedValue
	@Column(name="cat_id")
	public int id;
	@Column(name="cat_tipo")
	public String tipoCategoria;
	
	public Categoria() {}
	
	public Categoria(String tipoCategoria) {
		super();
		this.tipoCategoria = tipoCategoria;
	}
	public String getTipoCategoria() {
		return tipoCategoria;
	}
	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}
	
	
}
