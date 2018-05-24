package es.ldrsoftware.core.btc.entity;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad LOGP - Log de procesos automáticos
 * @author Luis David
 *
 */
@Repository(value = "logpDao")
public class LogpDAO extends BaseDAO {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Logp> getListByTipoIdenFechHora(String tipo, String iden, int fech, int hora) {
		TypedQuery<Logp> typedQuery = 
				this.getEntityManager().createQuery(
				          "SELECT L FROM Logp L"
						+ " WHERE L.tipo = '" + tipo + "' "
						+ "   AND L.iden = '" + iden + "' "
						+ "   AND L.fech = " + fech + " "
						+ "   AND L.hora = " + hora + " "
				        + " ORDER BY L.secu"
				         , Logp.class);
		List<Logp> resultList = typedQuery.getResultList();
		return resultList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public Logp save(Logp logp) {
		return getEntityManager().merge(logp);
	}
}
