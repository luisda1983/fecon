package es.ldrsoftware.core.fwk.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad AVIS - Avisos
 * @author Luis David
 *
 */
@Repository(value = "avisDao")
public class AvisDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Avis getByIden(long iden) {
		TypedQuery<Avis> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT A FROM Avis A"
				        + " WHERE A.iden = " + iden + " "
				         , Avis.class);
		List<Avis> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Avis> getListByEstaInstUsuaPerf(String esta, long inst, String usua, String perf) {
		TypedQuery<Avis> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT A FROM Avis A"
				        + " WHERE A.esta = '" + esta + "'"
				        + "   AND ((A.dest = 'GN' AND A.inst = 0            AND A.usua = ''             AND A.perf = '' ) "
				        + "    OR  (A.dest = 'IN' AND A.inst = " + inst + " AND A.usua = ''             AND A.perf = '' ) "
				        + "    OR  (A.dest = 'PF' AND A.inst = " + inst + " AND A.usua = ''             AND A.perf = '" + perf + "' ) "
				        + "    OR  (A.dest = 'US' AND A.inst = " + inst + " AND A.usua = '" + usua + "' AND A.perf = '' ) "
				        + "    OR  (A.dest = 'UG' AND A.inst = 0            AND A.usua = '" + usua + "' AND A.perf = '' ) "
				        + "    OR  (A.dest = 'PG' AND A.inst = 0            AND A.usua = ''             AND A.perf = '" + perf + "' ) )"
				         , Avis.class);
		List<Avis> resultList = typedQuery.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public void save(Avis avis) {
		getEntityManager().merge(avis);
	}
}
