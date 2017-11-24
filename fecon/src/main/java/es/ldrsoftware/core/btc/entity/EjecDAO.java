package es.ldrsoftware.core.btc.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad EJEC - Ejecuciones de procesos Batch
 * @author Luis David
 *
 */
@Repository(value = "ejecDao")
public class EjecDAO extends BaseDAO {
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Ejec> getListByFech(int fech) {
		TypedQuery<Ejec> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT E FROM Ejec E"
				        + " WHERE E.fech = " + fech + " "
				        + " ORDER BY E.orde ASC"
				         , Ejec.class);
		List<Ejec> resultList = typedQuery.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Ejec> getListByFechEsta(int fech, String esta) {
		TypedQuery<Ejec> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT E FROM Ejec E"
				        + " WHERE E.fech = " + fech + " "
				        + "   AND E.esta = '" + esta + "' "
				        + " ORDER BY E.orde ASC"
				         , Ejec.class);
		List<Ejec> resultList = typedQuery.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Ejec save(Ejec ejec) {
		return getEntityManager().merge(ejec);
	}
}
