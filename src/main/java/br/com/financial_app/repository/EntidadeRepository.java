package br.com.financial_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.financial_app.domain.EntidadeDominio;

@NoRepositoryBean
public interface EntidadeRepository<T extends EntidadeDominio> extends JpaRepository<T, Long> {

}
