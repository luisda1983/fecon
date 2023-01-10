package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.entity.Sesi;
import es.ldrsoftware.core.fwk.entity.SesiDAO;

@Component
public class BsSesiSave extends BaseBS {

	@Autowired
	private SesiDAO sesiDao;

	protected void execute(BaseBSArea a) throws Exception {
		BsSesiSaveArea area = (BsSesiSaveArea)a;
		
		Sesi sesi = area.IN.sesi;
		
		sesi = sesiDao.save(sesi);
		
		area.OUT.sesi = sesi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsSesiSaveArea area = (BsSesiSaveArea)a;

		//Validamos que la sesión esté informada
		Sesi sesi = (Sesi)validateDto(area.IN.sesi, LiteData.LT_EL_DTO_SESI); 
		
	}

}
