package es.ldrsoftware.core.btc.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad MPLA - Maestro de Planificación
 * @author Luis David
 *
 */
@Repository(value = "mplaDao")
public class MplaDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Mpla getByIden(int hora) {
		TypedQuery<Mpla> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT M FROM Mpla M"
				        + " WHERE M.hora = " + hora + " "
				         , Mpla.class);
		List<Mpla> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Mpla getGreaterByHora(int hora) {
		TypedQuery<Mpla> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT M FROM Mpla M"
				        + " WHERE M.hora > " + hora + " "
				        + "   AND M.esta = 'A' "
				        + " ORDER BY M.hora"
				         , Mpla.class);
		typedQuery.setMaxResults(1);
		List<Mpla> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Mpla> getList() {
		TypedQuery<Mpla> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT M FROM Mpla M"
				        + " ORDER BY M.hora"
				         , Mpla.class);
		List<Mpla> resultList = typedQuery.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Mpla save(Mpla mpla) {
		return getEntityManager().merge(mpla);
	}
}
