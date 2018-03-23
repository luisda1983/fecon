package es.ldrsoftware.core.oui.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad INST - Informaci�n de instalaci�n
 * @author Luis David
 *
 */
@Repository(value = "instDao")
public class InstDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Inst> getList() {
		TypedQuery<Inst> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT I FROM Inst I"
						+ " ORDER BY I.iden"
				         , Inst.class);
		List<Inst> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Inst> getListByEsta(String esta) {
		TypedQuery<Inst> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT I FROM Inst I"
						+ " WHERE I.esta = '" + esta + "' "
						+ " ORDER BY I.iden"
				         , Inst.class);
		List<Inst> resultList = typedQuery.getResultList();
		return resultList;
	}

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

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Inst getByDesc(String desc) {
		TypedQuery<Inst> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT I FROM Inst I"
				        + " WHERE I.desc = '" + desc + "' "
				         , Inst.class);
		List<Inst> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Inst getByCodi(String codi) {
		TypedQuery<Inst> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT I FROM Inst I"
				        + " WHERE I.codi = '" + codi + "' "
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
