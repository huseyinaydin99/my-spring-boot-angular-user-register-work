package com.user.beans;

import java.net.URISyntaxException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.ModelAndView;

import com.user.hibernate.util.HibernateUtil;

public interface Beans {
	public DriverManagerDataSource getDriverManagerDataSource();
	public LocalSessionFactoryBean getLocalSessionFactoryBean() throws URISyntaxException;
	public HibernateUtil getHibernateUtil();
	public ModelAndView getModelAndView();
}
