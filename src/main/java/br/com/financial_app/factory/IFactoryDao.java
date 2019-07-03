package br.com.financial_app.factory;

import br.com.financial_app.domain.EntidadeDominio;
import br.com.financial_app.persistence.IDAO;

public interface IFactoryDao {
	IDAO getDaoInstance(EntidadeDominio entidade);

}
