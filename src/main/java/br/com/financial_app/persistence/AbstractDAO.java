package br.com.financial_app.persistence;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.financial_app.domain.EntidadeDominio;
import br.com.financial_app.repository.EntidadeRepository;

@Service
public abstract class AbstractDAO implements IDAO{
	protected String tipoConsulta;
	protected EntidadeDominio entidade;
	
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

	/*
	 * (non-Javadoc)
	 * @see br.com.financial_app.persistence.IDAO#consulta(br.com.financial_app.domain.EntidadeDominio)
	 * o método busca todos os métodos que tem a assinatura semelhante ao getTipoConsulta no repositório
	 */

	public List<EntidadeDominio> consulta(EntidadeDominio entidade) {
		
		List<EntidadeDominio> entidades =  new ArrayList<>();
		try {
			Object obj = invokeGenericsMethods();
			if(obj!=null) {
				if(obj.getClass().equals(ArrayList.class)) {
					return (List<EntidadeDominio>) obj;
				} else {
					entidades.add((EntidadeDominio) obj);
					return entidades;
				}
			} else {
				return null;
			}

			
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private Object invokeGenericsMethods() throws NoSuchMethodException, SecurityException, 
	IllegalAccessException, IllegalArgumentException, InvocationTargetException {
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
		// caso seja métodos herdados da JpaRepositorio, tipo findById / findByAll não será localizado
		for(Class<?> inter: interfaces) {
			if(inter.getName().contains(classeEntidade.getSimpleName())) {
				nomeRepositorio = inter;
			}
		}
		// caso não localize o 
		if(nomeRepositorio==null)
			return null;
		
		// pegar todos os métodos da classe da entidade
		Method[] metodos = classeEntidade.getDeclaredMethods();
		
		// separar a string de entrada quando encontrar 'And'
		String[] parametros = param.split("And|Or");

		
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
		Object[] invocarMetodos =null;
		Method method;
		
		// invocar o método de entrada do repositorio identificado
		if(i!=0) {
			 invocarMetodos = new Object[parametros.length];
			i=parametros.length-1;
			for(String s : objMetodos) {
				Method m = classeEntidade.getMethod(s);
				invocarMetodos[i] = m.invoke(entidade);
				i--;
			}
			method = nomeRepositorio.getMethod(tipoParametro,objType);
			return method.invoke(repository, invocarMetodos);
		} else {
			tipoParametro = "find"+param;
			method = nomeRepositorio.getMethod(tipoParametro);
			return method.invoke(repository);
			
		}

//		if(resultado !=null) {
//			return resultado;
//		}
		
//		return null;
		
	}
	
	public String getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
}
