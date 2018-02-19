//Normalizado
package es.ldrsoftware.core.fwk.bs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.entity.Rela;
import es.ldrsoftware.core.fwk.entity.RelaDAO;

@Component
public class BsRelaList extends BaseBS {
	
	public final static String RELA_LIST_INST_BY_USUA = "RL0001";
	
	@Autowired
	public RelaDAO relaDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsRelaListArea area = (BsRelaListArea)a;
		
		List<Rela> relaList;
		
		switch(area.IN.rela) {
		case RELA_LIST_INST_BY_USUA:
			relaList = relaDao.getListByMae1Mae1Clc2(LiteData.LT_EL_RELAMAES_INSTALACION, LiteData.LT_EL_RELAMAES_USUARIO, area.IN.clca);
			break;
		default:
			relaList = new ArrayList<Rela>();
			break;
		}
		
		area.OUT.relaList = relaList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsRelaListArea area = (BsRelaListArea)a;

		validateStringRequired(area.IN.rela, CoreNotify.RELA_LIST_RELA_RQRD);
		
		switch(area.IN.rela) {
			case RELA_LIST_INST_BY_USUA:
				validateStringRequired(area.IN.clca, CoreNotify.RELA_LIST_CLCA_RQRD);
				break;
			default:
				notify(CoreNotify.RELA_LIST_RELA_ERRO);
				break;
		}
	}
}