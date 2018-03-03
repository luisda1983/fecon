package es.ldrsoftware.core.mnu.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad DTMN - Detalle de men�.
 *
 */
@Repository(value = "dtmnDao")
public class DtmnDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Dtmn> getListByCtmn(long ctmn) {
		TypedQuery<Dtmn> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT D FROM Dtmn D"
						+ " WHERE D.ctmn = " + ctmn
						+ " ORDER BY D.orde"
				         , Dtmn.class);
		List<Dtmn> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Dtmn> getListByCtmnActi(long ctmn, String acti) {
		TypedQuery<Dtmn> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT D FROM Dtmn D"
						+ " WHERE D.ctmn = " + ctmn + " "
						+ "   AND D.acti = '" + acti + "'"
						+ " ORDER BY D.orde"
				         , Dtmn.class);
		List<Dtmn> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Dtmn getByCtmnIden(long ctmn, long iden) {
		TypedQuery<Dtmn> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT D FROM Dtmn D"
				        + " WHERE D.ctmn = " + ctmn + " " 
				        + "   AND D.iden = " + iden + " "
				         , Dtmn.class);
		List<Dtmn> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Dtmn save(Dtmn dtmn) {
		return getEntityManager().merge(dtmn);
	}
}
