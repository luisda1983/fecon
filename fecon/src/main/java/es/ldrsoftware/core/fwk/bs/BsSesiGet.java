package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.arq.util.DateTimeData;
import es.ldrsoftware.core.arq.util.DateTimeUtil;
import es.ldrsoftware.core.arq.util.SesiUtil;
import es.ldrsoftware.core.fwk.data.PVFechperiod;
import es.ldrsoftware.core.fwk.data.ParaData;
import es.ldrsoftware.core.fwk.entity.Sesi;
import es.ldrsoftware.core.fwk.entity.SesiDAO;

@Component
public class BsSesiGet extends BaseBS {

	@Autowired
	SesiDAO sesiDao;

	@Autowired
	BsParaGet bsParaGet;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsSesiGetArea area = (BsSesiGetArea)a;
		
		//Obtenemos la sesi�n por clave �nica de usuario y validamos su existencia
		Sesi sesi = sesiDao.getByIden(area.IN.iden);
		
		if (sesi == null) {
			notify(CoreNotify.SESI_IDEN_NF);
		}
		
		//Validamos la clave externa de la sesi�n
		if (sesi.getClav() != area.IN.clav) {
			notify(CoreNotify.SESI_CLAV_DIFE);
		}
		
		//Validamos que la sesi�n est� abierta
		if (!"A".equals(sesi.getEsta())) {
			notify(CoreNotify.SESI_ESTA_NO_ABIE);
		}
		
		//Validamos la dirección IP desde la que nos llega la petición
		if (!sesi.getDiip().equals(SESSION.get().AREA_SRCE.DIIP)) {
			notify(CoreNotify.SESI_DIIP_DIFE);
		}
		
		//Validamos que la sessi�n no haya caducado
		if (!SesiUtil.isSesiActi(sesi)) {
			notify(CoreNotify.SESI_CADU);
		}
		
		//Actualizamos la ultima operaci�n de la sesi�n
		sesi.setFeul(SESSION.get().feop);
		sesi.setHoul(SESSION.get().hoop);
	
		//Recuperamos el par�metro de configuraci�n de periodo de renovaci�n de sesi�n
		BsParaGetArea paraGetArea = new BsParaGetArea();
		paraGetArea.IN.tbla = ParaData.PARA_TBLA_FPER;
		paraGetArea.IN.clav = ParaData.PARA_ELEM_FPER_RSES;
		bsParaGet.executeBS(paraGetArea);
		
		DateTimeData dataIn = new DateTimeData();
		dataIn.fech = SESSION.get().feop;
		dataIn.hora = SESSION.get().hoop;
		
		DateTimeData dataOut = DateTimeUtil.fechPeriod(dataIn, (PVFechperiod)paraGetArea.OUT.para.getPval());
		
		sesi.setFeca(dataOut.fech);
		sesi.setHoca(dataOut.hora);
				
		//TODO: renovacion de la clave externa, con su propio par�metro
		
		//Guardamos la sesi�n
		sesiDao.save(sesi);
		
		area.OUT.sesi = sesi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsSesiGetArea area = (BsSesiGetArea)a;
		
		if (area.IN.iden == 0) { notify(CoreNotify.SESI_IDEN_RQRD); }
		//TODO: clave externa dinamica
		//if (area.IN.clav == 0) { notify(CoreNotify.SESI_CLAV_RQRD); }
	}

}
