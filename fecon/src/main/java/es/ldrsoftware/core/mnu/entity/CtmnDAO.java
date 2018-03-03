package es.ldrsoftware.core.mnu.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad CTMN - Categor�a de Menu.
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

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Ctmn> getListByPerfActi(String perf, String acti) {
		TypedQuery<Ctmn> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Ctmn C"
				        + " WHERE C.perf = '" + perf + "'"
				        + "   AND C.acti = '" + acti + "'"
						+ " ORDER BY C.orde"
				         , Ctmn.class);
		List<Ctmn> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Ctmn getByIden(long iden) {
		TypedQuery<Ctmn> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Ctmn C"
				        + " WHERE C.iden = " + iden + " "
				         , Ctmn.class);
		List<Ctmn> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Ctmn save(Ctmn ctmn) {
		return getEntityManager().merge(ctmn);
	}
}
