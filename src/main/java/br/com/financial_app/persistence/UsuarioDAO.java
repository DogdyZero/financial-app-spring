package br.com.financial_app.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
	@Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByLogin(nome);
		return new User(nome, usuario.getSenha(),getPermissoes(usuario));
	}
	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario){
		Set<SimpleGrantedAuthority> permissoes = new HashSet<>();
		permissoes.add(new SimpleGrantedAuthority("ROLE_CADASTRAR"));
		return permissoes;
		
	}
	
	
	
}
