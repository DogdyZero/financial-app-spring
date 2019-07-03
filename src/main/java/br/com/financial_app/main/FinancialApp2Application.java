package br.com.financial_app.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.financial_app.repository.UsuarioRepository;

@EntityScan("br.com.financial_app.domain")
@ComponentScan("br.com.financial_app")
@EnableJpaRepositories("br.com.financial_app.repository")
@SpringBootApplication
public class FinancialApp2Application {

	public static void main(String[] args) {
		SpringApplication.run(FinancialApp2Application.class, args);
	}

}
