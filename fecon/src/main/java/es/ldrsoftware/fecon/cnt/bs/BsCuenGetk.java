package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.cnt.entity.CuenDAO;

@Component
public class BsCuenGetk extends BaseBS {
	
	@Autowired
	public CuenDAO cuenDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCuenGetkArea area = (BsCuenGetkArea)a;
		
		Cuen cuen = cuenDao.getByIden(SESSION.get().inst, area.IN.iden);
		
		area.OUT.cuen = cuen;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCuenGetkArea area = (BsCuenGetkArea)a;
		
		validateInputField(area.IN.iden, Cuen.IDEN);
	}
}