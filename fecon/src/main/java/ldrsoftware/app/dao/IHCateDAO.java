package ldrsoftware.app.dao;

import java.util.List;

import ldrsoftware.app.dao.search.CateSearch;
import ldrsoftware.app.domain.HCate;

/**
 * Interfaz de operaciones disponibles sobre la tabla THCATE.
 * @author Luis David
 *
 */
public interface IHCateDAO {
	
	List<HCate> getList(long inst, CateSearch cateSearch);
		
	HCate getByIden(long inst, int iden);
	HCate getByDesc(long inst, String desc);
	HCate getByOrde(long inst, int orde);
	
	void save(HCate cate);
	
	int getOrde(long inst);
}
