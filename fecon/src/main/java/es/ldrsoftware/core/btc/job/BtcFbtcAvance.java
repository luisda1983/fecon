package es.ldrsoftware.core.btc.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.Btc;
import es.ldrsoftware.core.arq.LoggerManager;
import es.ldrsoftware.core.arq.util.DateTimeData;
import es.ldrsoftware.core.arq.util.DateTimeUtil;
import es.ldrsoftware.core.fwk.bs.BsParaGet;
import es.ldrsoftware.core.fwk.bs.BsParaGetArea;
import es.ldrsoftware.core.fwk.bs.BsParaSave;
import es.ldrsoftware.core.fwk.bs.BsParaSaveArea;
import es.ldrsoftware.core.fwk.data.PVCtrlperiod;
import es.ldrsoftware.core.fwk.data.PVFechperiod;
import es.ldrsoftware.core.fwk.data.ParaData;
import es.ldrsoftware.core.fwk.entity.Para;

@Component
public class BtcFbtcAvance extends Btc {

	@Autowired
	LoggerManager logger;

	@Autowired
	BsParaGet bsParaGetk;

	@Autowired
	BsParaSave bsParaSave;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void executeBtc() throws Exception {

		logger.logProc("FbtcAvance", 0, "EJECUCION DE PROCESO: FbtcAvance");
		logger.logProc("FbtcAvance", 4, "Fecha de ejecución: " + SESSION.get().feop);
		logger.logProc("FbtcAvance", 4, "Hora de ejecución: " + SESSION.get().hoop);
		logger.logProc("FbtcAvance", 4, "Fecha de proceso: " + SESSION.get().fbtc);
		
		//Leemos la parametrización de periodicidades de avance en procesos
		BsParaGetArea bsParaGetkArea = new BsParaGetArea();
		bsParaGetkArea.IN.tbla = ParaData.PARA_TBLA_CPER;
		bsParaGetkArea.IN.clav = ParaData.PARA_ELEM_CPER_FBTC;
		bsParaGetk.executeBS(bsParaGetkArea);
		
		Para para = bsParaGetkArea.OUT.para;
		PVCtrlperiod pvCtrlperiod = (PVCtrlperiod)para.getPval();

		logger.logProc("FbtcAvance", 1, "Periodicidad de Control. ");
		logger.logProc("FbtcAvance", 2, "Fecha de última ejecución: " + pvCtrlperiod.feul);
		logger.logProc("FbtcAvance", 2, "Hora de última ejecución: " + pvCtrlperiod.houl);
		logger.logProc("FbtcAvance", 2, "Fecha de próxima ejecución: " + pvCtrlperiod.fepr);
		logger.logProc("FbtcAvance", 2, "Hora de próxima ejecución: " + pvCtrlperiod.hopr);
		logger.logProc("FbtcAvance", 2, "Fecha Batch: " + pvCtrlperiod.fbtc);
		
		//Montamos la fecha/hora de referencia
		DateTimeData dtdFbtc = new DateTimeData();
		dtdFbtc.fech = pvCtrlperiod.fbtc;
		dtdFbtc.hora = 0;
		
		//Montamos el periodo de avance: 1 día
		PVFechperiod pv = new PVFechperiod();
		pv.avre = "+";
		pv.DD = 1;
		
		//Avanzamos 1 día la fecha Batch
		dtdFbtc = DateTimeUtil.fechPeriod(dtdFbtc, pv);

		logger.logProc("FbtcAvance", 0, " ");
		logger.logProc("FbtcAvance", 2, "Nueva Fecha Batch: " + dtdFbtc.fech);
		
		pvCtrlperiod.fbtc = dtdFbtc.fech;
		para.setValo(pvCtrlperiod.format());
		
		BsParaSaveArea bsParaSaveArea = new BsParaSaveArea();
		bsParaSaveArea.IN.para = para;
		bsParaSave.executeBS(bsParaSaveArea);
		
		logger.logProc("FbtcAvance", 0, "FIN DE EJECUCION DE PROCESO: FbtcAvance");
	}
}
