# financial-app-spring

projeto que introduz o conceito do framework spring, o projeto ainda esta em desenvolvimento

Objetivo:
Elaborar uma pequena aplicação utilizando o framework spring afim de receber requisições restfull de um client angular.

Descrição:
-foi migrado o projeto jsf para o projeto spring, as principais mudanças adotas foi a controller que passou a ser rest, implementado a interface JpaRepository para consultas basicas no banco de dados, e um access token (JWT) para um teste com a camada de segurança

Este projeto foi hospedado inicialmente no heroku
requisição rest de teste para capturar o acess Token
requisição post, parametro objeto usuario, (login,senha);
url: https://financial-app-v2.herokuapp.com/oauth/token

Exemplo requisição httpClient - TypeScript

logar(usuario:Usuario){
	let url = 'https://financial-app-v2.herokuapp.com/oauth/token';
		
	let client='angular';
	let grant_type = 'password';
	let username = usuario.login;
	let password = usuario.senha;

	let body = `client=${client}&grant_type=${grant_type}&username=${username}&password=${password}`;

	this.http.post(url,body,

	{headers:{
		'Content-Type':'application/x-www-form-urlencoded',
		'Authorization':'Basic YW5ndWxhcjphbmd1bGFy'
		}
	}).subscribe(data=>{
		console.log(data);
	})
}
