package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.btc.entity.Ejec;
import es.ldrsoftware.core.btc.entity.EjecDAO;

@Component
public class BsEjecSave extends BaseBS {

	@Autowired
	EjecDAO ejecDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsEjecSaveArea area = (BsEjecSaveArea)a;
		
		Ejec ejec = ejecDao.save(area.IN.ejec);
		
		area.OUT.ejec = ejec;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsEjecSaveArea area = (BsEjecSaveArea)a;
		
		if (area.IN.ejec == null) { notify(CoreNotify.EJEC_SAVE_RQRD); }
		
		Ejec ejec = area.IN.ejec;
		
		if (ejec.getBtch() == null || "".equals(ejec.getBtch())) { notify(CoreNotify.EJEC_SAVE_BTCH_RQRD); }
		if (ejec.getFech() == 0) { notify(CoreNotify.EJEC_SAVE_FECH_RQRD); }
		if (ejec.getOrde() == 0) { notify(CoreNotify.EJEC_SAVE_ORDE_RQRD); }
		
		if (!" ".equals(ejec.getEsta()) &&
			!"P".equals(ejec.getEsta()) &&
			!"V".equals(ejec.getEsta())) {
			notify(CoreNotify.EJEC_SAVE_ESTA_ERRO);
		}
		
		//Si la ejecución no está pendiente, deben estar informadas las estadisticas
		if (!"P".equals(ejec.getEsta())) {
			if (ejec.getFein() == 0) { notify(CoreNotify.EJEC_SAVE_NOTP_FEIN_RQRD); }
			if (ejec.getFefi() == 0) { notify(CoreNotify.EJEC_SAVE_NOTP_FEFI_RQRD); }
			if (ejec.getTiej() == 0) { notify(CoreNotify.EJEC_SAVE_NOTP_TIEJ_RQRD); }
			if (ejec.getHoin() == 0) { notify(CoreNotify.EJEC_SAVE_NOTP_HOIN_RQRD); }
			if (ejec.getHofi() == 0) { notify(CoreNotify.EJEC_SAVE_NOTP_HOFI_RQRD); }
		}
		
		//Si la ejecución ha finalizado con error, debe informarse el código de error
		if ("V".equals(ejec.getEsta())) {
			if (ejec.getNotf() == null || "".equals(ejec.getNotf())) { notify(CoreNotify.EJEC_SAVE_VOID_NOTF_RQRD); }
		}
	}

}
