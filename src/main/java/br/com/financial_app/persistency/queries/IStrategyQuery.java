package br.com.financial_app.persistency.queries;

import java.util.List;

import br.com.financial_app.domain.EntidadeDominio;

public interface IStrategyQuery {
	public String gerarString(String tipoConsulta);
	public List<Object> retornoParametros();
	public void setMaps(EntidadeDominio entidade);
}
