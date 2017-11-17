package es.ldrsoftware.core.fwk.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad NOTF - Notificaciones.
 * @author Luis David
 *
 */
@Repository(value = "notfDao")
public class NotfDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Notf getByIden(String iden) {
		TypedQuery<Notf> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT N FROM Notf N"
				        + " WHERE N.iden = '" + iden + "'"
				         , Notf.class);
		List<Notf> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
}
