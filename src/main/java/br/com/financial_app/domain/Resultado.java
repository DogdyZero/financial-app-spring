package br.com.financial_app.domain;

public class Resultado extends EntidadeDominio {
	private String mensagem;

	
	public Resultado(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
