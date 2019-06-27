package br.com.financial_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.financial_app.domain.EntidadeDominio;
import br.com.financial_app.domain.Resultado;
import br.com.financial_app.domain.Usuario;

@RestController
public class UsuarioController {
	@Autowired
	private ApplicationContext context;
	
	@CrossOrigin
	@PostMapping("/salvar") 
	public Resultado salvarUsuario(@RequestBody Usuario usuario) {
		Facede facede = Facede.getInstance(usuario);
		String resultado = facede.salvar(usuario);
		if(resultado==null)
			return new Resultado("Sucesso");
		else
			return new Resultado("Erro ao processar requisição!");
	}
	@CrossOrigin
	@GetMapping("/teste") 
	public Resultado teste() {
		return new Resultado("Teste efetuado com sucesso!");
	}
	
	
	@CrossOrigin
	@PostMapping("/login") 
	public Resultado validarSenha(@RequestBody Usuario usuario) {
		System.out.println("login:" + usuario.getLogin() + " e Senha: "+ usuario.getSenha());
		Facede facede = Facede.getInstance(usuario);
		List<EntidadeDominio> resultado = facede.listaEntidades(usuario, "login");
		if(resultado != null) {
			return new Resultado("Login Efetuado com sucesso!");
		}
		return new Resultado("Usuario ou senha não localizados");
	}
		

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}
	
	
}
