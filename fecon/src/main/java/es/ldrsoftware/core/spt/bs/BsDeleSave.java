package es.ldrsoftware.core.spt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.spt.entity.Dele;
import es.ldrsoftware.core.spt.entity.DeleDAO;

@Component
public class BsDeleSave extends BaseBS {
	
	@Autowired
	DeleDAO deleDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDeleSaveArea area = (BsDeleSaveArea)a;

		//Guardamos el elemento de dominio
		Dele dele = deleDao.save(area.IN.dele);
		
		area.OUT.dele = dele;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsDeleSaveArea area = (BsDeleSaveArea)a;
		
		Dele dele = area.IN.dele;

		validateDtoRequired(dele, CoreNotify.DELE_SAVE_DELE_RQRD);
		
		validateIntRequired(dele.getInst(), CoreNotify.DELE_SAVE_INST_RQRD);
		validateIntRange(dele.getInst(), 0, 999999999, CoreNotify.DELE_SAVE_INST_RNGE);
		
		validateStringRequired(dele.getDomi(), CoreNotify.DELE_SAVE_DOMI_RQRD);
		validateStringMaxLength(dele.getDomi(), 10, CoreNotify.DELE_SAVE_DOMI_MAXL);
		
		validateStringRequired(dele.getValo(), CoreNotify.DELE_SAVE_VALO_RQRD);
		validateStringMaxLength(dele.getValo(), 50, CoreNotify.DELE_SAVE_VALO_MAXL);
		
		validateIntRequired(dele.getFeal(), CoreNotify.DELE_SAVE_FEAL_RQRD);
		validateIntRange(dele.getFeal(), 19000101, 29991231, CoreNotify.DELE_SAVE_FEAL_RNGE);
		
		validateIntRequired(dele.getHoal(), CoreNotify.DELE_SAVE_HOAL_RQRD);
		validateIntRange(dele.getHoal(), 000000, 235959, CoreNotify.DELE_SAVE_HOAL_RNGE);
		
		validateStringRequired(dele.getUsal(), CoreNotify.DELE_SAVE_USAL_RQRD);
		validateStringMaxLength(dele.getUsal(), 30, CoreNotify.DELE_SAVE_USAL_MAXL);
		
		validateIntRequired(dele.getFemo(), CoreNotify.DELE_SAVE_FEMO_RQRD);
		validateIntRange(dele.getFemo(), 19000101, 29991231, CoreNotify.DELE_SAVE_FEMO_RNGE);
		
		validateIntRequired(dele.getHomo(), CoreNotify.DELE_SAVE_HOMO_RQRD);
		validateIntRange(dele.getHomo(), 000000, 235959, CoreNotify.DELE_SAVE_HOMO_RNGE);
		
		validateStringRequired(dele.getUsmo(), CoreNotify.DELE_SAVE_USMO_RQRD);
		validateStringMaxLength(dele.getUsmo(), 30, CoreNotify.DELE_SAVE_USMO_MAXL);
	}
}