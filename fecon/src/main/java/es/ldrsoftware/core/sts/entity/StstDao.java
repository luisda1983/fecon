package es.ldrsoftware.core.sts.entity;


import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad STST - Estad�sticas de ejecuci�n
 * @author Luis David
 *
 */
@Repository(value = "ststDao")
public class StstDao extends BaseDAO {
	
	public final static String ORDER_CTRL_IDEN = "ORD-01";
	public final static String ORDER_IDEN      = "ORD-02";
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Stst save(Stst stst) {
		return getEntityManager().merge(stst);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Stst> getListByFeej(String orde, int feej) {
		String q = "SELECT S FROM Stst S "
			   +   " WHERE S.feej = " + feej + " ";
		
		if (ORDER_IDEN.equals(orde)) {
		   q = q + " ORDER BY s.iden ASC ";
		} else {
		   q = q + " ORDER BY s.ctrl ASC, s.iden ASC ";
		}
		
		TypedQuery<Stst> typedQuery = this.getEntityManager().createQuery(q, Stst.class);
		
		daoSetCont("ststDao", typedQuery);
		List<Stst> resultList = typedQuery.getResultList();
		return resultList.subList(0, daoListLength("ststDao", resultList));
	}
}
