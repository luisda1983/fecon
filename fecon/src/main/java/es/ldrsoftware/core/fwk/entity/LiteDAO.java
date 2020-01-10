package es.ldrsoftware.core.fwk.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.SimpleBaseDAO;

/**
 * Operaciones sobre entidad LITE - Literales
 * @author Luis David
 *
 */
@Repository(value = "liteDao")
public class LiteDAO extends SimpleBaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Lite getByTblaClav(String tbla, String clav) {
		TypedQuery<Lite> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT L FROM Lite L"
				        + " WHERE L.tbla = '" + tbla + "' "
				        + "   AND L.clav = '" + clav + "' "
				         , Lite.class);
		List<Lite> resultList = typedQuery.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Lite> getListByTbla(String tbla) {
		TypedQuery<Lite> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT L FROM Lite L"
				        + " WHERE L.tbla = '" + tbla + "' "
				        + " ORDER BY L.orde ASC "
				         , Lite.class);
		List<Lite> resultList = typedQuery.getResultList();
		return resultList;
	}
}
