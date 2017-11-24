package es.ldrsoftware.core.sts.entity;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.BaseDAO;

/**
 * Operaciones sobre entidad STME - Estadísticas mensuales de ejecución
 * @author Luis David
 *
 */
@Repository(value = "stmeDao")
public class StmeDao extends BaseDAO {
	
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public void save(Stme stme) {
		getEntityManager().merge(stme);
	}
}
