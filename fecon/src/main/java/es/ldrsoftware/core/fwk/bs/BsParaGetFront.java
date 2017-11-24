package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.PVParamaster;
import es.ldrsoftware.core.fwk.data.PVParser;
import es.ldrsoftware.core.fwk.data.ParaData;
import es.ldrsoftware.core.fwk.entity.Para;
import es.ldrsoftware.core.fwk.entity.ParaDAO;

@Component
public class BsParaGetFront extends BaseBS {

	@Autowired
	ParaDAO paraDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsParaGetFrontArea area = (BsParaGetFrontArea)a;
		
		//Buscamos la definición de la tabla
		Para para = paraDao.getByTblaClav(ParaData.PARA_TBLA_MSTR, area.IN.tbla);
		
		if (para == null) {
			notify(CoreNotify.PARA_GETP_FRNT_MSTR_NF);
		}
		
		PVParser.parse(para);
		PVParamaster pvParamaster = (PVParamaster)para.getPval();
		
		//Verificamos si es una tabla consultable desde el Front
		if (!"S".equals(pvParamaster.cons)) {
			notify(CoreNotify.PARA_GETP_FRNT_CONS_NO);
		}
		
		//Obtenemos el elemento solicitado y lo devolvemos en la salida
		para = paraDao.getByTblaClav(area.IN.tbla, area.IN.clav);
		
		if (para == null) {
			notify(CoreNotify.PARA_GETP_FRNT_NF);
		}
	
		PVParser.parse(para);
		
		area.OUT.para = para;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsParaGetFrontArea area = (BsParaGetFrontArea)a;
		
		//Tipo de parámetro I-Instalación, la clave será el código de instalación
		//formateado en 10 posiciones.
		if ("I".equals(area.IN.tipo)) {
			Long l = SESSION.get().inst;
			String s = l.toString();
			while (s.length() < 10) {
				s = "0" + s;
			}
			area.IN.clav = s;
		}
		
		if (area.IN.tbla == null || "".equals(area.IN.tbla)) { notify(CoreNotify.PARA_GETP_FRNT_TBLA_RQRD); }
		if (area.IN.clav == null || "".equals(area.IN.clav)) { notify(CoreNotify.PARA_GETP_FRNT_CLAV_RQRD); }
		
	}
}
