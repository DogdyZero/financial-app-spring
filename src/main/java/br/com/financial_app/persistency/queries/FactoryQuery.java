package br.com.financial_app.persistency.queries;

import java.util.HashMap;
import java.util.Map;

import br.com.financial_app.domain.Categoria;
import br.com.financial_app.domain.EntidadeDominio;
import br.com.financial_app.domain.Transacao;
import br.com.financial_app.domain.Usuario;

public class FactoryQuery implements IFactoryQuery {
	private Map<String,IStrategyQuery> factory;
	private static FactoryQuery factoryQuery;
	
	private FactoryQuery(EntidadeDominio entidade) {
		createMaps(entidade);
	}
	
	public static IFactoryQuery getInstance(EntidadeDominio entidade) {
		if(factoryQuery== null) {
			factoryQuery = new FactoryQuery(entidade);
		}
		return factoryQuery;
	}
	private void createMaps(EntidadeDominio entidade) {
		if(factory==null)
			factory = new HashMap<String,IStrategyQuery>();
		
		factory.put(Categoria.class.getSimpleName(),new CategoriaQuery(entidade));
		factory.put(Transacao.class.getSimpleName(),new TransacaoQuery(entidade));
		factory.put(Usuario.class.getSimpleName(), new UsuarioQuery(entidade));
		
	}
	
	@Override
	public IStrategyQuery createObjQuery(EntidadeDominio entidade) {
		String nomeClasse= entidade.getClass().getSimpleName();
		factory.get(nomeClasse).setMaps(entidade);
		return factory.get(nomeClasse);
	}

}
