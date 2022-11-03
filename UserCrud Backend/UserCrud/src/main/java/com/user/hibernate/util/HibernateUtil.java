package com.user.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class HibernateUtil {

	@Autowired
	@Qualifier("localSessionFactory")
	private SessionFactory sessionFactory;
	private Session session;

	public HibernateUtil() {
		super();
		if (this.sessionFactory != null)
			this.session = this.sessionFactory.openSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		if(this.sessionFactory.isClosed())
			this.session = this.sessionFactory.openSession();
		/*else
			this.session = this.sessionFactory.getCurrentSession();*/
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
