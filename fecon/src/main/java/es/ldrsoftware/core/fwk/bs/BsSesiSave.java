package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.entity.Sesi;
import es.ldrsoftware.core.fwk.entity.SesiDAO;

@Component
public class BsSesiSave extends BaseBS {

	@Autowired
	private SesiDAO sesiDao;

	protected void execute(BaseBSArea a) throws Exception {
		BsSesiSaveArea area = (BsSesiSaveArea)a;
		
		Sesi sesi = area.IN.sesi;
		
		sesi = sesiDao.save(sesi);
		
		area.OUT.sesi = sesi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsSesiSaveArea area = (BsSesiSaveArea)a;

		Sesi sesi = area.IN.sesi;
		
		//Validamos que la sesión esté informada
		validateDtoRequired(sesi, CoreNotify.SESI_SAVE_SESI_RQRD);
		
		//Validamos que el identificador de usuario esté informado y no exceda la longitud
		validateStringRequired(sesi.getUsua(), CoreNotify.SESI_SAVE_USUA_RQRD);
		validateStringMaxLength(sesi.getUsua(), 30, CoreNotify.SESI_SAVE_USUA_MAXL);
		
		//TODO: clave externa de sesión
		
		//Validamos que la dirección IP esté informada y no exceda la longitud
		validateStringRequired(sesi.getDiip(), CoreNotify.SESI_SAVE_DIIP_RQRD);
		validateStringMaxLength(sesi.getDiip(), 20, CoreNotify.SESI_SAVE_DIIP_MAXL);
		
		//Validamos que el perfil esté informado y tengo un valor dentro del dominio permitido
		validateStringRequired(sesi.getPerf(), CoreNotify.SESI_SAVE_PERF_RQRD);
		validateStringDomain(CoreNotify.SESI_SAVE_PERF_ERRO, sesi.getPerf(), LiteData.LT_ST_USUAPERF);

		//Validamos que el dispotivio esté informado y tenga un valor dentro del dominio permitido
		validateStringRequired(sesi.getDvce(), CoreNotify.SESI_SAVE_DVCE_RQRD);
		validateStringDomain(CoreNotify.SESI_SAVE_DVCE_ERRO, sesi.getDvce(), LiteData.LT_ST_SESIDVCE);
		
		//Validamos que la instalación esté informada y dentro de rango, salvo para el perfil APM
		if (!LiteData.LT_EL_USUAPERF_APM.equals(sesi.getPerf())) {
			validateIntRequired(sesi.getInst(), CoreNotify.SESI_SAVE_INST_RQRD);
			validateIntRange(sesi.getInst(), 1, 999999999, CoreNotify.SESI_SAVE_INST_RNGE);
		}
		
		//Validamos que la fecha de apertura esté informada y dentro de rango
		validateIntRequired(sesi.getFeap(), CoreNotify.SESI_SAVE_FEAP_RQRD);
		validateIntRange(sesi.getFeap(), 19000101, 29991231, CoreNotify.SESI_SAVE_FEAP_RNGE);
		
		//Validamos que la hora de apertura esté dentro de rango
		validateIntRange(sesi.getHoap(), 000000, 235959, CoreNotify.SESI_SAVE_HOAP_RNGE);
		
		//Validamos que el estado esté informado y dentro del dominio permitido
		validateStringRequired(sesi.getEsta(), CoreNotify.SESI_SAVE_ESTA_RQRD);
		validateStringDomain(CoreNotify.SESI_SAVE_ESTA_ERRO, sesi.getEsta(), LiteData.LT_ST_SESIESTA);
		
		//Validamos que la fecha de última actividad esté informada y dentro de rango
		validateIntRequired(sesi.getFeul(), CoreNotify.SESI_SAVE_FEUL_RQRD);
		validateIntRange(sesi.getFeul(), 19000101, 29991231, CoreNotify.SESI_SAVE_FEUL_RNGE);
		
		//Validamos que la hora de última actividad esté dentro de rango
		validateIntRange(sesi.getHoul(), 000000, 235959, CoreNotify.SESI_SAVE_HOUL_RNGE);
		
		//TODO: fecha/hora de renovación
		
		//Validamos que la fecha y hora de caducidad estén informada y dentro de rango, si la sesión está abierta
		if (LiteData.LT_EL_SESIESTA_ABIERTA.equals(sesi.getEsta())) {
			validateIntRequired(sesi.getFeca(), CoreNotify.SESI_SAVE_FECA_RQRD);
			validateIntRange(sesi.getFeca(), 19000101, 29991231, CoreNotify.SESI_SAVE_FECA_RNGE);
			
			validateIntRange(sesi.getHoca(), 000000, 235959, CoreNotify.SESI_SAVE_HOCA_RNGE);
		}
	}

}
