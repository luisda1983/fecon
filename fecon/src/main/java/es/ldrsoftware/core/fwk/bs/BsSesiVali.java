package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.arq.util.DateTimeData;
import es.ldrsoftware.core.arq.util.DateTimeUtil;
import es.ldrsoftware.core.arq.util.SesiUtil;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.data.PVFechperiod;
import es.ldrsoftware.core.fwk.data.ParaData;
import es.ldrsoftware.core.fwk.entity.Sesi;

@Component
public class BsSesiVali extends BaseBS {

	@Autowired
	BsSesiGetk bsSesiGetk;

	@Autowired
	BsSesiSave bsSesiSave;
	
	@Autowired
	BsParaGet bsParaGet;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsSesiValiArea area = (BsSesiValiArea)a;
		
		//Obtenemos la sesión
		BsSesiGetkArea bsSesiGetkArea = new BsSesiGetkArea();
		bsSesiGetkArea.IN.iden = area.IN.iden;
		bsSesiGetk.executeBS(bsSesiGetkArea);
		
		Sesi sesi = bsSesiGetkArea.OUT.sesi;
		
		if (sesi == null) {
			notify(CoreNotify.SESI_VALI_SESI_NF);
		}
		
		//Validamos la clave externa de la sesión
		if (sesi.getClav() != area.IN.clav) {
			notify(CoreNotify.SESI_VALI_CLAV_DIFE);
		}
		
		//Validamos que la sesión está abierta
		if (!LiteData.LT_EL_SESIESTA_ABIERTA.equals(sesi.getEsta())) {
			notify(CoreNotify.SESI_VALI_ESTA_NO_ABIE);
		}
		
		//Validamos la dirección IP desde la que nos llega la petición
		if (!sesi.getDiip().equals(SESSION.get().AREA_SRCE.DIIP)) {
			notify(CoreNotify.SESI_VALI_DIIP_DIFE);
		}
		
		//Validamos que la sessión no haya caducado
		if (!SesiUtil.isSesiActi(sesi)) {
			notify(CoreNotify.SESI_VALI_SESI_CADU);
		}
		
		//Actualizamos la ultima operación de la sesión
		sesi.setFeul(SESSION.get().feop);
		sesi.setHoul(SESSION.get().hoop);
	
		//Recuperamos el parámetro de configuración de periodo de renovación de sesión
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
				
		//TODO: renovacion de la clave externa, con su propio parámetro
		
		//Guardamos la sesión
		BsSesiSaveArea bsSesiSaveArea = new BsSesiSaveArea();
		bsSesiSaveArea.IN.sesi = sesi;
		bsSesiSave.executeBS(bsSesiSaveArea);
		
		area.OUT.sesi = bsSesiSaveArea.OUT.sesi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsSesiValiArea area = (BsSesiValiArea)a;
		
		validateIntRequired(area.IN.iden, CoreNotify.SESI_VALI_IDEN_RQRD);
		
		//TODO: clave externa dinamica
		//if (area.IN.clav == 0) { notify(CoreNotify.SESI_CLAV_RQRD); }
	}

}
