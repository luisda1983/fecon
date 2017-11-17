package es.ldrsoftware.core.oui.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad INST - Información de instalación
 * @author Luis David
 *
 */
@Repository(value = "instDao")
public class InstDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Inst getByIden(long iden) {
		TypedQuery<Inst> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT I FROM Inst I"
				        + " WHERE I.iden = " + iden + " "
				         , Inst.class);
		List<Inst> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Inst save(Inst inst) {
		return getEntityManager().merge(inst);
	}
}
