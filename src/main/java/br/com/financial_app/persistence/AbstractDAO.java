package br.com.financial_app.persistence;

import java.lang.reflect.Method;
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
import br.com.financial_app.repository.UsuarioRepository;

@Service
public abstract class AbstractDAO implements IDAO{
	private int contador =1;
	protected String tipoConsulta;
	protected IFactoryQuery fabricaQuery;
	protected EntidadeDominio entidade;
	protected SessionFactory sessionFactory;
	protected Session session;
	
	protected EntidadeRepository<?> repository;
	
	protected void iniciarTransacao() {
		SessionConfigApplication ss = SessionConfigApplication.getInstanceSession();
		this.session = ss.getInstanceSessionFactory().openSession();
		this.session.beginTransaction();
	}
	protected void finalizarTransacao() {
		this.session.getTransaction().commit();
		this.session.flush();
		this.session.close();
	}
	
	
	private Usuario testeCast(EntidadeDominio entidade) {
		
		return Usuario.class.cast(entidade);
	}
	
	@Override
	public String salvar(EntidadeDominio entidade) {
		
		try {
			Class<?> classe = repository.getClass();
			
			Method m = classe.getMethod("save", Object.class);
			m.invoke(repository, entidade);
			
//			iniciarTransacao();
//			this.session.save(entidade);
//			finalizarTransacao();
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
			
//			iniciarTransacao();
//			this.session.update(entidade);
//
//			finalizarTransacao();
		} catch (Exception e) {
			return e.toString();
		}

		return null;
	}


	public List<EntidadeDominio> consulta(EntidadeDominio entidade) {
		iniciarTransacao();
		this.fabricaQuery = FactoryQuery.getInstance(entidade);
		IStrategyQuery strategyQuery= this.fabricaQuery.createObjQuery(entidade);
		
		Query<EntidadeDominio> query = session.createQuery(
				strategyQuery.gerarString(getTipoConsulta()));
		
		List<Object> listaObj = strategyQuery.retornoParametros();
		contador =1;
		if(listaObj!=null) {
			for (Object ob :listaObj) {
				String parametro = "param"+contador;
				
				query.setParameter(parametro, ob);
				contador++;
			}
		}

        List<EntidadeDominio> resultado =query.list();
        finalizarTransacao();

        if(resultado.size()==0) {
        	return null;
        }
		return resultado;
	}
	
	public String getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
}
