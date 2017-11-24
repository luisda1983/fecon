package es.ldrsoftware.core.arq;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

/**
 * Clase que incluye al atributo y setter EntityManager,
 * requerido por los objetos de acceso a BBDD.
 * 
 * @author Luis David
 *
 */
@Component
public class BaseDAO {

	@PersistenceContext
	EntityManager em;
	
	/**
	 * Getter.
	 * @return EntityManager
	 */
	public EntityManager getEntityManager() {
		return this.em;
	}
	
	/**
	 * Setter.
	 * @param em EntityManager.
	 */
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
}
