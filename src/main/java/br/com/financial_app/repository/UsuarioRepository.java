package br.com.financial_app.repository;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.financial_app.domain.Usuario;

@Transactional @Repository
public interface UsuarioRepository extends EntidadeRepository<Usuario> {
	public Usuario findByLoginAndSenha(String login,String senha);
}
