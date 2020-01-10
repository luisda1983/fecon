package es.ldrsoftware.core.spt.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Dele;
import es.ldrsoftware.core.spt.entity.DeleDAO;

@Component
public class BsDeleList extends BaseBS {
	
	@Autowired
	DeleDAO deleDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDeleListArea area = (BsDeleListArea)a;

		//Obtenemos la lista de elementos de dominio
		List<Dele> deleList = deleDao.getListByDomi(SESSION.get().inst, area.IN.domi);
		
		area.OUT.deleList = deleList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsDeleListArea area = (BsDeleListArea)a;
		
		validateInputField(area.IN.domi, Dele.DOMI);
	}
}