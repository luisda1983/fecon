package es.ldrsoftware.core.oui.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad USUA - Informaci�n de usuario
 * @author Luis David
 *
 */
@Repository(value = "usuaDao")
public class UsuaDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Usua> getList() {
		TypedQuery<Usua> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT U FROM Usua U"
						+ " ORDER BY U.iden"
				         , Usua.class);
		List<Usua> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Usua> getListByInst(long inst) {
		TypedQuery<Usua> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT U FROM Usua U, Rela R"
						+ " WHERE R.mae1 = 'INST' "
				        + "   AND R.mae2 = 'USUA' "
						+ "   AND R.cln1 = " + inst + " "
						+ "   AND R.clc2 = U.iden "
						+ " ORDER BY U.iden"
				         , Usua.class);
		List<Usua> resultList = typedQuery.getResultList();
		return resultList;
	}

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
