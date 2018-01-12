package es.ldrsoftware.fecon.prp.entity;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad CONC - Conceptos
 * @author Luis David
 *
 */
@Repository(value = "concDao")
public class ConcDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Conc> getList(long inst) {
		
		TypedQuery<Conc> query = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Conc C "
						+ " WHERE C.inst = " + inst + " "
						+ " ORDER BY C.orde ASC "
						, Conc.class);
		
		List<Conc> resultList = query.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Conc> getListByCate(long inst, long cate) {
		
		TypedQuery<Conc> query = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Conc C "
						+ " WHERE C.inst = " + inst + " "
						+ "   AND C.cate = " + cate + " "
						+ " ORDER BY C.orde ASC "
						, Conc.class);
		
		List<Conc> resultList = query.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Conc getByIden(long inst, long iden) {
		
		TypedQuery<Conc> query = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Conc C "
						+ " WHERE C.inst = " + inst + " "
						+ "   AND C.iden = " + iden + " "
						+ " ORDER BY C.orde ASC "
						, Conc.class);
		
		List<Conc> resultList = query.getResultList();
		
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Conc getByCateDesc(long inst, long cate, String desc) {
		
		TypedQuery<Conc> query = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Conc C "
						+ " WHERE C.inst = " + inst + " "
						+ "   AND C.cate = " + cate + " "
						+ "   AND C.desc = '" + desc + "' "
						+ " ORDER BY C.orde ASC "
						, Conc.class);
		
		List<Conc> resultList = query.getResultList();
		
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Conc getByCateDesl(long inst, long cate, String desl) {
		
		TypedQuery<Conc> query = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Conc C "
						+ " WHERE C.inst = " + inst + " "
						+ "   AND C.cate = " + cate + " "
						+ "   AND C.desl = '" + desl + "' "
						+ " ORDER BY C.orde ASC "
						, Conc.class);
		
		List<Conc> resultList = query.getResultList();
		
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int getOrde(long inst, long cate) {
		
		Query query = 
				this.getEntityManager().createQuery(
				          "SELECT max(C.orde) FROM Conc C "
						+ " WHERE C.inst = " + inst + " "
						+ "   AND C.cate = " + cate + " "
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
	public Conc save(Conc conc) {
		return getEntityManager().merge(conc);
	}
}
