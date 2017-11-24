package es.ldrsoftware.core.oui.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad INVI - Invitaciones de registro
 * @author Luis David
 *
 */
@Repository(value = "inviDao")
public class InviDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Invi getByIden(String iden) {
		TypedQuery<Invi> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT I FROM Invi I"
				        + " WHERE I.iden = '" + iden + "' "
				         , Invi.class);
		List<Invi> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Invi> getListByTipo(String tipo) {
		TypedQuery<Invi> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT I FROM Invi I"
				        + " WHERE I.tipo = '" + tipo + "' "
				        + " ORDER BY I.feal DESC, I.hoal DESC "
				         , Invi.class);
		List<Invi> resultList = typedQuery.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Invi> getListByInstTipo(long inst, String tipo) {
		TypedQuery<Invi> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT I FROM Invi I"
				        + " WHERE I.inst = " + inst + " " 
				        + "   AND I.tipo = '" + tipo + "' "
				        + " ORDER BY I.feal DESC, I.hoal DESC "
				         , Invi.class);
		List<Invi> resultList = typedQuery.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Invi> getListByInstTipoEsta(long inst, String tipo, String esta) {
		TypedQuery<Invi> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT I FROM Invi I"
				        + " WHERE I.inst = " + inst + " " 
				        + "   AND I.tipo = '" + tipo + "' "
				        + "   AND I.esta = '" + esta + "' "
				        + " ORDER BY I.feal DESC, I.hoal DESC "
				         , Invi.class);
		List<Invi> resultList = typedQuery.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Invi> getListByTipoEsta(String tipo, String esta) {
		TypedQuery<Invi> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT I FROM Invi I"
				        + " WHERE I.tipo = '" + tipo + "' "
				        + "   AND I.esta = '" + esta + "' "
				        + " ORDER BY I.feal DESC, I.hoal DESC"
				         , Invi.class);
		List<Invi> resultList = typedQuery.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Invi> getListByTipoEstaMail(String tipo, String esta, String mail) {
		TypedQuery<Invi> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT I FROM Invi I"
				        + " WHERE I.tipo = '" + tipo + "' "
				        + "   AND I.esta = '" + esta + "' "
				        + "   AND I.mail = '" + mail + "' "
				        + " ORDER BY I.feal DESC, I.hoal DESC"
				         , Invi.class);
		List<Invi> resultList = typedQuery.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Invi save(Invi invi) {
		return getEntityManager().merge(invi);
	}
}
