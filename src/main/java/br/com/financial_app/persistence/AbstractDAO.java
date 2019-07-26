package br.com.financial_app.persistence;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import br.com.financial_app.domain.EntidadeDominio;
import br.com.financial_app.domain.Usuario;
import br.com.financial_app.persistency.queries.FactoryQuery;
import br.com.financial_app.persistency.queries.IFactoryQuery;
import br.com.financial_app.persistency.queries.IStrategyQuery;
import br.com.financial_app.repository.EntidadeRepository;

@Service
public abstract class AbstractDAO implements IDAO{
	private int contador =1;
	protected String tipoConsulta;
	protected IFactoryQuery fabricaQuery;
	protected EntidadeDominio entidade;
	protected SessionFactory sessionFactory;
	protected Session session;
	
	protected EntidadeRepository<?> repository;
	
	
	@Override
	public String salvar(EntidadeDominio entidade) {
		
		try {
			Class<?> classe = repository.getClass();
			
			Method m = classe.getMethod("save", Object.class);
			m.invoke(repository, entidade);

		} catch (Exception e) {
			return e.toString();
		}

		return null;
	}
	@Override
	public String alterar(EntidadeDominio entidade) {
		try {
			Class<?> classe = repository.getClass();
			
			Method m = classe.getMethod("save", Object.class);
			m.invoke(repository, entidade);
			
		} catch (Exception e) {
			return e.toString();
		}

		return null;
	}


	public List<EntidadeDominio> consulta(EntidadeDominio entidade) {
		List<EntidadeDominio> entidades =  new ArrayList<>();
		// pegar a classe que entro repositorio para pegar a interface
		Class<?> classeRepositorio = repository.getClass();
		Class<?>[] interfaces = classeRepositorio.getInterfaces();

		// criar o tipo da consulta no repositório
		String param = this.tipoConsulta;
		String tipoParametro = "findBy"+param;
		
		// descobrir a classe da entidade
		Class<?> classeEntidade = entidade.getClass();
		Class<?> nomeRepositorio = null;
		
		// verificar se na interface tem o nome da classe
		// OBS: como a consulta é pelo nome da classe, poderá ocorrer problemas, caso exista
		// classes com nomes iguais em pacotes diferentes
		// caso não exista o repositorio ocorrerá erro
		for(Class<?> inter: interfaces) {
			if(inter.getName().contains(classeEntidade.getSimpleName())) {
				nomeRepositorio = inter;
			}
		}
		
		// pegar todos os métodos da classe da entidade
		Method[] metodos = classeEntidade.getDeclaredMethods();
		
		// separar a string de entrada quando encontrar 'And'
		String[] parametros = param.split("And");
		parametros = param.split("Or");

		
		String[] objMetodos = new String[parametros.length];
		Class<?>[] objType= new Class<?>[parametros.length];
		
		// procurar todos os métodos gets similar aos parametros de entrada
		int i =0;
		for(Method m : metodos) {
			for(String p : parametros) {
				if(m.getName().contains("get"+p)) {
					try {
						objMetodos[i] =m.getName();
						objType[i]=m.getReturnType();
						i++;

					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
				}
			}	
		}
		// criar array e invocar os metodos
		Object[] invocarMetodos = new Object[parametros.length];
		i=parametros.length-1;
		for(String s : objMetodos) {
			try {
				Method m = classeEntidade.getMethod(s);
				invocarMetodos[i] = m.invoke(entidade);
				i--;
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		try {
			// invocar o método de entrada do repositorio identificado
			Method method = nomeRepositorio.getMethod(tipoParametro,objType);
			
			Object resultado = method.invoke(repository, invocarMetodos);
			
			if(resultado !=null) {
				
				entidades.add((EntidadeDominio) resultado);
				return entidades;
			}
			
			return null;
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public String getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
}
