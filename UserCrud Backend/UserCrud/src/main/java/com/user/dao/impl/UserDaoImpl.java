package com.user.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.user.dao.IEntityRepository;
import com.user.hibernate.util.HibernateUtil;
import com.user.model.impl.UserImpl;

@Repository
public class UserDaoImpl implements IEntityRepository<UserImpl> {
	
	@Autowired
	@Qualifier("hibernateUtil")
	private HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}

	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}
	
	@Override
	public void save(UserImpl object) {
		this.hibernateUtil.getSession().save(object);
		System.out.println("cba " + object);
		System.out.println("user dao impl save çalıştı");
	}

	@Override
	public boolean delete(UserImpl object) {
		Long id = object.getUserId();
		Criteria criteria = this.hibernateUtil.getSession().createCriteria(UserImpl.class);
		criteria.add(Restrictions.eq("userId", id));
		List<UserImpl> users = criteria.list();
		UserImpl userImpl = users.get(0);
		System.out.println("aaa " + userImpl);
		this.hibernateUtil.getSessionFactory().getCurrentSession().delete(object);
		return true;
	}


	@Override
	public boolean update(UserImpl object) {
		this.hibernateUtil.getSessionFactory().getCurrentSession().update(object);
		return true;
	}


	@Override
	public UserImpl get(UserImpl object) {
		Long id = object.getUserId();
		Criteria criteria = this.hibernateUtil.getSession().createCriteria(UserImpl.class);
		criteria.add(Restrictions.eq("userId", id));
		List<UserImpl> users = criteria.list();
		UserImpl userImpl = users.get(0);
		return userImpl;
	}


	@Override
	public UserImpl get(Long id) {
		Criteria criteria = this.hibernateUtil.getSession().createCriteria(UserImpl.class);
		criteria.add(Restrictions.eq("userId", id));
		List<UserImpl> users = criteria.list();
		UserImpl userImpl = users.get(0);
		return userImpl;
	}


	@Override
	public List<UserImpl> getAll() {
		Criteria criteria = this.hibernateUtil.getSessionFactory().getCurrentSession().createCriteria(UserImpl.class);
		return criteria.list();
	}

	@Override
	public boolean delete(Long id) {
		Criteria criteria = this.hibernateUtil.getSession().createCriteria(UserImpl.class);
		criteria.add(Restrictions.eq("userId", id));
		List<UserImpl> users = criteria.list();
		UserImpl userImpl = users.get(0);
		System.out.println("aaa " + userImpl);
		this.hibernateUtil.getSessionFactory().getCurrentSession().delete(userImpl);
		return true;
	}

}
