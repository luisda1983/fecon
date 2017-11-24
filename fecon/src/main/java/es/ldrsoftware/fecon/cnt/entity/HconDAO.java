package es.ldrsoftware.fecon.cnt.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad HCON - Histórico Contable
 * @author Luis David
 *
 */
@Repository(value = "hconDao")
public class HconDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Hcon getByIden(long inst, long iden) {
		TypedQuery<Hcon> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT H FROM Hcon H"
				        + " WHERE H.inst = " + inst + " "
				        + "   AND H.iden = " + iden + " "
				         , Hcon.class);
		List<Hcon> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Hcon save(Hcon hcon) {
		return getEntityManager().merge(hcon);
	}
}
