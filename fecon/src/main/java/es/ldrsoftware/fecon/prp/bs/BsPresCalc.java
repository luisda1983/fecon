package es.ldrsoftware.fecon.prp.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.util.DateTimeUtil;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.prp.entity.Pres;
import es.ldrsoftware.fecon.prp.entity.PresDAO;

@Component
public class BsPresCalc extends BaseBS {
	
	@Autowired
	public PresDAO presDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsPresCalcArea area = (BsPresCalcArea)a;

		int anua = DateTimeUtil.getYear(area.IN.fech);
		int mesp = DateTimeUtil.getMonth(area.IN.fech);
				
		Pres pres;
		//En primer lugar buscamos partida mensual por categoria y concepto
		pres = presDao.getByAnuaMespCateConc(SESSION.get().inst, anua, mesp, area.IN.cate, area.IN.conc);
		
		if (pres == null) {
			//Buscamos partida mensual por categoria
			pres = presDao.getByAnuaMespCateConc(SESSION.get().inst, anua, mesp, area.IN.cate, 0); 
		}
		
		if (pres == null) {
			//Buscamos partida anual por categoria y concepto
			pres = presDao.getByAnuaMespCateConc(SESSION.get().inst, anua, 0, area.IN.cate, area.IN.conc);
		}
		
		if (pres == null) {
			//Buscamos partida anual por categoria
			pres = presDao.getByAnuaMespCateConc(SESSION.get().inst, anua, 0, area.IN.cate, 0);
		}
				
		if (pres == null) {
			//Si no lo hemos encontrado, creamos registro no presupuestado
			pres = new Pres();
			pres.setInst(SESSION.get().inst);
			pres.setAnua(anua);
			pres.setMesp(mesp);
			pres.setCate(area.IN.cate);
			pres.setConc(area.IN.conc);
			pres.setImpo(0);
			pres.setImpr(0);
			pres.setImnp(0);
			pres.setImto(0);
			pres.setDesv(0);
			pres.setEsta("N"); //Devolvemos el objeto con estado N-Cómo marca de que no existe
			pres.setObse("");
		}
		
		area.OUT.pres = pres;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsPresCalcArea area = (BsPresCalcArea)a;
		
		validateIntRequired(area.IN.fech, AppNotify.PRES_CALC_FECH_RQRD);
		validateIntRequired(area.IN.cate, AppNotify.PRES_CALC_CATE_RQRD);
		validateIntRequired(area.IN.conc, AppNotify.PRES_CALC_CONC_RQRD);
	}
}