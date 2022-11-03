package com.user.dao;

import java.util.List;

import com.user.model.IEntity;
import com.user.model.impl.UserImpl;

public interface IEntityRepository<T extends IEntity> {
	void save(T object);
	boolean delete(T object);
	boolean update(T object);
	UserImpl get(T object);
	UserImpl get(Long id);
	List<UserImpl> getAll();
	public boolean delete(Long id);
}
