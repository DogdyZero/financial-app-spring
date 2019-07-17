package br.com.financial_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.financial_app.domain.EntidadeDominio;
import br.com.financial_app.domain.Resultado;
import br.com.financial_app.domain.Usuario;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	
	@Autowired
	private Facade facade;
	
	@CrossOrigin
	@PostMapping("/salvar") 
	public ResponseEntity<Resultado> salvarUsuario(@RequestBody Usuario usuario) {
		String resultado = facade.salvar(usuario);
		if(resultado==null)
			return new ResponseEntity<Resultado>(HttpStatus.CREATED);
		else 
			return new ResponseEntity<Resultado>(HttpStatus.BAD_REQUEST);
		
	}
	
	@CrossOrigin
	@GetMapping("/teste") 
	public Resultado teste() {
		return new Resultado("Teste efetuado com sucesso!");
	}
	

	@CrossOrigin
	@PostMapping("/login") 
	public Usuario validarSenha(@RequestBody Usuario usuario) {
		List<EntidadeDominio> resultado = facade.buscar(usuario, "LoginAndSenha");
		if(resultado != null) {
//			return new ResponseEntity<Resultado>(HttpStatus.OK);
			Usuario u = (Usuario) resultado.get(0);
			return u;
		}else {
//			return new ResponseEntity<Resultado>(HttpStatus.UNAUTHORIZED);
			return null;
		}
	}
			
}
