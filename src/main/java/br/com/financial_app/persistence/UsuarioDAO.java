package br.com.financial_app.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.financial_app.domain.EntidadeDominio;
import br.com.financial_app.domain.Usuario;
import br.com.financial_app.repository.EntidadeRepository;
import br.com.financial_app.repository.UsuarioRepository;

@Service
public class UsuarioDAO extends AbstractDAO {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public void setAbstractEntity(EntidadeDominio entidade) {
		super.entidade = entidade;
		super.repository = usuarioRepository;
	}
}
