package ldrsoftware.app.dao;

import ldrsoftware.app.domain.HMens;

/**
 * Interfaz de operaciones disponibles sobre la tabla THMENS.
 * @author Luis David
 *
 */
public interface IHMensDAO {
	HMens getByIden(String iden);
}
