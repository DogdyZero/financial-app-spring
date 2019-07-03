# financial-app-spring

projeto que introduz o conceito do framework spring, o projeto ainda esta em desenvolvimento

Objetivo:
Elaborar uma aplicação utilizando o framework spring utilizando a RestController para receber as requisições de um projeto angular
na qual simula uma aplicação de gerenciamento financeiro.

Pontos a alterar:
O projeto foi elaborado a partir de uma aplicação JSF, portanto as queries ainda estão no padrão hibernate adotadas no projeto JSF,
ou seja, o projeto não implementa a interface JPARepository nas DAOs, não tem as configurações na propeties e sim ainda na 
hibernate.cfg.xml

Este projeto foi hospedado inicialmente no heroku
requisição rest de teste
requisição post, parametro objeto usuario, (login,senha);
url: https://financial-app-v2.herokuapp.com/login

Exemplo requisição httpClient - TypeScript

logar(usuario:Usuario){
		let url = 'https://financial-app-v2.herokuapp.com/login';
		
		this.http.post(url,{
			'login':usuario.login,
			'senha':usuario.senha
		},{headers:{
			'Accept':'application/json'}
		}).subscribe(data =>{
			console.log(data);
		})
}
