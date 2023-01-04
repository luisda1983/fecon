package es.ldrsoftware.fecon.cnt.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad CUEN - Información de cuentas
 * @author Luis David
 *
 */
@Repository(value = "tradDao")
public class TradDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Trad getByIden(long inst, long iden) {
		TypedQuery<Trad> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT T FROM Trad T"
				        + " WHERE T.inst = " + inst + " "
				        + "   AND T.iden = " + iden + " "
				         , Trad.class);
		List<Trad> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Trad getByNomb(long inst, String nomb) {
		TypedQuery<Trad> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT T FROM Trad T"
				        + " WHERE T.inst = " + inst + " "
				        + "   AND T.nomb = '" + nomb + "' "
				         , Trad.class);
		List<Trad> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Trad> getList(long inst) {
		TypedQuery<Trad> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT T FROM Trad T"
				        + " WHERE T.inst = " + inst + " "
				        + " ORDER BY T.iden ASC"
				        , Trad.class);
		List<Trad> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Trad save(Trad trad) {
		return getEntityManager().merge(trad);
	}
}
