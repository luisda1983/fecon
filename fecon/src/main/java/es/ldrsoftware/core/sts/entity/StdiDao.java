package es.ldrsoftware.core.sts.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad STDI - Estad�sticas diarias de ejecuci�n
 * @author Luis David
 *
 */
@Repository(value = "stdiDao")
public class StdiDao extends BaseDAO {

	public final static String ORDER_CTRL      = "ORD-01";
	public final static String ORDER_CTRL_FECH = "ORD-02";
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Stdi> getListBetweenFech(String orde, int fein, int fefi) {
		String q = "SELECT S FROM Stdi S "
			   +   " WHERE S.fech BETWEEN " + fein + " AND " + fefi + " ";
		q = setOrderBy(orde, q);
		
		TypedQuery<Stdi> typedQuery = this.getEntityManager().createQuery(q, Stdi.class);
		
		daoSetCont("stdiDao", typedQuery);
		List<Stdi> resultList = typedQuery.getResultList();
		return resultList.subList(0, daoListLength("stdiDao", resultList));
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Stdi> getListByFech(String orde, int fech) {
		String q = "SELECT S FROM Stdi S "
			   +   " WHERE S.fech = " + fech + " ";
		
		q = setOrderBy(orde, q);
		
		TypedQuery<Stdi> typedQuery = this.getEntityManager().createQuery(q, Stdi.class);
		
		daoSetCont("stdiDao", typedQuery);
		List<Stdi> resultList = typedQuery.getResultList();
		return resultList.subList(0, daoListLength("stdiDao", resultList));
	}

	private String setOrderBy(String orde, String q) {
		if (ORDER_CTRL.equals(orde)) {
			q = q + "ORDER BY S.ctrl ASC ";
		} else if (ORDER_CTRL_FECH.equals(orde)) {
			q = q + "ORDER BY S.ctrl ASC, S.fech ASC";
		}
		return q;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Stdi save(Stdi stdi) {
		return getEntityManager().merge(stdi);
	}
}
