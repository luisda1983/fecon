package es.ldrsoftware.core.btc.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad BTCH - Configuración de procesos Batch
 * @author Luis David
 *
 */
@Repository(value = "btchDao")
public class BtchDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Btch getByIden(String iden) {
		TypedQuery<Btch> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT B FROM Btch B"
				        + " WHERE B.iden = '" + iden + "'"
				         , Btch.class);
		List<Btch> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Btch> getListByPlan(String plan) {
		TypedQuery<Btch> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT B FROM Btch B"
				        + " WHERE B.plan = '" + plan + "'"
				         , Btch.class);
		List<Btch> resultList = typedQuery.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Btch save(Btch btch) {
		return getEntityManager().merge(btch);
	}
}
