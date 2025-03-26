package com.api.proyecto.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import com.api.proyecto.utiles.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public abstract class AbstractDao<T> implements Dao<T> {

	private Class<T> clazz;
	private EntityManager entityManager = EntityManagerUtil.getEntityManager();

	public Optional<T> get(long id) {
		T res = entityManager.find(clazz, id);
		return Optional.ofNullable(res);
	}

	public List<T> getAll() {
		String qlString = "FROM " + clazz.getName();
		Query query = entityManager.createQuery(qlString);
		return query.getResultList();
	}

	public void update(T t) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));
	}

	public void delete(long id) {
		T entidad = entityManager.find(clazz, id);
		if (entidad != null) {
			executeInsideTransaction(entityManager -> entityManager.remove(entidad));
		}
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public void save(T t) {
		executeInsideTransaction(entityManager -> entityManager.persist(t));
	}

	private void executeInsideTransaction(Consumer<EntityManager> action) {
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			action.accept(entityManager);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		}

	}
	public boolean existe(long id) {
		if(get(id)!=null) {
			return true;
		}
		
		return false;
		
	}

}
