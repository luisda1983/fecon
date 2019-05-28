package es.ldrsoftware.core.spt.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad DOMI - Dominios
 * @author Luis David
 *
 */
@Repository(value = "domiDao")
public class DomiDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Domi> getList(long inst) {
		TypedQuery<Domi> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT D FROM Domi D"
						+ " WHERE D.inst = " + inst
						+ " ORDER BY D.iden"
				         , Domi.class);
		List<Domi> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Domi getByIden(long inst, long iden) {
		TypedQuery<Domi> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT D FROM Domi D"
				        + " WHERE D.inst = " + inst + " " 
				        + "   AND D.iden = " + iden + " "
				         , Domi.class);
		List<Domi> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Domi getByNomb(long inst, String nomb) {
		TypedQuery<Domi> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT D FROM Domi D"
				        + " WHERE D.inst = " + inst 
				        + "   AND D.nomb = '" + nomb + "' "
				         , Domi.class);
		List<Domi> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Domi getByDesc(long inst, String desc) {
		TypedQuery<Domi> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT D FROM Domi D"
				        + " WHERE D.inst = " + inst 
				        + "   AND D.desc = '" + desc + "' "
				         , Domi.class);
		List<Domi> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Domi save(Domi domi) {
		return getEntityManager().merge(domi);
	}
}
