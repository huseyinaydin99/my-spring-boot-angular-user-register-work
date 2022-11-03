package com.user.beans.impl;

import java.net.URISyntaxException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.user.beans.Beans;
import com.user.dao.IEntityRepository;
import com.user.dao.impl.UserDaoImpl;
import com.user.hibernate.util.HibernateUtil;
import com.user.service.IUserService;
import com.user.service.impl.UserServiceImpl;

@Configuration
//@EnableWebMvc
@EnableTransactionManagement
//@ComponentScan(basePackages = {"com.huseyinaydin"})
public class BeansImpl implements Beans {

	@Autowired
	private ApplicationContext appCtx;
	
	@Bean(name = "driverManagerDataSource")
	@Scope(scopeName = "singleton")
	@Override
	public DriverManagerDataSource getDriverManagerDataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		driverManagerDataSource.setUrl(
				"jdbc:mysql://localhost:3306/usercrud?createDatabaseIfNotExist=true");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("toor");
		return driverManagerDataSource;
	}

	@Bean(name = "localSessionFactory")
	@Scope(scopeName = "singleton")
	@Override
	public LocalSessionFactoryBean getLocalSessionFactoryBean() throws URISyntaxException {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource((DriverManagerDataSource) appCtx.getBean("driverManagerDataSource"));
		//localSessionFactoryBean.setMappingLocations(new ClassPathResource("/com/huseyinaydin/person/Person.hbm.xml"));
		localSessionFactoryBean.setPackagesToScan("com.user.model.impl");
		Properties hibernateProperties = new Properties();
		
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		hibernateProperties.put("hibernate.show_sql", "true");
		hibernateProperties.put("hibernate.hbm2ddl.auto", "create");
		hibernateProperties.put("hibernate.cache.region_prefix", "hibernate.test");
		hibernateProperties.put("hibernate.cache.use_query_cache", "true");
		hibernateProperties.put("hibernate.cache.use_second_level_cache", "true");
		hibernateProperties.put("hibernate.cache.use_structured_entries", "true");
		hibernateProperties.put("hibernate.cache.provider_class", "net.sf.ehcache.EhCacheProvider");
		hibernateProperties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
		//hibernateProperties.put("net.sf.ehcache.configurationResourceName", "ehcache.xml");
		
		localSessionFactoryBean.setHibernateProperties(hibernateProperties);
		
		Properties properties = localSessionFactoryBean.getHibernateProperties();
		System.err.println(properties.getProperty("hibernate.cache.region.factory_class"));
		return localSessionFactoryBean;
	}
	
	@Bean(name = "hibernateUtil")
	@Scope(scopeName = "prototype")
	@Override
	public HibernateUtil getHibernateUtil() {
		HibernateUtil hibernateUtil = new HibernateUtil();
		hibernateUtil.setSessionFactory((SessionFactory) appCtx.getBean("localSessionFactory"));
		hibernateUtil.setSession(hibernateUtil.getSessionFactory().openSession());
		return hibernateUtil;
	}
	
	@Bean(name = "userDao")
	@Scope(scopeName = "prototype")
	public UserDaoImpl getUserDao() {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		return userDaoImpl;
	}
	
	@Bean(name = "userService")
	@Scope(scopeName = "prototype")
	public IUserService getUserService() {
		IUserService iUserService = new UserServiceImpl();
		return iUserService;
	}
	
	@Bean(name = "modelAndView")
	@Scope(scopeName = "prototype")
	@Override
	public ModelAndView getModelAndView() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
	/*@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/add").allowedOrigins("http://localhost:4200");
				registry.addMapping("/api/update").allowedOrigins("http://localhost:4200");
				registry.addMapping("/api/delete").allowedOrigins("http://localhost:4200");
				registry.addMapping("/api/getUser").allowedOrigins("http://localhost:4200");
				registry.addMapping("/api/getId/").allowedOrigins("http://localhost:4200");
				registry.addMapping("/api/getAll").allowedOrigins("http://localhost:4200");
			}
			
		};
	}*/

	public ApplicationContext getAppCtx() {
		return appCtx;
	}

	public void setAppCtx(ApplicationContext appCtx) {
		this.appCtx = appCtx;
	}

}
