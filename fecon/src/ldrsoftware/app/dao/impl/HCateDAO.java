package ldrsoftware.app.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ldrsoftware.app.domain.HCate;
import ldrsoftware.app.dao.IHCateDAO;
import ldrsoftware.app.dao.search.CateSearch;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de operaciones disponible sobre la tabla THCATE.
 * @author Luis David
 *
 */
@Repository(value = "thcateDao")
public class HCateDAO extends DAOEntityManager implements IHCateDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<HCate> getList(long inst, CateSearch cateSearch) {
		String query =  "  SELECT C FROM HCate C ";
		query = query + "   WHERE C.inst = " + inst + " ";
		if (cateSearch.isActi()) {
			query = query + " AND C.acti = true ";
		}
		query = query + "   ORDER BY C.orde ASC";
		
		TypedQuery<HCate> typedQuery = this.getEntityManager().createQuery(query, HCate.class);
		List<HCate> resultList = typedQuery.getResultList();
		return resultList;
	}
			
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public HCate getByIden(long inst, int iden) {
		String query =  " SELECT C FROM HCate C ";
		query = query + "  WHERE C.inst = " + inst + " ";
		query = query + "    AND C.iden = " + iden + " ";
		
		TypedQuery<HCate> typedQuery = this.getEntityManager().createQuery(query, HCate.class);
		List<HCate> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public HCate getByDesc(long inst, String desc) {
		String query =  " SELECT C FROM HCate C ";
		query = query + "  WHERE C.inst = " + inst + " ";
		query = query + "    AND C.desc = '" + desc + "' ";
		
		TypedQuery<HCate> typedQuery = this.getEntityManager().createQuery(query, HCate.class);
		List<HCate> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
		
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public HCate getByOrde(long inst, int orde) {
		String query =  " SELECT C FROM HCate C ";
		query = query + "  WHERE C.inst = " + inst + " ";
		query = query + "    AND C.orde = " + orde + " ";
		
		TypedQuery<HCate> typedQuery = this.getEntityManager().createQuery(query, HCate.class);
		List<HCate> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public void save(HCate cateBean) {
		getEntityManager().merge(cateBean);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int getOrde(long inst) {
		String query =  " SELECT MAX(C.orde) FROM HCate C ";
		query = query + "  WHERE C.inst = " + inst + " ";
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
