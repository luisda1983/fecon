package ldrsoftware.app.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import ldrsoftware.app.domain.HPrep;
import ldrsoftware.app.dao.IHPrepDAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de operaciones disponible sobre la tabla THPREP.
 * @author Luis David
 *
 */
@Repository(value = "thprepDao")
public class HPrepDAO extends DAOEntityManager implements IHPrepDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public HPrep getByIden(long inst, int iden) {
		String query =  " SELECT P FROM HPrep P ";
		query = query + "  WHERE P.inst = " + inst + " ";
		query = query + "    AND P.iden = " + iden + " ";
		
		TypedQuery<HPrep> typedQuery = this.getEntityManager().createQuery(query, HPrep.class);
		List<HPrep> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<HPrep> getListByAnua(long inst, int anua) {
		
		String query = " SELECT P FROM HPrep P, HCate Ca ";
		query = query + " WHERE P.inst = " + inst + " ";
		query = query + "   AND P.anua = " + anua + " ";
		query = query + "   AND P.inst = Ca.inst ";
		query = query + "   AND P.cate = Ca.iden ";
		query = query + " ORDER BY Ca.orde ASC ";
		
		TypedQuery<HPrep> q = this.getEntityManager().createQuery(query, HPrep.class);
		List<HPrep> resultList = q.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<HPrep> getListByAnuaCate(long inst, int anua, int cate, boolean conc) {
		
		String query = " SELECT P FROM HPrep P ";
		query = query + " WHERE P.inst = " + inst + " ";
		query = query + "   AND P.anua = " + anua + " "; 
		query = query + "   AND P.cate = " + cate + " ";
		if (conc) {
			query = query + " AND P.conc > 0 ";
		} else {
			query = query + " AND P.conc = 0 ";
		}
		query = query + " ORDER BY P.conc ASC ";
					
		TypedQuery<HPrep> q = this.getEntityManager().createQuery(query, HPrep.class);
		List<HPrep> resultList = q.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public void save(HPrep prep) {
		getEntityManager().merge(prep);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public void delete(HPrep prep) {
		getEntityManager().remove(prep);
	}
}
