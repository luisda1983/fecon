package ldrsoftware.app.dao;

import java.util.List;

import ldrsoftware.app.domain.HParm;

/**
 * Interfaz de operaciones disponibles sobre la tabla THPARM.
 * @author Luis David
 *
 */
public interface IHParmDAO {
	HParm getByTblaClav(String tbla, String clav);
	List<HParm> getListByTbla(String tbla);
}
