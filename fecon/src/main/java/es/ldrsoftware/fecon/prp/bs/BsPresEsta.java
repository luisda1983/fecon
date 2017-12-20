package es.ldrsoftware.fecon.prp.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.data.LiteData;
import es.ldrsoftware.fecon.prp.entity.Pres;
import es.ldrsoftware.fecon.prp.entity.PresDAO;

@Component
public class BsPresEsta extends BaseBS {
	
	@Autowired
	public PresDAO presDao;

	@Autowired
	public BsPresSave bsPresSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsPresEstaArea area = (BsPresEstaArea)a;

				
		Pres pres;
		
		pres = presDao.getByAnuaMespCateConc(SESSION.get().inst, area.IN.anua, area.IN.mesp, area.IN.cate, area.IN.conc);
		
		if (pres == null) {
			notify(AppNotify.PRES_ESTA_PRES_NF); 
		}
		
		if (LiteData.LT_EL_PRESESTA_ABIERTA.equals(pres.getEsta())) {
			if (LiteData.LT_EL_PRESESTA_CERRADA.equals(area.IN.esta)) {
				pres.setEsta(area.IN.esta);
			} else {
				notify(AppNotify.PRES_ESTA_ESTA_ERRO);
			}
		} else if (LiteData.LT_EL_PRESESTA_CERRADA.equals(pres.getEsta())) {
			if (LiteData.LT_EL_PRESESTA_ABIERTA.equals(area.IN.esta)) {
				pres.setEsta(area.IN.esta);
			} else {
				notify(AppNotify.PRES_ESTA_ESTA_ERRO);
			}
		} 
		
		BsPresSaveArea bsPresSaveArea = new BsPresSaveArea();
		bsPresSaveArea.IN.pres = pres;
		bsPresSave.executeBS(bsPresSaveArea);
		
		area.OUT.pres = bsPresSaveArea.OUT.pres;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsPresEstaArea area = (BsPresEstaArea)a;
		
		validateIntRequired(area.IN.anua, AppNotify.PRES_ESTA_ANUA_RQRD);
		validateIntRequired(area.IN.cate, AppNotify.PRES_ESTA_CATE_RQRD);
		validateStringRequired(area.IN.esta, AppNotify.PRES_ESTA_ESTA_RQRD);
	}
}