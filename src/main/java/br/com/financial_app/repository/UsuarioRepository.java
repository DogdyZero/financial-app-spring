package br.com.financial_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.financial_app.domain.Usuario;

@Repository 
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
	
}
