package es.ldrsoftware.fecon.cnt.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad HCON - Histórico Contable
 * @author Luis David
 *
 */
@Repository(value = "hconDao")
public class HconDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Hcon> getListByFeva(long inst, int fevaInic, int fevaFini) {
		TypedQuery<Hcon> typedQuery = 
				this.getEntityManager().createQuery(
						  "SELECT H FROM Hcon H"
						+ " WHERE H.inst = " + inst + " "
						+ "   AND H.feva BETWEEN " + fevaInic + " AND " + fevaFini + " "
						+ "   AND H.tipo = 'C'"
						+ " ORDER BY H.feva ASC, H.iden ASC"
						 , Hcon.class);
		List<Hcon> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Hcon> getListByFevaCate(long inst, int fevaInic, int fevaFini, long cate) {
		TypedQuery<Hcon> typedQuery = 
				this.getEntityManager().createQuery(
						  "SELECT H FROM Hcon H"
						+ " WHERE H.inst = " + inst + " "
						+ "   AND H.feva BETWEEN " + fevaInic + " AND " + fevaFini + " "
						+ "   AND H.cate = " + cate + " "
						+ "   AND H.tipo = 'C'"
						+ " ORDER BY H.feva ASC, H.iden ASC"
						 , Hcon.class);
		List<Hcon> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Hcon> getListByFevaCateConc(long inst, int fevaInic, int fevaFini, long cate, long conc) {
		TypedQuery<Hcon> typedQuery = 
				this.getEntityManager().createQuery(
						  "SELECT H FROM Hcon H"
						+ " WHERE H.inst = " + inst + " "
						+ "   AND H.feva BETWEEN " + fevaInic + " AND " + fevaFini + " "
						+ "   AND H.cate = " + cate + " "
						+ "   AND H.conc = " + conc + " "
						+ "   AND H.tipo = 'C'"
						+ " ORDER BY H.feva ASC, H.iden ASC"
						 , Hcon.class);
		List<Hcon> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Hcon getByIden(long inst, long iden) {
		TypedQuery<Hcon> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT H FROM Hcon H"
				        + " WHERE H.inst = " + inst + " "
				        + "   AND H.iden = " + iden + " "
				         , Hcon.class);
		List<Hcon> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Hcon save(Hcon hcon) {
		return getEntityManager().merge(hcon);
	}
}
