package es.ldrsoftware.core.fwk.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad SESI - Informaci�n persistente de sesi�n
 * @author Luis David
 *
 */
@Repository(value = "sesiDao")
public class SesiDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Sesi getByIden(long iden) {
		TypedQuery<Sesi> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT S FROM Sesi S"
				        + " WHERE S.iden = " + iden + " "
				         , Sesi.class);
		List<Sesi> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Sesi getByUsua(String usua) {
		TypedQuery<Sesi> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT S FROM Sesi S"
				        + " WHERE S.usua = '" + usua + "'"
				         , Sesi.class);
		List<Sesi> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Sesi save(Sesi sesi) {
		return getEntityManager().merge(sesi);
	}
}
