package es.ldrsoftware.core.oui.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad USUA - Información de usuario
 * @author Luis David
 *
 */
@Repository(value = "usuaDao")
public class UsuaDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Usua getByIden(String iden) {
		TypedQuery<Usua> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT U FROM Usua U"
				        + " WHERE U.iden = '" + iden + "' "
				         , Usua.class);
		List<Usua> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Usua getByMail(String mail) {
		TypedQuery<Usua> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT U FROM Usua U"
				        + " WHERE U.mail = '" + mail + "' "
				         , Usua.class);
		List<Usua> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Usua save(Usua usua) {
		return getEntityManager().merge(usua);
	}
}
