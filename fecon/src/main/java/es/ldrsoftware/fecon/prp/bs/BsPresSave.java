package es.ldrsoftware.fecon.prp.bs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.util.DoubleUtil;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.data.LiteData;
import es.ldrsoftware.fecon.prp.entity.Pres;
import es.ldrsoftware.fecon.prp.entity.PresDAO;

@Component
public class BsPresSave extends BaseBS {
	
	@Autowired
	public PresDAO presDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsPresSaveArea area = (BsPresSaveArea)a;

		Pres pres = area.IN.pres;
		
		//Si el estado es 'N', quiere decir que aï¿½n no estï¿½ creado el registro, y debe pasar a 'A'-Abierto
		//Además, se debe validar que no tiene importe presupuestado
		if (LiteData.LT_EL_PRESESTA_NO_CREADA.equals(pres.getEsta())) {
			validateDecEmpty(pres.getImpo(), AppNotify.PRES_SAVE_NPER_IMPO);
			pres.setEsta(LiteData.LT_EL_PRESESTA_ABIERTA);
		}

		//Si cerramos la partida, validamos los caudres entre importes
		if (LiteData.LT_EL_PRESESTA_CERRADA.equals(pres.getEsta())) {

			if (pres.getImto() != DoubleUtil.round(pres.getImnp() + pres.getImpr(), 2)) {
				notify(AppNotify.PRES_SAVE_IMPO_DESC);
			}
			
			//La desviación sólo aplica a partidas presupuestadas
			if (pres.getImpo() != 0) {
				if (pres.getDesv() != DoubleUtil.round(pres.getImto() - pres.getImpo(), 2)) {
					notify(AppNotify.PRES_SAVE_DESV_DESC);
				}
			}
			
			if (pres.getBala() != DoubleUtil.round(pres.getImto() - pres.getImpo(), 2)) {
				notify(AppNotify.PRES_SAVE_BALA_DESC);
			}
		}
		
		pres = presDao.save(pres);
		
		area.OUT.pres = pres;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsPresSaveArea area = (BsPresSaveArea)a;
		
		Pres pres = area.IN.pres;
		
		validateDtoRequired(pres, AppNotify.PRES_SAVE_PRES_RQRD);
		
		validateIntRequired(pres.getInst(), AppNotify.PRES_SAVE_INST_RQRD);
		validateIntRange(pres.getInst(), 0, 999999999, AppNotify.PRES_SAVE_INST_RNGE);
		
		validateIntRequired(pres.getAnua(), AppNotify.PRES_SAVE_ANUA_RQRD);
		validateIntRange(pres.getAnua(), 1900, 2999, AppNotify.PRES_SAVE_ANUA_RNGE);
		
		validateIntRange(pres.getMesp(), 0, 12, AppNotify.PRES_SAVE_MESP_RNGE);
		
		validateIntRequired(pres.getCate(), AppNotify.PRES_SAVE_CATE_RQRD);
		validateIntRange(pres.getCate(), 0, 999999999, AppNotify.PRES_SAVE_CATE_RNGE);
		
		validateIntRange(pres.getConc(), 0, 999999999, AppNotify.PRES_SAVE_CONC_RNGE);
		
		validateDecRange(pres.getImpo(), -99999999999.99, 99999999999.99, AppNotify.PRES_SAVE_IMPO_RNGE);
		
		validateDecRange(pres.getImpr(), -99999999999.99, 99999999999.99, AppNotify.PRES_SAVE_IMPR_RNGE);
		
		validateDecRange(pres.getImnp(), -99999999999.99, 99999999999.99, AppNotify.PRES_SAVE_IMNP_RNGE);
		
		validateDecRange(pres.getImto(), -99999999999.99, 99999999999.99, AppNotify.PRES_SAVE_IMTO_RNGE);
		
		validateDecRange(pres.getDesv(), -99999999999.99, 99999999999.99, AppNotify.PRES_SAVE_DESV_RNGE);
		
		validateDecRange(pres.getBala(), -99999999999.99, 99999999999.99, AppNotify.PRES_SAVE_BALA_RNGE);
		
		validateStringRequired(pres.getEsta(), AppNotify.PRES_SAVE_ESTA_RQRD);
		validateStringDomain(AppNotify.PRES_SAVE_ESTA_ERRO, pres.getEsta(), LiteData.LT_ST_PRESESTA);
		
		validateStringNotNull(pres.getObse(), AppNotify.PRES_SAVE_OBSE_NOTN);
		validateStringMaxLength(pres.getObse(), 100, AppNotify.PRES_SAVE_OBSE_MAXL);
	}
}