package br.com.financial_app.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="transacao")
public class Transacao extends EntidadeDominio {
	@Id
	@GeneratedValue
	@Column(name="tra_id")
	private int id;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="tra_data")
	private Date data;
	
	@Column(name="tra_tipo")
	private TipoTransacao tipoTransacao;
	
	@Column(name="tra_forma")
	private FormaPagamento formaPagamento;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cat_id")
	private Categoria categoria;
	
	@Column(name="tra_valor")
	private double valor;
	
	@Column(name="tra_obs")
	private String obs;
	
	public Transacao() {
		super();
	}
	public Transacao(Date data, TipoTransacao tipoTransacao, FormaPagamento formaPagamento, Categoria categoria,
			double valor, String obs) {
		super();
		this.data = data;
		this.tipoTransacao = tipoTransacao;
		this.formaPagamento = formaPagamento;
		this.categoria = categoria;
		this.valor = valor;
		this.obs = obs;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	
	
	
}
