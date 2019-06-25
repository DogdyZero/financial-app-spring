package br.com.financial_app.persistence;

import br.com.financial_app.domain.EntidadeDominio;
import br.com.financial_app.persistency.queries.UsuarioQuery;

public class UsuarioDAO extends AbstractDAO {
	
	public UsuarioDAO(EntidadeDominio entidade) {
		super.entidade = entidade;
	}
}
