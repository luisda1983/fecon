package es.ldrsoftware.core.fwk.bs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.arq.util.DateTimeData;
import es.ldrsoftware.core.arq.util.DateTimeUtil;
import es.ldrsoftware.core.fwk.data.PVFechperiod;
import es.ldrsoftware.core.fwk.data.ParaData;
import es.ldrsoftware.core.fwk.entity.Sesi;
import es.ldrsoftware.core.fwk.entity.SesiDAO;

@Component
public class BsSesiOpen extends BaseBS {

	@Autowired
	private SesiDAO sesiDao;

	@Autowired
	private BsParaGet bsParaGet;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsSesiOpenArea area = (BsSesiOpenArea)a;
		
		Sesi sesi = sesiDao.getByUsua(area.IN.usua);
		
		if (sesi == null) {
			sesi = new Sesi();
			sesi.setUsua(area.IN.usua);
		}
		
		//TODO: claves externas
		sesi.setClav(0);
		sesi.setDiip(SESSION.get().diip);
		sesi.setPerf(area.IN.perf);
		sesi.setInst(area.IN.inst);
		sesi.setFeap(SESSION.get().feop);
		sesi.setHoap(SESSION.get().hoop);
		sesi.setEsta("A");
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
				
		//TODO: renovaci�n de clave externa con su propio par�metro
		
		sesi = sesiDao.save(sesi);
		
		area.OUT.sesi = sesi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsSesiOpenArea area = (BsSesiOpenArea)a;
		
		if (area.IN.usua == null || "".equals(area.IN.usua)) { notify(CoreNotify.SESI_OPEN_USUA_RQRD); }
		if (area.IN.perf == null || "".equals(area.IN.perf)) { notify(CoreNotify.SESI_OPEN_PERF_RQRD); }
		
		//No se valida el campo de entrada inst, porque se permite abrir sesi�n sin instalaci�n asignada en multi-instalaci�n
	}

}
