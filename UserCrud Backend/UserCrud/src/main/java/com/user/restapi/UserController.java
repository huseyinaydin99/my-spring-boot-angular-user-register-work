package com.user.restapi;

import java.util.List;

import com.user.dao.IEntityRepository;
import com.user.model.impl.UserImpl;

public interface UserController {
	void add(UserImpl user);
	boolean delete(UserImpl user);
	public boolean delete(Long id);
	boolean update(UserImpl user);
	UserImpl get(UserImpl user);
	UserImpl get(Long id);
	List<UserImpl> getAll();
}
