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
@Repository(value = "cuenDao")
public class CuenDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Cuen getByIden(long inst, long iden) {
		TypedQuery<Cuen> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Cuen C"
				        + " WHERE C.inst = " + inst + " "
				        + "   AND C.iden = " + iden + " "
				         , Cuen.class);
		List<Cuen> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Cuen getByDesc(long inst, String desc) {
		TypedQuery<Cuen> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Cuen C"
				        + " WHERE C.inst = " + inst + " "
				        + "   AND C.desc = '" + desc + "' "
				         , Cuen.class);
		List<Cuen> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Cuen> getList(long inst) {
		TypedQuery<Cuen> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT C FROM Cuen C"
				        + " WHERE C.inst = " + inst + " "
				        + " ORDER BY C.tipo ASC, C.iden ASC"
				         , Cuen.class);
		List<Cuen> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Cuen save(Cuen cuen) {
		return getEntityManager().merge(cuen);
	}
}
