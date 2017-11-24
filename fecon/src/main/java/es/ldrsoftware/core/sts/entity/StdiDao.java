package es.ldrsoftware.core.sts.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad STDI - Estadísticas diarias de ejecución
 * @author Luis David
 *
 */
@Repository(value = "stdiDao")
public class StdiDao extends BaseDAO {
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Stdi> getListBetweenFech(int fein, int fefi) {
		TypedQuery<Stdi> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT S FROM Stdi S"
				        + " WHERE S.fech BETWEEN " + fein + " AND " + fefi + " "
						+ " ORDER BY S.ctrl ASC, S.fech ASC"
				         , Stdi.class);
		List<Stdi> resultList = typedQuery.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public void save(Stdi stdi) {
		getEntityManager().merge(stdi);
	}
}
