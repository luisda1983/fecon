package es.ldrsoftware.core.spt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Dele;
import es.ldrsoftware.core.spt.entity.DeleDAO;

@Component
public class BsDeleGetv extends BaseBS {
	
	@Autowired
	DeleDAO deleDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDeleGetvArea area = (BsDeleGetvArea)a;

		//Obtenemos el dominio
		Dele dele = deleDao.getByDomiValo(SESSION.get().inst, area.IN.domi, area.IN.valo);
		
		area.OUT.dele = dele;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsDeleGetvArea area = (BsDeleGetvArea)a;
		
		validateInputField(area.IN.domi, Dele.DOMI);
		validateInputField(area.IN.valo, Dele.VALO);
	}
}