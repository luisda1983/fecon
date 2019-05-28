package es.ldrsoftware.core.spt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.spt.entity.Domi;
import es.ldrsoftware.core.spt.entity.DomiDAO;

@Component
public class BsDomiSave extends BaseBS {
	
	@Autowired
	DomiDAO domiDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDomiSaveArea area = (BsDomiSaveArea)a;

		//Guardamos el dominio
		Domi domi = domiDao.save(area.IN.domi);
		
		area.OUT.domi = domi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsDomiSaveArea area = (BsDomiSaveArea)a;
		
		Domi domi = area.IN.domi;

		validateDtoRequired(domi, CoreNotify.DOMI_SAVE_DOMI_RQRD);
		
		validateIntRequired(domi.getInst(), CoreNotify.DOMI_SAVE_INST_RQRD);
		validateIntRange(domi.getInst(), 0, 999999999, CoreNotify.DOMI_SAVE_INST_RNGE);
		
		validateStringRequired(domi.getNomb(), CoreNotify.DOMI_SAVE_NOMB_RQRD);
		validateStringMaxLength(domi.getNomb(), 50, CoreNotify.DOMI_SAVE_NOMB_MAXL);
		
		validateStringRequired(domi.getDesc(), CoreNotify.DOMI_SAVE_DESC_RQRD);
		validateStringMaxLength(domi.getDesc(), 50, CoreNotify.DOMI_SAVE_DESC_MAXL);
		
		validateIntRequired(domi.getFeal(), CoreNotify.DOMI_SAVE_FEAL_RQRD);
		validateIntRange(domi.getFeal(), 19000101, 29991231, CoreNotify.DOMI_SAVE_FEAL_RNGE);
		
		validateIntRequired(domi.getHoal(), CoreNotify.DOMI_SAVE_HOAL_RQRD);
		validateIntRange(domi.getHoal(), 000000, 235959, CoreNotify.DOMI_SAVE_HOAL_RNGE);
		
		validateStringRequired(domi.getUsal(), CoreNotify.DOMI_SAVE_USAL_RQRD);
		validateStringMaxLength(domi.getUsal(), 30, CoreNotify.DOMI_SAVE_USAL_MAXL);
		
		validateIntRequired(domi.getFemo(), CoreNotify.DOMI_SAVE_FEMO_RQRD);
		validateIntRange(domi.getFemo(), 19000101, 29991231, CoreNotify.DOMI_SAVE_FEMO_RNGE);
		
		validateIntRequired(domi.getHomo(), CoreNotify.DOMI_SAVE_HOMO_RQRD);
		validateIntRange(domi.getHomo(), 000000, 235959, CoreNotify.DOMI_SAVE_HOMO_RNGE);
		
		validateStringRequired(domi.getUsmo(), CoreNotify.DOMI_SAVE_USMO_RQRD);
		validateStringMaxLength(domi.getUsmo(), 30, CoreNotify.DOMI_SAVE_USMO_MAXL);
	}
}