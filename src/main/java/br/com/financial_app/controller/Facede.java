package br.com.financial_app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.financial_app.domain.Categoria;
import br.com.financial_app.domain.EntidadeDominio;
import br.com.financial_app.domain.Transacao;
import br.com.financial_app.domain.Usuario;
import br.com.financial_app.persistence.CategoriaDAO;
import br.com.financial_app.persistence.IDAO;
import br.com.financial_app.persistence.TransacaoDAO;
import br.com.financial_app.persistence.UsuarioDAO;

public class Facede {
	private static Facede facede;
	private Map<String,IDAO> daos;
	
	private Facede(EntidadeDominio entidade) {
		daos = new HashMap<String, IDAO>();

		daos.put(Categoria.class.getName(), new CategoriaDAO(entidade));
		daos.put(Usuario.class.getName(), new UsuarioDAO(entidade));
		daos.put(Transacao.class.getName(), new TransacaoDAO(entidade));
		
	}
	
	public static Facede getInstance(EntidadeDominio entidade) {
		if(facede==null) {
			facede = new Facede(entidade);
		} 		
		return facede;
	}
	public String salvar(EntidadeDominio entidade) {
		String nomeClasse = entidade.getClass().getName();
		
		String msg = daos.get(nomeClasse).salvar(entidade);
		if(msg!=null) {
			return msg;
		}
		return null;
	}

	public String alterar(EntidadeDominio entidade) {
		String nomeClasse = entidade.getClass().getName();
		String msg = null;
		
		msg = daos.get(nomeClasse).alterar(entidade);
		if(msg!=null) {
			return msg;
		}
			return null;
		}
		
	

	public List<EntidadeDominio> listaEntidades(EntidadeDominio entidade, String tipoConsulta) {
		String nomeClasse = entidade.getClass().getName();
		daos.get(nomeClasse).setTipoConsulta(tipoConsulta);
		List<EntidadeDominio> resultado = daos.get(nomeClasse).consulta(entidade);
		if(resultado==null) {
			return null;
		} else {
			return resultado;
		}
	}
}
