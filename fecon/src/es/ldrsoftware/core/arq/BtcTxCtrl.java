package es.ldrsoftware.core.arq;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.arq.util.DateTimeData;
import es.ldrsoftware.core.arq.util.DateTimeUtil;
import es.ldrsoftware.core.btc.bs.BsBtchGet;
import es.ldrsoftware.core.btc.bs.BsBtchGetArea;
import es.ldrsoftware.core.btc.bs.BsBtchGetPlan;
import es.ldrsoftware.core.btc.bs.BsBtchGetPlanArea;
import es.ldrsoftware.core.btc.bs.BsBtchSave;
import es.ldrsoftware.core.btc.bs.BsBtchSaveArea;
import es.ldrsoftware.core.btc.bs.BsEjecGetList;
import es.ldrsoftware.core.btc.bs.BsEjecGetListArea;
import es.ldrsoftware.core.btc.bs.BsEjecPlan;
import es.ldrsoftware.core.btc.bs.BsEjecPlanArea;
import es.ldrsoftware.core.btc.bs.BsEjecSave;
import es.ldrsoftware.core.btc.bs.BsEjecSaveArea;
import es.ldrsoftware.core.btc.entity.Btch;
import es.ldrsoftware.core.btc.entity.Ejec;
import es.ldrsoftware.core.btc.job.BtcStdiAgrup;
import es.ldrsoftware.core.btc.job.BtcStstAgrup;
import es.ldrsoftware.core.fwk.bs.BsParaGet;
import es.ldrsoftware.core.fwk.bs.BsParaGetArea;
import es.ldrsoftware.core.fwk.bs.BsParaSave;
import es.ldrsoftware.core.fwk.bs.BsParaSaveArea;
import es.ldrsoftware.core.fwk.bs.BsParaTbla;
import es.ldrsoftware.core.fwk.bs.BsParaTblaArea;
import es.ldrsoftware.core.fwk.data.PVCtrlperiod;
import es.ldrsoftware.core.fwk.data.PVFechperiod;
import es.ldrsoftware.core.fwk.data.ParaData;
import es.ldrsoftware.core.fwk.entity.Para;

@Component
public class BtcTxCtrl extends BaseNotifyManager {
	
	@Autowired
	public BsBtchGetPlan bsBtchGetPlan;

	@Autowired
	public BsBtchSave bsBtchSave;

	@Autowired
	public BsBtchGet bsBtchGet;
	
	@Autowired
	public BsEjecPlan bsEjecPlan;

	@Autowired
	public BsEjecSave bsEjecSave;

	@Autowired
	public BsEjecGetList bsEjecGetList;

	@Autowired
	public BsParaGet bsParaGet;

	@Autowired
	public BsParaTbla bsParaTbla;
	
	@Autowired
	public BsParaSave bsParaSave;

	
	@Autowired
	public BtcStstAgrup btcStstAgrup;

	@Autowired
	public BtcStdiAgrup btcStdiAgrup;
	
