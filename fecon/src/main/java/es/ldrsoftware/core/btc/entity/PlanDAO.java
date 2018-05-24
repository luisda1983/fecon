package es.ldrsoftware.core.btc.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad PLAN - Planificación de Batch
 * @author Luis David
 *
 */
@Repository(value = "planDao")
public class PlanDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Plan getByFechHora(int fech, int hora) {
		TypedQuery<Plan> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT P FROM Plan P"
				        + " WHERE P.fech = " + fech + " "
				        + "   AND P.hora = " + hora + " "
				         , Plan.class);
		List<Plan> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Plan> getListByFech(int fech) {
		TypedQuery<Plan> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT P FROM Plan P"
						+ " WHERE P.fech = " + fech + " "
				        + " ORDER BY P.hora"
				         , Plan.class);
		List<Plan> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Plan> getListByFechProc(int fech) {
		TypedQuery<Plan> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT P FROM Plan P"
						+ " WHERE P.fech = " + fech + " "
						+ "   AND P.proc > 0 "
				        + " ORDER BY P.hora"
				         , Plan.class);
		List<Plan> resultList = typedQuery.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Plan save(Plan plan) {
		return getEntityManager().merge(plan);
	}
}
