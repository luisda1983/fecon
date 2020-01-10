package es.ldrsoftware.core.mnu.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.mnu.entity.Dtmn;
import es.ldrsoftware.core.mnu.entity.DtmnDAO;

@Component
public class BsDtmnSave extends BaseBS {

	@Autowired
	DtmnDAO dtmnDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDtmnSaveArea area = (BsDtmnSaveArea)a;

		//Guardamos el detalle de menú
		Dtmn dtmn = dtmnDao.save(area.IN.dtmn);
		
		area.OUT.dtmn = dtmn;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsDtmnSaveArea area = (BsDtmnSaveArea)a;

		//Validamos que el detalle de menú esté informado
		Dtmn dtmn = (Dtmn)validateDto(area.IN.dtmn, LiteData.LT_EL_DTO_DTMN); 
		dtmn.validate();
		
	}

}
