package br.com.financial_app.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("br.com.financial_app")
@SpringBootApplication
public class FinancialApp2Application {

	public static void main(String[] args) {
		SpringApplication.run(FinancialApp2Application.class, args);
	}

}
