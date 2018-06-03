package es.ldrsoftware.core.sts.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad STME - Estad�sticas mensuales de ejecuci�n
 * @author Luis David
 *
 */
@Repository(value = "stmeDao")
public class StmeDao extends BaseDAO {

	public final static String ORDER_CTRL      = "ORD-01";
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Stme save(Stme stme) {
		return getEntityManager().merge(stme);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Stme> getListByAnyoMess(String orde, int anyo, int mess) {
		String q = "SELECT S FROM Stme S "
			   +   " WHERE S.anyo = " + anyo + " "
			   +   "   AND S.mess = " + mess + " ";
		q = setOrderBy(orde, q);
		
		TypedQuery<Stme> typedQuery = this.getEntityManager().createQuery(q, Stme.class);
		
		daoSetCont("stmeDao", typedQuery);
		List<Stme> resultList = typedQuery.getResultList();
		return resultList.subList(0, daoListLength("stmeDao", resultList));
	}

	private String setOrderBy(String orde, String q) {
		if (ORDER_CTRL.equals(orde)) {
			q = q + "ORDER BY S.ctrl ASC ";
		} 
		return q;
	}
}
