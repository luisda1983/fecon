package ldrsoftware.app.dao;

import java.util.List;

import ldrsoftware.app.domain.HPrep;

/**
 * Interfaz de operaciones disponibles sobre la tabla THPREP.
 * @author Luis David
 *
 */
public interface IHPrepDAO {
	HPrep getByIden(long inst, int iden);
	List<HPrep> getListByAnua(long inst, int anua);
	List<HPrep> getListByAnuaCate(long inst, int anua, int cate, boolean conc);
	void save(HPrep prep);
	void delete(HPrep prep);
}
