package es.ldrsoftware.fecon.prp.entity;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad CATE - Categor�as de conceptos
 * @author Luis David
 *
 */
@Repository(value = "cateDao")
public class CateDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Cate> getList(long inst) {
		
		TypedQuery<Cate> query = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Cate C "
						+ " WHERE C.inst = " + inst + " "
						+ " ORDER BY C.orde ASC "
						, Cate.class);
		
		List<Cate> resultList = query.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Cate getByIden(long inst, long iden) {
		
		TypedQuery<Cate> query = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Cate C "
						+ " WHERE C.inst = " + inst + " "
						+ "   AND C.iden = " + iden + " "
						+ " ORDER BY C.orde ASC "
						, Cate.class);
		
		List<Cate> resultList = query.getResultList();
		
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Cate getByDesc(long inst, String desc) {
		
		TypedQuery<Cate> query = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Cate C "
						+ " WHERE C.inst = " + inst + " "
						+ "   AND C.desc = '" + desc + "' "
						+ " ORDER BY C.orde ASC "
						, Cate.class);
		
		List<Cate> resultList = query.getResultList();
		
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Cate getByDesl(long inst, String desl) {
		
		TypedQuery<Cate> query = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Cate C "
						+ " WHERE C.inst = " + inst + " "
						+ "   AND C.desl = '" + desl + "' "
						+ " ORDER BY C.orde ASC "
						, Cate.class);
		
		List<Cate> resultList = query.getResultList();
		
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int getOrde(long inst) {
		
		Query query = 
				this.getEntityManager().createQuery(
				          "SELECT max(C.orde) FROM Cate C "
						+ " WHERE C.inst = " + inst + " "
						);
		
		@SuppressWarnings("unchecked")
		List<Object> resultList = (List<Object>)query.getResultList();
		if (resultList != null && resultList.size() > 0 && resultList.get(0) != null) { 
			int orde = (Integer)resultList.get(0);
			return orde + 1;
		} else {
			return 1;
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Cate save(Cate cate) {
		return getEntityManager().merge(cate);
	}
}
