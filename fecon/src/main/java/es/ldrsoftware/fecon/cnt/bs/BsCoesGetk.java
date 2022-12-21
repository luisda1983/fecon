package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Coes;
import es.ldrsoftware.fecon.cnt.entity.CoesDAO;

@Component
public class BsCoesGetk extends BaseBS {
	
	@Autowired
	public CoesDAO coesDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCoesGetkArea area = (BsCoesGetkArea)a;
		
		Coes coes = coesDao.getByIden(SESSION.get().inst, area.IN.iden);
		
		area.OUT.coes = coes;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCoesGetkArea area = (BsCoesGetkArea)a;
		
		validateInputField(area.IN.iden, Coes.IDEN);
	}
}