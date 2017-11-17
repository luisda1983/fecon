package ldrsoftware.app.dao;

import java.util.List;

import ldrsoftware.app.domain.HHcon;

/**
 * Interfaz de operaciones disponibles sobre la tabla THHCON.
 * @author Luis David
 *
 */
public interface IHHconDAO {
	void save(HHcon hcon);
	List<HHcon> getList(int fdes, int fhas, int cate, int conc);
	HHcon getByIden(int iden);
}
