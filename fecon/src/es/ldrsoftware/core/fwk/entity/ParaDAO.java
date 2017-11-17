package es.ldrsoftware.core.fwk.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad Para - Parametros de aplicación
 * @author Luis David
 *
 */
@Repository(value = "paraDao")
public class ParaDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Para getByTblaClav(String tbla, String clav) {
		TypedQuery<Para> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT P FROM Para P"
				        + " WHERE P.tbla = '" + tbla + "' "
				        + "   AND P.clav = '" + clav + "' "
				         , Para.class);
		List<Para> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Para> getListByTbla(String tbla) {
		TypedQuery<Para> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT P FROM Para P"
				        + " WHERE P.tbla = '" + tbla + "' "
				         , Para.class);
		List<Para> resultList = typedQuery.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Para save(Para para) {
		return getEntityManager().merge(para);
	}
}
