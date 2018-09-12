package es.ldrsoftware.core.fwk.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad RELA - Informaci�n de relaciones
 * @author Luis David
 *
 */
@Repository(value = "relaDao")
public class RelaDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Rela> getListByMae1Mae2Clc2(String mae1, String mae2, String clc2) {
		TypedQuery<Rela> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT R FROM Rela R"
				        + " WHERE R.mae1 = '" + mae1 + "' "
				        + "   AND R.mae2 = '" + mae2 + "' "
				        + "   AND R.clc2 = '" + clc2 + "' "
				        + "   AND R.esta = 'A' "
				        + " ORDER BY R.cln1 ASC, R.clc1 ASC"
				         , Rela.class);
		List<Rela> resultList = typedQuery.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Rela save(Rela rela) {
		return getEntityManager().merge(rela);
	}
}
