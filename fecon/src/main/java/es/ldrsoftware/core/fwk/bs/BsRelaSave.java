package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.RDInstUsua;
import es.ldrsoftware.core.fwk.entity.Rela;
import es.ldrsoftware.core.fwk.entity.RelaDAO;

@Component
public class BsRelaSave extends BaseBS {
	
	public final static String RELA_REGI_INST_USUA = "RL0002";
	
	private final static String RELA_MAES_INST = "INST";
	private final static String RELA_MAES_USUA = "USUA";
	
	@Autowired
	public RelaDAO relaDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsRelaSaveArea area = (BsRelaSaveArea)a;
		
		Rela rela = null;
		
		switch(area.IN.rela) {
		case RELA_REGI_INST_USUA:
			rela = new Rela();
			rela.setMae1(RELA_MAES_INST);
			rela.setCln1(area.IN.cln1);
			rela.setClc1("");
			rela.setMae2(RELA_MAES_USUA);
			rela.setClc2(area.IN.clc2);
			rela.setCln2(0);
			rela.setEsta("A");
			rela.setFeal(SESSION.get().feop);
			RDInstUsua data = new RDInstUsua();
			data.perf = area.IN.perf;
			rela.setData(data.format());
			relaDao.save(rela);
			break;
		default:
			break;
		}
		
		area.OUT.rela = rela;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsRelaSaveArea area = (BsRelaSaveArea)a;
		
		if (area.IN.rela == null || "".equals(area.IN.rela)) { notify(CoreNotify.RELA_SAVE_RELA_RQRD); }
		
		switch(area.IN.rela) {
			case RELA_REGI_INST_USUA:
				validateIntRequired(area.IN.cln1, CoreNotify.RELA_SAVE_CLN1_RQRD);
				validateStringRequired(area.IN.clc2, CoreNotify.RELA_SAVE_CLC2_RQRD);
				validateStringRequired(area.IN.perf, CoreNotify.RELA_SAVE_PERF_RQRD);
				break;
			default:
				notify(CoreNotify.RELA_SAVE_RELA_ERRO);
		}
	}
}