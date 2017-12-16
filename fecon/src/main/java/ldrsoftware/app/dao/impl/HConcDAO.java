package ldrsoftware.app.dao.impl;


import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ldrsoftware.app.domain.HConc;
import ldrsoftware.app.dao.IHConcDAO;
import ldrsoftware.app.dao.search.ConcSearch;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de operaciones disponible sobre la tabla THCONC.
 * @author Luis David
 *
 */
@Repository(value = "thconcDao")
public class HConcDAO extends DAOEntityManager implements IHConcDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<HConc> getList(long inst, ConcSearch search) {
		String query =  "  SELECT C FROM HConc C ";
		query = query + "   WHERE C.inst = " + inst + " ";
		if (search.isActi()) {
			query = query + " AND C.acti = true ";
		}
		if (search.getCate() > 0) {
			query = query + " AND C.cate = " + search.getCate() + " ";
		}
		query = query + "   ORDER BY C.orde ASC";
		
		TypedQuery<HConc> typedQuery = this.getEntityManager().createQuery(query, HConc.class);
		List<HConc> resultList = typedQuery.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public HConc getByIden(long inst, long iden) {
		String query =  "  SELECT C FROM HConc C ";
		query = query + "   WHERE C.inst = " + inst + " ";
		query = query + "     AND C.iden = " + iden + " ";
		
		TypedQuery<HConc> typedQuery = this.getEntityManager().createQuery(query, HConc.class);
		List<HConc> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
		
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public HConc getByCateDesc(long inst, int cate, String desc) {
		String query =  "  SELECT C FROM HConc C ";
		query = query + "   WHERE C.inst = " + inst + " ";
		query = query + "     AND C.cate = " + cate + " ";
		query = query + "     AND C.desc = '" + desc + "' ";
		
		TypedQuery<HConc> typedQuery = this.getEntityManager().createQuery(query, HConc.class);
		List<HConc> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public HConc getByCateOrde(long inst, int cate, int orde) {
		String query =  "  SELECT C FROM HConc C ";
		query = query + "   WHERE C.inst = " + inst + " ";
		query = query + "     AND C.cate = " + cate + " ";
		query = query + "     AND C.orde = " + orde + " ";
		
		TypedQuery<HConc> typedQuery = this.getEntityManager().createQuery(query, HConc.class);
		List<HConc> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public void save(HConc hConc) {
		getEntityManager().merge(hConc);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int getOrde(long inst, int cate) {
		
		String query =  " SELECT MAX(C.orde) FROM HConc C ";
		query = query + "  WHERE C.inst = " + inst + " ";
		query = query + "    AND C.cate = " + cate + " ";
		Query q = this.getEntityManager().createQuery(query);
		
		@SuppressWarnings("unchecked")
		List<Object> resultList = (List<Object>)q.getResultList();
		if (resultList != null && resultList.size() > 0 && resultList.get(0) != null) { 
			int orde = (Integer)resultList.get(0);
			return orde + 1;
		} else {
			return 1;
		}
	}
}
