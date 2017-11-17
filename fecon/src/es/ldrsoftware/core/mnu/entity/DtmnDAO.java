package es.ldrsoftware.core.mnu.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad DTMN - Detalle de menú.
 *
 */
@Repository(value = "dtmnDao")
public class DtmnDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Dtmn> getListByCtmn(int ctmn) {
		TypedQuery<Dtmn> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT D FROM Dtmn D"
						+ " WHERE D.ctmn = " + ctmn
						+ " ORDER BY D.orde"
				         , Dtmn.class);
		List<Dtmn> resultList = typedQuery.getResultList();
		return resultList;
	}
}
