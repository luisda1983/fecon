package es.ldrsoftware.core.sts.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad STST - Estadísticas de ejecución
 * @author Luis David
 *
 */
@Repository(value = "ststDao")
public class StstDao extends BaseDAO {
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public void save(Stst stst) {
		getEntityManager().merge(stst);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Stst> getListByFeej(int feej) {
		TypedQuery<Stst> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT S FROM Stst S"
				        + " WHERE S.feej = " + feej + " "
						+ " ORDER BY S.ctrl ASC, S.iden ASC"
				         , Stst.class);
		List<Stst> resultList = typedQuery.getResultList();
		return resultList;
	}
}
