package ldrsoftware.app.dao;

import java.util.List;

import ldrsoftware.app.dao.search.ConcSearch;
import ldrsoftware.app.domain.HConc;

/**
 * Interfaz de operaciones disponibles sobre la tabla THCONC.
 * @author Luis David
 *
 */
public interface IHConcDAO {

	List<HConc> getList(long inst, ConcSearch search);
	
	HConc getByIden    (long inst, int iden);
	HConc getByCateDesc(long inst, int cate, String desc);
	HConc getByCateOrde(long inst, int cate, int orde);
	
	void save(HConc conc);
	
	int getOrde(long inst, int cate);
}
