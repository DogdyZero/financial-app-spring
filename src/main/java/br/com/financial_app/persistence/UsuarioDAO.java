package br.com.financial_app.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.financial_app.domain.EntidadeDominio;
import br.com.financial_app.domain.Usuario;
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
	public List<EntidadeDominio> consulta(EntidadeDominio entidade) {
		List<EntidadeDominio> usuarios = new ArrayList<EntidadeDominio>();
		Usuario u = Usuario.class.cast(entidade);
		u = usuarioRepository.findByLoginAndSenha(u.getLogin(),u.getSenha());
		if(u!=null) {
			usuarios.add(u);
			return usuarios;
		} else {
			return null;
		}
	}
	
}
