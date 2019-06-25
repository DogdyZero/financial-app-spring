package br.com.financial_app.domain;

public enum TipoTransacao {
	Entrada("Entrada"), Saida ("Saida");
	
	private String descricao;
    
    public String getDescricao(){
          return this.descricao;
    }
   
    private TipoTransacao(String descricao){
          this.descricao = descricao;
    }
	
}
