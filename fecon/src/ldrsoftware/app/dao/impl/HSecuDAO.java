package ldrsoftware.app.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import ldrsoftware.app.domain.HSecu;
import ldrsoftware.app.dao.IHSecuDAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de operaciones disponible sobre la tabla THSECU.
 * @author Luis David
 *
 */
@Repository(value = "thsecuDao")
public class HSecuDAO extends DAOEntityManager implements IHSecuDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public int getNext(long inst, String iden) {
		TypedQuery<HSecu> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT S FROM HSecu S"
						+ " WHERE S.inst = " + inst + " "
						+ "   AND S.iden = '" + iden + "' "
				         , HSecu.class);
		List<HSecu> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			HSecu secu = resultList.get(0);
			int i = secu.getValo();
			secu.setValo(secu.getValo()+1);
			this.getEntityManager().merge(secu);
			return i+1;
		} else {
			return -1;
		}
	}
}
