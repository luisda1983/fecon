package es.ldrsoftware.core.mnu.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad CTMN - Categoría de Menu.
 * @author Luis David
 *
 */
@Repository(value = "ctmnDao")
public class CtmnDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Ctmn> getListByPerf(String perf) {
		TypedQuery<Ctmn> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Ctmn C"
				        + " WHERE C.perf = '" + perf + "'"
						+ " ORDER BY C.orde"
				         , Ctmn.class);
		List<Ctmn> resultList = typedQuery.getResultList();
		return resultList;
	}
}
