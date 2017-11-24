package es.ldrsoftware.core.fwk.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad CTRL - Configuración de controladores
 * @author Luis David
 *
 */
@Repository(value = "ctrlDao")
public class CtrlDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Ctrl getByIden(String iden) {
		TypedQuery<Ctrl> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Ctrl C"
				        + " WHERE C.iden = '" + iden + "'"
				         , Ctrl.class);
		List<Ctrl> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
}
