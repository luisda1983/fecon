package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.btc.entity.Ejec;
import es.ldrsoftware.core.btc.entity.EjecDAO;
import es.ldrsoftware.core.fwk.data.LiteData;

@Component
public class BsEjecSave extends BaseBS {

	@Autowired
	EjecDAO ejecDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsEjecSaveArea area = (BsEjecSaveArea)a;
		
		//Grabamos la ejecución
		Ejec ejec = ejecDao.save(area.IN.ejec);
		
		area.OUT.ejec = ejec;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsEjecSaveArea area = (BsEjecSaveArea)a;
		
		//Validamos que recibimos una ejecución
		validateDtoRequired(area.IN.ejec, CoreNotify.EJEC_SAVE_EJEC_RQRD);
		
		Ejec ejec = area.IN.ejec;
		
		//Validamos que la fecha de ejecución está informada y dentro de rango
		validateIntRequired(ejec.getFech(), CoreNotify.EJEC_SAVE_FECH_RQRD);
		validateIntRange(ejec.getFech(), 19000101, 29991231, CoreNotify.EJEC_SAVE_FECH_RNGE);
		
		//Validamos que la hora de ejecución esté dentro de rango
		validateIntRange(ejec.getHora(), 0000, 2359, CoreNotify.EJEC_SAVE_HORA_RNGE);
		
		//Validamos que el Batch de la ejecución esté informado y no exceda la longitud máxima
		validateStringRequired(ejec.getBtch(), CoreNotify.EJEC_SAVE_BTCH_RQRD);
		validateStringMaxLength(ejec.getBtch(), 30, CoreNotify.EJEC_SAVE_BTCH_MAXL);
		
		//Validamos que la Secuencia está informada y dentro de rango
		validateIntRequired(ejec.getSecu(), CoreNotify.EJEC_SAVE_SECU_RQRD);
		validateIntRange(ejec.getSecu(), 00, 99, CoreNotify.EJEC_SAVE_SECU_RNGE);
		
		//Validamos que el orden esté informado y dentro de rango
		validateIntRequired(ejec.getOrde(), CoreNotify.EJEC_SAVE_ORDE_RQRD);
		validateIntRange(ejec.getOrde(), 00000, 99999, CoreNotify.EJEC_SAVE_ORDE_RNGE);
		
		//Validamos que el estado de ejecución esté informado y tenga un valor correcto
		validateStringRequired(ejec.getEsta(), CoreNotify.EJEC_SAVE_ESTA_RQRD);
		validateStringDomain(CoreNotify.EJEC_SAVE_ESTA_ERRO, ejec.getEsta(), LiteData.LT_ST_EJECESTA);
		
		//Si la ejecución está pendiente, no se permtien los datos de auditoría
		if (LiteData.LT_EL_EJECESTA_PENDIENTE.equals(ejec.getEsta())) {
			validateIntEmpty(ejec.getFein(), CoreNotify.EJEC_SAVE_FEIN_NPER);
			validateIntEmpty(ejec.getHoin(), CoreNotify.EJEC_SAVE_HOIN_NPER);
			validateIntEmpty(ejec.getFefi(), CoreNotify.EJEC_SAVE_FEFI_NPER);
			validateIntEmpty(ejec.getHofi(), CoreNotify.EJEC_SAVE_HOFI_NPER);
			validateIntEmpty(ejec.getTiej(), CoreNotify.EJEC_SAVE_TIEJ_NPER);
			validateIntEmpty(ejec.getFepl(), CoreNotify.EJEC_SAVE_FEPL_NPER);
			validateIntEmpty(ejec.getHopl(), CoreNotify.EJEC_SAVE_HOPL_NPER);
		} else {
		//Si la ejecución no está pendiente, los datos de auditoría son obligatorios.
			validateIntRequired(ejec.getFein(), CoreNotify.EJEC_SAVE_FEIN_RQRD);
			validateIntRange(ejec.getFein(), 19000101, 29991231, CoreNotify.EJEC_SAVE_FEIN_RNGE);
			
			validateIntRange(ejec.getHoin(), 000000, 235959, CoreNotify.EJEC_SAVE_HOIN_RNGE);
			
			validateIntRequired(ejec.getFefi(), CoreNotify.EJEC_SAVE_FEFI_RQRD);
			validateIntRange(ejec.getFefi(), 19000101, 29991231, CoreNotify.EJEC_SAVE_FEFI_RNGE);
			
			validateIntRange(ejec.getHofi(), 000000, 235959, CoreNotify.EJEC_SAVE_HOFI_RNGE);
			
			validateIntRequired(ejec.getTiej(), CoreNotify.EJEC_SAVE_TIEJ_RQRD);
			validateIntRange(ejec.getTiej(), 0, 99999999, CoreNotify.EJEC_SAVE_TIEJ_RNGE);

			//La auditoria de planificación sólo es obligatoria en primeras ejecuciones de procesos
			if (ejec.getSecu() == 1) {
				validateIntRequired(ejec.getFepl(), CoreNotify.EJEC_SAVE_FEPL_RQRD);
				validateIntRange(ejec.getFepl(), 19000101, 29991231, CoreNotify.EJEC_SAVE_FEPL_RNGE);
			
				validateIntRange(ejec.getHopl(), 000000, 235959, CoreNotify.EJEC_SAVE_HOPL_RNGE);
			}
		}
		
		//Si la ejecución ha finalizado con error, debe informarse el código de notificación
		if (LiteData.LT_EL_EJECESTA_VOID.equals(ejec.getEsta())) {
			validateStringRequired(ejec.getNotf(), CoreNotify.EJEC_SAVE_NOTF_RQRD);
			validateStringMaxLength(ejec.getNotf(), 10, CoreNotify.EJEC_SAVE_NOTF_MAXL);
		} else {
		//En caso contrario, no se permite
			validateStringEmpty(ejec.getNotf(), CoreNotify.EJEC_SAVE_NOTF_NPER);
		}
		
		//Si la ejecución no está pendiente, debe informarse la fecha batch
		if (!LiteData.LT_EL_EJECESTA_PENDIENTE.equals(ejec.getEsta())) {
			validateIntRequired(ejec.getFbtc(), CoreNotify.EJEC_SAVE_FBTC_RQRD);
			validateIntRange(ejec.getFbtc(), 19000101, 29991231, CoreNotify.EJEC_SAVE_FBTC_RNGE);
		} else {
			validateIntEmpty(ejec.getFbtc(), CoreNotify.EJEC_SAVE_FBTC_NPER);
		}
	}

}
