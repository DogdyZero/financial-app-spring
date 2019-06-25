package br.com.financial_app.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionConfigApplication {
	private SessionFactory sessionFactory;
	private static SessionConfigApplication sessionConfig;
	
	private SessionConfigApplication(){
		if(sessionFactory == null || sessionFactory.isClosed()){
			this.sessionFactory = new Configuration().configure().buildSessionFactory();
		}
	}
	public static SessionConfigApplication getInstanceSession(){
		if(sessionConfig == null){
			sessionConfig = new SessionConfigApplication();
		}
		return sessionConfig;
		
	}
	public SessionFactory getInstanceSessionFactory(){
		return sessionFactory;
	}
}
