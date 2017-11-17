package ldrsoftware.app.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import ldrsoftware.app.dao.IHMensDAO;
import ldrsoftware.app.domain.HMens;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de operaciones disponible sobre la tabla THMENS.
 * @author Luis David
 *
 */
@Repository(value = "thmensDao")
public class HMensDAO extends DAOEntityManager implements IHMensDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public HMens getByIden(String iden) {
		TypedQuery<HMens> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT M FROM HMens M"
						+ " WHERE M.iden = '" + iden + "'"
				         , HMens.class);
		List<HMens> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
}
