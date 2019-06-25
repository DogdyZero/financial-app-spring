package br.com.financial_app.domain;

public enum FormaPagamento {
	Dinheiro("Dinheiro"),
	Crédito("Cartão de Crédito"), 
	Débito ("Cartão de Débito");
	
	private String descricao;
	
	private FormaPagamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao(){
        return this.descricao;
	}
	
}
