package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.btc.entity.Btch;
import es.ldrsoftware.core.btc.entity.BtchDAO;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.data.ParaData;

@Component
public class BsBtchSave extends BaseBS {

	@Autowired
	BtchDAO btchDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsBtchSaveArea area = (BsBtchSaveArea)a;
		
		//Grabamos el proceso Batch
		Btch btch = btchDao.save(area.IN.btch);
		
		area.OUT.btch = btch;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsBtchSaveArea area = (BsBtchSaveArea)a;
		
		//Validamos que está informado el proceso Batch
		validateDtoRequired(area.IN.btch, CoreNotify.BTCH_SAVE_BTCH_RQRD);
		
		Btch btch = area.IN.btch;
		
		//Validamos que el identificador esté informado y no sea demasiado largo
		validateStringRequired(btch.getIden(), CoreNotify.BTCH_SAVE_IDEN_RQRD);
		validateStringMaxLength(btch.getIden(), 30, CoreNotify.BTCH_SAVE_IDEN_MAXL);
		
		//Validamos que la descripción esté informada y no sea demasiado larga
		validateStringRequired(btch.getDesc(), CoreNotify.BTCH_SAVE_DESC_RQRD);
		validateStringMaxLength(btch.getDesc(), 200, CoreNotify.BTCH_SAVE_DESC_MAXL);
		
		//Validamos que la fecha de alta esté informada y dentro de rango
		validateIntRequired(btch.getFeal(), CoreNotify.BTCH_SAVE_FEAL_RQRD);
		validateIntRange(btch.getFeal(), 19000101, 29991231, CoreNotify.BTCH_SAVE_FEAL_RNGE);
		
		//Validamos que el estado esté informado y tenga un valor permitido
		validateStringRequired(btch.getEsta(), CoreNotify.BTCH_SAVE_ESTA_RQRD);
		validateStringDomain(CoreNotify.BTCH_SAVE_ESTA_ERRO, btch.getEsta(), LiteData.LT_ST_BTCHESTA);
		
		//Validamos que el indicador de proceso automático esté informado y tenga un valor permitido
		validateStringRequired(btch.getAuto(), CoreNotify.BTCH_SAVE_AUTO_RQRD);
		validateStringDomain(CoreNotify.BTCH_SAVE_AUTO_ERRO, btch.getAuto(), LiteData.LT_ST_BOOL);
		
		//Validamos que el indicador de dependencia de procesos anteriores esté informado y tenga un valor permitido
		validateStringRequired(btch.getDepe(), CoreNotify.BTCH_SAVE_DEPE_RQRD);
		validateStringDomain(CoreNotify.BTCH_SAVE_DEPE_ERRO, btch.getDepe(), LiteData.LT_ST_BOOL);
		
		//Si el proceso tiene dependencia, el proceso del que depende debe estar informado y dentro de la longitud permitida
		if (LiteData.LT_EL_BOOL_SI.equals(btch.getDepe())) {
			validateStringRequired(btch.getDbtc(), CoreNotify.BTCH_SAVE_DBTC_RQRD);
			validateStringMaxLength(btch.getDbtc(), 30, CoreNotify.BTCH_SAVE_DBTC_MAXL);
		} else {
			validateStringEmpty(btch.getDbtc(), CoreNotify.BTCH_SAVE_DBTC_NPER);
		}
		
		//Si el proceso es automático, validamos que el tipo de proceso esté informado y tenga un valor permitido
		if (LiteData.LT_EL_BOOL_SI.equals(btch.getAuto())) {
			validateStringRequired(btch.getTipo(), CoreNotify.BTCH_SAVE_TIPO_RQRD);
			validateStringDomain(CoreNotify.BTCH_SAVE_TIPO_ERRO, btch.getTipo(), ParaData.PM_ST_BTCHAVANCE);
		} else {
			validateStringEmpty(btch.getTipo(), CoreNotify.BTCH_SAVE_TIPO_NPER);
		}
		
		//Validamos que el orden esté informado y dentro de rango
		validateIntRequired(btch.getOrde(), CoreNotify.BTCH_SAVE_ORDE_RQRD);
		validateIntRange(btch.getOrde(), 1, 99999, CoreNotify.BTCH_SAVE_ORDE_RNGE);
		
	}

}
