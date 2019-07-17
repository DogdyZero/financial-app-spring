package br.com.financial_app.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.financial_app.domain.EntidadeDominio;
import br.com.financial_app.domain.Usuario;
import br.com.financial_app.repository.UsuarioRepository;

@Service
public class UsuarioDAO extends AbstractDAO implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public void setAbstractEntity(EntidadeDominio entidade) {
		super.entidade = entidade;
		super.repository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByLogin(nome);
		UserBuilder builder=null;
		if (usuario != null) {
		      builder = org.springframework.security.core.userdetails.User.withUsername(nome);
		      builder.password(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		      String [] regras = {"ADMIN"};
		      builder.roles(regras);
		    } else {
		      throw new UsernameNotFoundException("User not found.");
		    }

		    return builder.build();
	}
	
	
	
}
