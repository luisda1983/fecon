package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Inst;
import es.ldrsoftware.core.oui.entity.InstDAO;

@Component
public class BsInstSave extends BaseBS {
	
	@Autowired
	public InstDAO instDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInstSaveArea area = (BsInstSaveArea)a;
		
		Inst inst = instDao.save(area.IN.inst);
		
		area.OUT.inst = inst;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInstSaveArea area = (BsInstSaveArea)a;
		
		Inst inst = area.IN.inst;
		
		validateDto(inst, CoreNotify.INST_SAVE_INST_RQRD);
		
		validateStringRequired(inst.getDesc(), CoreNotify.INST_SAVE_DESC_RQRD);
		validateStringMaxLength(inst.getDesc(), 50, CoreNotify.INST_SAVE_DESC_MAXL);
		
		validateIntRequired(inst.getFeal(), CoreNotify.INST_SAVE_FEAL_RQRD);
		validateIntRange(inst.getFeal(), 19000101, 29991231, CoreNotify.INST_SAVE_FEAL_RNGE);
		
		validateStringRequired(inst.getEsta(), CoreNotify.INST_SAVE_ESTA_RQRD);
		validateStringDomain(CoreNotify.INST_SAVE_ESTA_ERRO, inst.getEsta(), LiteData.LT_ST_INSTESTA);
		
		validateIntRequired(inst.getFeul(), CoreNotify.INST_SAVE_FEUL_RQRD);
		validateIntRange(inst.getFeul(), 19000101, 29991231, CoreNotify.INST_SAVE_FEUL_RNGE);
		
		validateStringRequired(inst.getTipo(), CoreNotify.INST_SAVE_TIPO_RQRD);
		validateStringDomain(CoreNotify.INST_SAVE_TIPO_ERRO, inst.getTipo(), LiteData.LT_ST_INSTTIPO);
				
		if (LiteData.LT_EL_INSTTIPO_PREMIUM.equals(inst.getTipo())) {
			validateIntRequired(inst.getFeca(), CoreNotify.INST_SAVE_FECA_RQRD);
			validateIntRange(inst.getFeca(), SESSION.get().feop, 29991231, CoreNotify.INST_SAVE_FECA_RNGE);
		}
	}
}