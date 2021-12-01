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
			testData(pres.getImpo(), AppNotify.PRES_SAVE_NPER_IMPO);
			pres.setEsta(LiteData.LT_EL_PRESESTA_ABIERTA);
		}

		//Si cerramos la partida, validamos los caudres entre importes
		if (LiteData.LT_EL_PRESESTA_CERRADA.equals(pres.getEsta())) {

			if (pres.getImto() != DoubleUtil.round(pres.getImnp() + pres.getImpr(), 2)) {
				notify(AppNotify.PRES_SAVE_IMPO_DESC);
			}
			
			//La desviación sólo aplica a partidas presupuestadas
			if (pres.getImpo() != 0) {
				if (pres.getDesv() != DoubleUtil.round(pres.getImto() - pres.getImpo() - pres.getImnp(), 2)) {
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
		
		Pres pres = (Pres)validateDto(area.IN.pres, LiteData.LT_EL_DTO_PRES);
	}
}