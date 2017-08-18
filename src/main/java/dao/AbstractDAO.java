package dao;

import java.lang.annotation.Inherited;
import java.util.Date;
import java.util.List;

import javax.ejb.EJBException;
import javax.persistence.EntityManager;

import db.PersistenceUnit;
import model.BasicEntity;

/**
 * Implementação de {@link DAO}
 * @author Gabriel Vieira - gabrielvra@outlook.com
 * @param <T> Entidade persistente
 */
public abstract class AbstractDAO<T extends BasicEntity> implements DAO<T> {
	
	private EntityManager entityManager;
	
	public AbstractDAO() {
		PersistenceUnit.init();
		entityManager = PersistenceUnit.getEntityManager();
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	/**
	 * {@link Inherited}
	 */
	public T persist(T entity) throws EJBException {
		entity.setCriado(new Date());
		//Controle de criação e atualização do registro
		if (entity.getId() != null){
			entity.setAtualizado(new Date());
		} else { 
			entity.setCriado(new Date());
		}
		entityManager.getTransaction().begin();
		try {
			atualizar(entity);
			entityManager.persist(entity);
			entityManager.flush();
			entityManager.refresh(entity);
		} finally {
			entityManager.getTransaction().commit();
		}
		return entity;
	}

	protected abstract void atualizar(T entity);
	
	/**
	 * {@link Inherited}
	 */
	public T findById(Long id) {
		T entity = entityManager.find(getEntityClass(), id);
		return entity;
	}

	/**
	 * {@link Inherited}
	 */
	public void delete(T entity) {
		entityManager.remove(entity);
	}

	/**
	 * {@link Inherited}
	 */
	public List<T> findAll() {
		return null;
	}

	/**
	 * {@link Inherited}
	 */
	public void deleteAll() {
		List<T> entityList = findAll();
		for (T entity : entityList) {
			delete(entity);
		}
	}	
		
	public abstract Class<T> getEntityClass();	
}