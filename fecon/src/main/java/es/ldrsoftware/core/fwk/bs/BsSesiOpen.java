package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.util.DateTimeData;
import es.ldrsoftware.core.arq.util.DateTimeUtil;
import es.ldrsoftware.core.fwk.data.PVFechperiod;
import es.ldrsoftware.core.fwk.data.ParaData;
import es.ldrsoftware.core.fwk.entity.Sesi;

@Component
public class BsSesiOpen extends BaseBS {

	@Autowired
	BsSesiGetu bsSesiGetu;
	
	@Autowired
	BsSesiSave bsSesiSave;
	
	@Autowired
	private BsParaGet bsParaGet;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsSesiOpenArea area = (BsSesiOpenArea)a;
	
		BsSesiGetuArea bsSesiGetuArea = new BsSesiGetuArea();
		bsSesiGetuArea.IN.usua = area.IN.usua;
		bsSesiGetu.executeBS(bsSesiGetuArea);
		
		Sesi sesi = bsSesiGetuArea.OUT.sesi;
		
		if (sesi == null) {
			sesi = new Sesi();
			sesi.setUsua(area.IN.usua);
		}
		
		//TODO: claves externas
		sesi.setClav(0);
		sesi.setDiip(SESSION.get().AREA_SRCE.DIIP);
		sesi.setPerf(area.IN.perf);
		sesi.setDvce(SESSION.get().AREA_SRCE.DEVICE);
		sesi.setInst(area.IN.inst);
		sesi.setFeap(SESSION.get().feop);
		sesi.setHoap(SESSION.get().hoop);
		sesi.setEsta("A");
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
				
		//TODO: renovación de clave externa con su propio parámetro
		
		BsSesiSaveArea bsSesiSaveArea = new BsSesiSaveArea();
		bsSesiSaveArea.IN.sesi = sesi;
		bsSesiSave.executeBS(bsSesiSaveArea);
		
		area.OUT.sesi = bsSesiSaveArea.OUT.sesi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsSesiOpenArea area = (BsSesiOpenArea)a;

		validateInputField(area.IN.usua, Sesi.USUA);
		validateInputField(area.IN.perf, Sesi.PERF);
		
		//No se valida el campo de entrada inst, porque se permite abrir sesión sin instalación asignada en multi-instalación
	}

}
