package br.com.financial_app.persistence;

import br.com.financial_app.domain.EntidadeDominio;
import br.com.financial_app.persistency.queries.TransacaoQuery;

public class TransacaoDAO extends AbstractDAO{
	public TransacaoDAO(EntidadeDominio entidade) {
		super.entidade = entidade;
	}
}
