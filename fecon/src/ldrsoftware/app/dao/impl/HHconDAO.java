package ldrsoftware.app.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import ldrsoftware.app.domain.HHcon;
import ldrsoftware.app.dao.IHHconDAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de operaciones disponible sobre la tabla THHCON.
 * @author Luis David
 *
 */
@Repository(value = "thhconDao")
public class HHconDAO extends DAOEntityManager implements IHHconDAO {
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public void save(HHcon hcon) {
		getEntityManager().merge(hcon);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<HHcon> getList(int fdes, int fhas, int cate, int conc) {
		TypedQuery<HHcon> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT H FROM HHcon H"
						+ " WHERE H.fval >= " + fdes
						+ "   AND H.fval <= " + fhas
						+ "   AND (H.cate = " + cate + " OR 0 = " + cate + ")"
						+ "   AND (H.conc = " + conc + " OR 0 = " + conc + ")"
						+ "   AND H.tipo NOT IN ('T', 'A')"
						+ " ORDER BY H.fval asc, H.fope asc, H.hope, H.iden"
				         , HHcon.class);
		List<HHcon> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public HHcon getByIden(int iden) {
		TypedQuery<HHcon> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT H FROM HHcon H"
						+ " WHERE H.iden = " + iden
				         , HHcon.class);
		List<HHcon> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
}
