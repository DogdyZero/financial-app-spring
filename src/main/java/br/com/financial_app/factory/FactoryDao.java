package br.com.financial_app.factory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.financial_app.domain.Categoria;
import br.com.financial_app.domain.EntidadeDominio;
import br.com.financial_app.domain.Transacao;
import br.com.financial_app.domain.Usuario;
import br.com.financial_app.persistence.CategoriaDAO;
import br.com.financial_app.persistence.IDAO;
import br.com.financial_app.persistence.TransacaoDAO;
import br.com.financial_app.persistence.UsuarioDAO;

@Service
public class FactoryDao implements IFactoryDao {

	private Map<String,IDAO> daos;
	
	@Autowired
	private UsuarioDAO usuarioDao;
	@Autowired
	private CategoriaDAO categoriaDao;
	@Autowired
	private TransacaoDAO transacaoDao;
	
	public FactoryDao() {
		daos = new HashMap<String,IDAO>();
	}
	
	private void startMaps() {
		daos.put(Usuario.class.getName(), usuarioDao);
		daos.put(Categoria.class.getName(), categoriaDao);
		daos.put(Transacao.class.getName(), transacaoDao);
	}
	
	@Override
	public IDAO getDaoInstance(EntidadeDominio entidade) {
		startMaps();
		return daos.get(entidade.getClass().getName());
	}

}
