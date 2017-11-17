package ldrsoftware.app.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import ldrsoftware.app.domain.HParm;
import ldrsoftware.app.dao.IHParmDAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de operaciones disponible sobre la tabla THPARM.
 * @author Luis David
 *
 */
@Repository(value = "thparmDao")
public class HParmDAO extends DAOEntityManager implements IHParmDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public HParm getByTblaClav(String tbla, String clav) {
		TypedQuery<HParm> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT P FROM HParm P"
						+ " WHERE P.tbla = '" + tbla + "'"
						+ "   AND P.clav = '" + clav + "'"
				         , HParm.class);
		List<HParm> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<HParm> getListByTbla(String tbla) {
		TypedQuery<HParm> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT P FROM HParm P"
						+ " WHERE P.tbla = '" + tbla + "'"
						+ " ORDER BY P.orde ASC"
				         , HParm.class);
		List<HParm> resultList = typedQuery.getResultList();
		return resultList;
	}
}
