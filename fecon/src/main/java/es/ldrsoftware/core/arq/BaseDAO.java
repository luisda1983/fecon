package es.ldrsoftware.core.arq;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import es.ldrsoftware.core.fwk.data.LiteData;

/**
 * Clase que incluye al atributo y setter EntityManager,
 * requerido por los objetos de acceso a BBDD.
 * 
 * @author Luis David
 *
 */
@Component
public class BaseDAO extends BaseNotifyManager {

	@PersistenceContext
	EntityManager em;
	
	/**
	 * Getter.
	 * @return EntityManager
	 */
	public EntityManager getEntityManager() {
		return this.em;
	}
	
	/**
	 * Setter.
	 * @param em EntityManager.
	 */
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	public void daoSetCont(String dao, TypedQuery<?> query) {
		if (SESSION.get().AREA_CONT.CONT_DAO.equals(dao)) {
			if (SESSION.get().AREA_CONT.MAXM_REGS > 0) {
				query.setMaxResults(SESSION.get().AREA_CONT.MAXM_REGS + 1);
			}
			if (SESSION.get().AREA_CONT.CONT_NUMB > 0) {
				query.setFirstResult(SESSION.get().AREA_CONT.CONT_NUMB * SESSION.get().AREA_CONT.MAXM_REGS + 1);
			}
		}
	}
	
	public int daoListLength(String dao, List<?> result) {
		//Si es el Dao configurado para la continuación
		if (SESSION.get().AREA_CONT.CONT_DAO.equals(dao)) {
			//Si el controlador admite continuación
			//if (LiteData.LT_EL_BOOL_SI.equals(SESSION.get().cont)) {
			if (LiteData.LT_EL_BOOL_SI.equals(SESSION.get().AREA_CONT.CONT_GRTN)) {
				//Si tenemos exceso de registros
				if (result.size() > SESSION.get().AREA_CONT.MAXM_REGS) {
					//Indicador de activación de continuación
					SESSION.get().AREA_CONT.MORE_DATA = LiteData.LT_EL_BOOL_SI;
					//Incrementamos el número de continuación
					SESSION.get().AREA_CONT.CONT_NUMB++;
					return result.size() - 2;
				}
			}
		}
		return result.size();
	}
}
