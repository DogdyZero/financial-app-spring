package br.com.financial_app.persistency.queries;

import br.com.financial_app.domain.EntidadeDominio;


public interface IFactoryQuery {
	public IStrategyQuery createObjQuery(EntidadeDominio entidade);
}
