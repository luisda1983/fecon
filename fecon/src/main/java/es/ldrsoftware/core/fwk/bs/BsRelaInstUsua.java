package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.data.RDInstUsua;
import es.ldrsoftware.core.fwk.entity.Rela;

@Component
public class BsRelaInstUsua extends BaseBS {

	@Autowired
	public BsRelaSave bsRelaSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsRelaInstUsuaArea area = (BsRelaInstUsuaArea)a;
		
		Rela rela = new Rela();
		rela.setMae1(LiteData.LT_EL_RELAMAES_INSTALACION);
		rela.setCln1(area.IN.inst);
		rela.setClc1("");
		rela.setMae2(LiteData.LT_EL_RELAMAES_USUARIO);
		rela.setCln2(0);
		rela.setClc2(area.IN.usua);
		rela.setEsta(LiteData.LT_EL_RELAESTA_ACTIVA);
		rela.setFeal(SESSION.get().feop);
		
		RDInstUsua data = new RDInstUsua();
		data.perf = area.IN.perf;
		rela.setData(data.format());
		
		BsRelaSaveArea bsRelaSaveArea = new BsRelaSaveArea();
		bsRelaSaveArea.IN.rela = rela;
		bsRelaSave.executeBS(bsRelaSaveArea);
			
		area.OUT.rela = bsRelaSaveArea.OUT.rela;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsRelaInstUsuaArea area = (BsRelaInstUsuaArea)a;
		
		validateIntRequired(area.IN.inst, CoreNotify.RELA_INST_USUA_INST_RQRD);
		validateStringRequired(area.IN.usua, CoreNotify.RELA_INST_USUA_USUA_RQRD);
		validateStringRequired(area.IN.perf, CoreNotify.RELA_INST_USUA_PERF_RQRD);
	}
}