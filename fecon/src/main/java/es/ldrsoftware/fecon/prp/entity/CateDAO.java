package es.ldrsoftware.fecon.prp.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad CATE - Categorías de conceptos
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

	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Cate save(Cate cate) {
		return getEntityManager().merge(cate);
	}
}
