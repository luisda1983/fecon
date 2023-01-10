package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Ejec;
import es.ldrsoftware.core.btc.entity.EjecDAO;
import es.ldrsoftware.core.fwk.data.LiteData;

@Component
public class BsEjecSave extends BaseBS {

	@Autowired
	EjecDAO ejecDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsEjecSaveArea area = (BsEjecSaveArea)a;
		
		//Grabamos la ejecución
		Ejec ejec = ejecDao.save(area.IN.ejec);
		
		area.OUT.ejec = ejec;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsEjecSaveArea area = (BsEjecSaveArea)a;
		
		//Validamos que recibimos una ejecución
		Ejec ejec = (Ejec)validateDto(area.IN.ejec, LiteData.LT_EL_DTO_EJEC);
	}

}
