package br.com.financial_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.financial_app.domain.EntidadeDominio;
import br.com.financial_app.factory.IFactoryDao;

@Service
public class Facade {
	
	@Autowired
	private IFactoryDao factoryDao;

	public String salvar(EntidadeDominio entidade) {
//		String nomeClasse = entidade.getClass().getName();
//		
//		String msg = daos.get(nomeClasse).salvar(entidade);
//		if(msg!=null) {
//			return msg;
//		}
		
		return factoryDao.getDaoInstance(entidade).salvar(entidade);
	}

	public String alterar(EntidadeDominio entidade) {
//		String nomeClasse = entidade.getClass().getName();
//		String msg = null;
//		
//		msg = daos.get(nomeClasse).alterar(entidade);
//		if(msg!=null) {
//			return msg;
//		}
//			return null;
//		}
		return null;
	}	
	

	public List<EntidadeDominio> buscar(EntidadeDominio entidade, String tipoConsulta) {
//		String nomeClasse = entidade.getClass().getName();
//		daos.get(nomeClasse).setTipoConsulta(tipoConsulta);
//		List<EntidadeDominio> resultado = daos.get(nomeClasse).consulta(entidade);
//		if(resultado==null) {
//			return null;
//		} else {
//			return resultado;
//		}
		return null;
	}
}
