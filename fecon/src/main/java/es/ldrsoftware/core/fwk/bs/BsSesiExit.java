package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.entity.Sesi;
import es.ldrsoftware.core.fwk.entity.SesiDAO;

@Component
public class BsSesiExit extends BaseBS {

	@Autowired
	SesiDAO sesiDao;

	protected void execute(BaseBSArea a) throws Exception {
		BsSesiExitArea area = (BsSesiExitArea)a;
		
		//Obtenemos la sesi�n por clave �nica de usuario y validamos su existencia
		Sesi sesi = sesiDao.getByIden(area.IN.iden);
		
		if (sesi == null) {
			notify(CoreNotify.SESI_IDEN_NF);
		}
		
		//Validamos la clave externa de la sesi�n
		if (sesi.getClav() != area.IN.clav) {
			notify(CoreNotify.SESI_CLAV_DIFE);
		}
		
		//Validamos la dirección IP desde la que nos llega la petición
		if (!sesi.getDiip().equals(SESSION.get().AREA_SRCE.DIIP)) {
			notify(CoreNotify.SESI_DIIP_DIFE);
		}

		//Validamos que la sesi�n est� abierta
		if ("A".equals(sesi.getEsta())) {
			sesi.setEsta("C");
		}
		
		sesi.setClav(0);
		sesi.setFeul(SESSION.get().feop);
		sesi.setHoul(SESSION.get().hoop);
		sesi.setFeca(0);
		sesi.setHoca(0);
		sesi.setFere(0);
		sesi.setHore(0);
		
		sesiDao.save(sesi);
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsSesiExitArea area = (BsSesiExitArea)a;
		
		if (area.IN.iden == 0) { notify(CoreNotify.SESI_IDEN_RQRD); }
		//TODO: clave externa dinamica
		//if (area.IN.clav == 0) { notify(CoreNotify.SESI_CLAV_RQRD); }
	}
}
