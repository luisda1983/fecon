package es.ldrsoftware.core.spt.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad DELE - Elementos de dominios
 * @author Luis David
 *
 */
@Repository(value = "deleDao")
public class DeleDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Dele> getListByDomi(long inst, String domi) {
		TypedQuery<Dele> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT D FROM Dele D"
						+ " WHERE D.inst = " + inst
						+ "   AND D.domi = '" + domi + "' "
						+ " ORDER BY D.iden"
				         , Dele.class);
		List<Dele> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Dele getByIden(long inst, long iden) {
		TypedQuery<Dele> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT D FROM Dele D"
				        + " WHERE D.inst = " + inst + " "
				        + "   AND D.iden = " + iden + " "
				         , Dele.class);
		List<Dele> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Dele getByDomiValo(long inst, String domi, String valo) {
		TypedQuery<Dele> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT D FROM Dele D"
				        + " WHERE D.inst = " + inst 
				        + "   AND D.domi = '" + domi + "' "
				        + "   AND D.valo = '" + valo + "' "
				         , Dele.class);
		List<Dele> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Dele save(Dele dele) {
		return getEntityManager().merge(dele);
	}
}
