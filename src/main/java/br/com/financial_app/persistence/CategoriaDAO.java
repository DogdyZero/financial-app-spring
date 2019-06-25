package br.com.financial_app.persistence;

import br.com.financial_app.domain.EntidadeDominio;
import br.com.financial_app.persistency.queries.CategoriaQuery;

public class CategoriaDAO extends AbstractDAO{
	public CategoriaDAO(EntidadeDominio entidade) {
		super.entidade = entidade;
	}
}
