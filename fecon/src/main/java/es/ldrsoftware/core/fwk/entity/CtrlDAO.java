package es.ldrsoftware.core.fwk.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad CTRL - Configuraci�n de controladores
 * @author Luis David
 *
 */
@Repository(value = "ctrlDao")
public class CtrlDAO extends BaseDAO {

	public final static String ORDER_IDEN      = "ORD-01";
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Ctrl> getList(String orde) {
		String q = "SELECT C FROM Ctrl C ";
		q = setOrderBy(orde, q);
		
		TypedQuery<Ctrl> typedQuery = this.getEntityManager().createQuery(q, Ctrl.class);
		
		daoSetCont("ctrlDao", typedQuery);
		List<Ctrl> resultList = typedQuery.getResultList();
		return resultList.subList(0, daoListLength("ctrlDao", resultList));
	}

	private String setOrderBy(String orde, String q) {
		if (ORDER_IDEN.equals(orde)) {
			q = q + "ORDER BY C.iden ASC ";
		}
		return q;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Ctrl getByIden(String iden) {
		TypedQuery<Ctrl> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Ctrl C"
				        + " WHERE C.iden = '" + iden + "'"
				         , Ctrl.class);
		List<Ctrl> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
}
