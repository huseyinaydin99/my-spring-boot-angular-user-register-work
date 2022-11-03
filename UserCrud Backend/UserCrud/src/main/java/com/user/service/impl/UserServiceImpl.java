package com.user.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.user.dao.impl.UserDaoImpl;
import com.user.model.impl.UserImpl;
import com.user.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	@Qualifier("userDao")
	private UserDaoImpl userDao;
	
	@Override
	public void save(UserImpl object) {
		this.userDao.save(object);
		System.out.println("user service save çalıştı");
	}

	@Override
	public boolean delete(UserImpl object) {
		return this.userDao.delete(object);
	}

	@Override
	public boolean update(UserImpl object) {
		return this.userDao.update(object);
	}

	@Override
	public UserImpl get(UserImpl object) {
		return this.userDao.get(object);
	}

	@Override
	public UserImpl get(Long id) {
		return this.userDao.get(id);
	}

	@Override
	public List<UserImpl> getAll() {
		return this.userDao.getAll();
	}

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean delete(Long id) {
		return this.userDao.delete(id);
	}

}
