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
@Repository(value = "coesDao")
public class CoesDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Coes getByIden(long inst, long iden) {
		TypedQuery<Coes> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Coes C"
				        + " WHERE C.inst = " + inst + " "
				        + "   AND C.iden = " + iden + " "
				         , Coes.class);
		List<Coes> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Coes getByDesc(long inst, String desc) {
		TypedQuery<Coes> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Coes C"
				        + " WHERE C.inst = " + inst + " "
				        + "   AND C.desc = '" + desc + "' "
				         , Coes.class);
		List<Coes> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Coes> getList(long inst) {
		TypedQuery<Coes> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Coes C"
				        + " WHERE C.inst = " + inst + " "
				        + " ORDER BY C.favo ASC, C.usad ASC, C.iden ASC"
				         , Coes.class);
		List<Coes> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Coes save(Coes coes) {
		return getEntityManager().merge(coes);
	}
}
