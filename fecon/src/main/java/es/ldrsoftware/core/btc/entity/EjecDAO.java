package es.ldrsoftware.core.btc.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;
import es.ldrsoftware.core.fwk.data.LiteData;

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
				        + " ORDER BY E.hora, E.orde ASC, E.secu ASC"
				         , Ejec.class);
		List<Ejec> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Ejec> getListByFeplHopl(int fepl, int hopl) {
		TypedQuery<Ejec> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT E FROM Ejec E"
				        + " WHERE E.fepl = " + fepl + " "
				        + "   AND E.hopl = " + hopl + " "
				        + " ORDER BY E.fech, E.hora, E.orde ASC, E.secu ASC"
				         , Ejec.class);
		List<Ejec> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Ejec> getListByFechHoraEsta(int fech, int hora, String esta) {
		TypedQuery<Ejec> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT E FROM Ejec E"
				        + " WHERE E.fech = " + fech + " "
				        + "   AND E.hora = " + hora + " "
				        + "   AND E.esta = '" + esta + "' "
				        + " ORDER BY E.orde ASC"
				         , Ejec.class);
		List<Ejec> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Ejec> getListPendingExecution(int fech, int hora) {
		TypedQuery<Ejec> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT E FROM Ejec E"
				        + " WHERE E.esta = '" + LiteData.LT_EL_EJECESTA_PENDIENTE + "' "
				        + "   AND ((E.fech = " + fech + " AND E.hora <= " + hora + ") "
				        + "    OR  (E.fech < " + fech + ")) "
				        + " ORDER BY E.fech, E.hora, E.orde ASC"
				         , Ejec.class);
		List<Ejec> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Ejec save(Ejec ejec) {
		return getEntityManager().merge(ejec);
	}
}
