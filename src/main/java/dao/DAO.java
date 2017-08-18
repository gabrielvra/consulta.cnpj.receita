package dao;

import java.util.List;

import model.BasicEntity;

/**
 * Definir os métodos disponíveis em um DAO
 * @author Gabriel Vieira - gabrielvra@outlook.com
 *
 * @param <T> uma entidade
 */
public interface DAO<T extends BasicEntity> {

	/**
	 * Salva uma entidade
	 * @param entity
	 */
	T persist(T entity);
	
	/**
	 * Pesquisar um campo pelo ID da entidade
	 * @param id
	 * @return Objeto da entidade
	 */
	T findById(Long id);
	
	/**
	 * Deleta uma determinada entidade
	 * @param entity
	 */
	void delete(T entity);
	
	/**
	 * Listar todos os registros da entidade
	 * @return {@link List}
	 */
	List<T> findAll();
	
	/**
	 * Deleta todos os registros
	 */
	void deleteAll();
}