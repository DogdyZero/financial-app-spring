package br.com.financial_app.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.financial_app.domain.EntidadeDominio;
import br.com.financial_app.persistency.queries.FactoryQuery;
import br.com.financial_app.persistency.queries.IFactoryQuery;
import br.com.financial_app.persistency.queries.IStrategyQuery;

public abstract class AbstractDAO implements IDAO{
	private int contador =1;
	protected String tipoConsulta;
	protected IFactoryQuery fabricaQuery;
	protected EntidadeDominio entidade;
	protected SessionFactory sessionFactory;
	protected Session session;

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
	
	@Override
	public String salvar(EntidadeDominio entidade) {
		try {
			iniciarTransacao();
			this.session.save(entidade);
			finalizarTransacao();
		} catch (Exception e) {
			return e.toString();
		}

		return null;
	}
	@Override
	public String alterar(EntidadeDominio entidade) {
		try {
			iniciarTransacao();
			this.session.update(entidade);

			finalizarTransacao();
		} catch (Exception e) {
			return e.toString();
		}

		return null;
	}


	public List<EntidadeDominio> consulta(EntidadeDominio entidade) {
		iniciarTransacao();
		this.fabricaQuery = FactoryQuery.getInstance(entidade);
		IStrategyQuery strategyQuery= this.fabricaQuery.createObjQuery(entidade);
		
		Query query = session.createQuery(
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
