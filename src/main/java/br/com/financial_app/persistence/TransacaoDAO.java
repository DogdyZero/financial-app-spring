package br.com.financial_app.persistence;

import org.springframework.stereotype.Service;

import br.com.financial_app.domain.EntidadeDominio;

@Service
public class TransacaoDAO extends AbstractDAO{
	@Override
	public void setAbstractEntity(EntidadeDominio entidade) {
		super.entidade = entidade;
	}
}