	private Map<String, PVFechperiod> btchAvanMap;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseController ctrl, BaseBSArea a) throws Exception {
		ctrl.execute(a);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void init() throws Exception {
		BsParaTblaArea paraTblaArea = new BsParaTblaArea();
		paraTblaArea.IN.tbla = ParaData.PARA_TBLA_BTAV;
		bsParaTbla.executeBS(paraTblaArea);
		
		List<Para> paraList = paraTblaArea.OUT.paraList;
		
		ListIterator<Para> it = paraList.listIterator();
	
		btchAvanMap = new HashMap<String, PVFechperiod>();
		
		while (it.hasNext()) {
			Para para = it.next();
			PVFechperiod pvFechperiod = (PVFechperiod)para.getPval();
			btchAvanMap.put(para.getClav(), pvFechperiod);
		}
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void getFbatch() throws Exception {
	
		BsParaGetArea paraGetArea = new BsParaGetArea();
		paraGetArea.IN.tbla = ParaData.PARA_TBLA_CPER;
		paraGetArea.IN.clav = ParaData.PARA_ELEM_CPER_FBTC;
		bsParaGet.executeBS(paraGetArea);
		
		PVCtrlperiod fbtcParavalo = (PVCtrlperiod)paraGetArea.OUT.para.getPval();
		
		SESSION.get().fbtc = fbtcParavalo.fepr;
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void nextFbatch() throws Exception {
		
		BsParaGetArea paraGetArea = new BsParaGetArea();
		paraGetArea.IN.tbla = ParaData.PARA_TBLA_FPER;
		paraGetArea.IN.clav = ParaData.PARA_ELEM_FPER_RBTC;
		bsParaGet.executeBS(paraGetArea);
		
		PVFechperiod rbtcParavalo = (PVFechperiod)paraGetArea.OUT.para.getPval();
		
		paraGetArea = new BsParaGetArea();
		paraGetArea.IN.tbla = ParaData.PARA_TBLA_CPER;
		paraGetArea.IN.clav = ParaData.PARA_ELEM_CPER_FBTC;
		bsParaGet.executeBS(paraGetArea);
		
		PVCtrlperiod fbtcParavalo = (PVCtrlperiod)paraGetArea.OUT.para.getPval();
		
		DateTimeData dtd = new DateTimeData();
		dtd.fech = fbtcParavalo.fepr;
		dtd.hora = fbtcParavalo.hopr;
		
		dtd = DateTimeUtil.fechPeriod(dtd, rbtcParavalo);
		
		fbtcParavalo.feul = fbtcParavalo.fepr;
		fbtcParavalo.houl = fbtcParavalo.hopr;
		fbtcParavalo.fepr = dtd.fech;
		fbtcParavalo.hopr = dtd.hora;
		
		paraGetArea.OUT.para.setValo(fbtcParavalo.format());
		
		BsParaSaveArea paraSaveArea = new BsParaSaveArea();
		paraSaveArea.IN.para = paraGetArea.OUT.para;
		bsParaSave.executeBS(paraSaveArea);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void doPlanification() throws Exception {
		
		BsBtchGetPlanArea btchGetPlanArea = new BsBtchGetPlanArea();
		bsBtchGetPlan.executeBS(btchGetPlanArea);
		
		List<Btch> btchList = btchGetPlanArea.OUT.btchList;
		
		ListIterator<Btch> it = btchList.listIterator();
		
		while (it.hasNext()) {
			
			Btch btch = it.next();

			BsEjecPlanArea ejecPlanArea = new BsEjecPlanArea();
			ejecPlanArea.IN.btch = btch.getIden();
			ejecPlanArea.IN.fech = btch.getFepr();
			ejecPlanArea.IN.secu = 0;
			ejecPlanArea.IN.orde = btch.getOrde();
			bsEjecPlan.executeBS(ejecPlanArea);
			
			btch.setPlan("S");
			btch.setFein(SESSION.get().fbtc);
			
			BsBtchSaveArea btchSaveArea = new BsBtchSaveArea();
			btchSaveArea.IN.btch = btch;
			
			bsBtchSave.executeBS(btchSaveArea);
		}
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public List<Ejec> getJobs() throws Exception {

		BsEjecGetListArea ejecGetListArea = new BsEjecGetListArea();
		ejecGetListArea.IN.fech = SESSION.get().fbtc;
		ejecGetListArea.IN.esta = "P";
		
		bsEjecGetList.executeBS(ejecGetListArea);
		
		List<Ejec> ejecList = ejecGetListArea.OUT.ejecList;
		return ejecList;
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void doJob(String iden) throws Exception {
		Btc btc = getBatch(iden);
		
		if (btc == null) {
			notify(CoreNotify.CORE_BTCH_NO_IMPL);
		} 
				
		btc.executeBtc(SESSION.get().fbtc);
		
	}
	
	private Btc getBatch(String btch) {
		
		switch(btch) {
		case "StstAgrup":
			return btcStstAgrup;
		case "StdiAgrup":
			return btcStdiAgrup;
		default:
			return null;
		}
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void doResult(Ejec ejec) throws Exception {
		
		BsEjecSaveArea ejecSaveArea = new BsEjecSaveArea();
		ejecSaveArea.IN.ejec = ejec;
		
		bsEjecSave.executeBS(ejecSaveArea);
		
		ejec = ejecSaveArea.OUT.ejec;
		
		if (!"V".equals(ejec.getEsta())) {
	
			BsBtchGetArea btchGetArea = new BsBtchGetArea();
			btchGetArea.IN.iden = ejec.getBtch();
			
			bsBtchGet.executeBS(btchGetArea);
			
			Btch btch = btchGetArea.OUT.btch;
			
			if (btch == null) {
				notify(CoreNotify.BTCH_NF);
			}
			
			BsEjecPlanArea ejecPlanArea = new BsEjecPlanArea();
			ejecPlanArea.IN.btch = ejec.getBtch();
			ejecPlanArea.IN.fech = getFepr(ejec.getFech(), btch.getTipo());
			ejecPlanArea.IN.secu = 0;
			ejecPlanArea.IN.orde = btch.getOrde();
			bsEjecPlan.executeBS(ejecPlanArea);
			
			btch.setFeul(ejec.getFech());
			btch.setFepr(ejecPlanArea.OUT.ejec.getFech());

			BsBtchSaveArea btchSaveArea = new BsBtchSaveArea();
			btchSaveArea.IN.btch = btch;
			bsBtchSave.executeBS(btchSaveArea);
		}
	}
	
	private int getFepr(int fech, String tipo) throws Exception {
		int fepr = fech;
		
		PVFechperiod peri = btchAvanMap.get(tipo);
		
		if (peri == null) {
			notify(CoreNotify.BTCH_TIPO_ERRO);
		}
		
		DateTimeData dtd = new DateTimeData();
		dtd.fech = fech;
		dtd.hora = 0;
		dtd = DateTimeUtil.fechPeriod(dtd, peri);
		
		fepr = dtd.fech;		
		return fepr;
	}
}
