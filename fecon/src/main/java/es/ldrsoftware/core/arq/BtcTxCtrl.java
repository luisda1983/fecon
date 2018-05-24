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
import es.ldrsoftware.core.btc.bs.BsBtchGetk;
import es.ldrsoftware.core.btc.bs.BsBtchGetkArea;
import es.ldrsoftware.core.btc.bs.BsBtchSave;
import es.ldrsoftware.core.btc.bs.BsBtchSaveArea;
import es.ldrsoftware.core.btc.bs.BsEjecList;
import es.ldrsoftware.core.btc.bs.BsEjecListArea;
import es.ldrsoftware.core.btc.bs.BsEjecPlan;
import es.ldrsoftware.core.btc.bs.BsEjecSave;
import es.ldrsoftware.core.btc.bs.BsEjecSaveArea;
import es.ldrsoftware.core.btc.bs.BsMplaGetk;
import es.ldrsoftware.core.btc.bs.BsMplaGetkArea;
import es.ldrsoftware.core.btc.bs.BsPlanGetk;
import es.ldrsoftware.core.btc.bs.BsPlanGetkArea;
import es.ldrsoftware.core.btc.bs.BsPlanNuev;
import es.ldrsoftware.core.btc.bs.BsPlanNuevArea;
import es.ldrsoftware.core.btc.bs.BsPlanSave;
import es.ldrsoftware.core.btc.bs.BsPlanSaveArea;
import es.ldrsoftware.core.btc.entity.Btch;
import es.ldrsoftware.core.btc.entity.Ejec;
import es.ldrsoftware.core.btc.entity.Mpla;
import es.ldrsoftware.core.btc.entity.Plan;
import es.ldrsoftware.core.btc.job.BtcFbtcAvance;
import es.ldrsoftware.core.btc.job.BtcPlanificador;
import es.ldrsoftware.core.btc.job.BtcStdiAgrup;
import es.ldrsoftware.core.btc.job.BtcStstAgrup;
import es.ldrsoftware.core.fwk.bs.BsParaGet;
import es.ldrsoftware.core.fwk.bs.BsParaGetArea;
import es.ldrsoftware.core.fwk.bs.BsParaSave;
import es.ldrsoftware.core.fwk.bs.BsParaSaveArea;
import es.ldrsoftware.core.fwk.bs.BsParaTbla;
import es.ldrsoftware.core.fwk.bs.BsParaTblaArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.data.PVCtrlperiod;
import es.ldrsoftware.core.fwk.data.PVFechperiod;
import es.ldrsoftware.core.fwk.data.ParaData;
import es.ldrsoftware.core.fwk.entity.Para;

@Component
public class BtcTxCtrl extends BaseNotifyManager {
	
	@Autowired
	BsBtchSave bsBtchSave;

	@Autowired
	BsBtchGetk bsBtchGetk;
	
	@Autowired
	public BsEjecPlan bsEjecPlan;

	@Autowired
	public BsEjecSave bsEjecSave;

	@Autowired
	BsEjecList bsEjecList;

	@Autowired
	public BsParaGet bsParaGet;

	@Autowired
	public BsParaTbla bsParaTbla;
	
	@Autowired
	public BsParaSave bsParaSave;

	@Autowired
	BsMplaGetk bsMplaGetk;

	@Autowired
	BsPlanGetk bsPlanGetk;

	@Autowired
	BsPlanNuev bsPlanNuev;

	@Autowired
	BsPlanSave bsPlanSave;
	
	@Autowired
	public BtcStstAgrup btcStstAgrup;

	@Autowired
	public BtcStdiAgrup btcStdiAgrup;
	
	@Autowired
	public BtcPlanificador btcPlanificador;

	@Autowired
	public BtcFbtcAvance btcFbtcAvance;
	
	private Map<String, PVFechperiod> btchAvanMap;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseController ctrl, BaseBSArea a) throws Exception {
		ctrl.execute(a);
	}
	
	//Inicialización de datos y parámetros que serán requeridos durante el proceso Batch
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void init() throws Exception {
		//Leemos la parametrización de periodicidades de avance en procesos
		BsParaTblaArea paraTblaArea = new BsParaTblaArea();
		paraTblaArea.IN.tbla = ParaData.PARA_TBLA_BTAV;
		bsParaTbla.executeBS(paraTblaArea);
		
		List<Para> paraList = paraTblaArea.OUT.paraList;
		
		ListIterator<Para> it = paraList.listIterator();
	
		//Cargamos los datos, para su acceso rápido durante el proceso Batch
		btchAvanMap = new HashMap<String, PVFechperiod>();
		
		while (it.hasNext()) {
			Para para = it.next();
			PVFechperiod pvFechperiod = (PVFechperiod)para.getPval();
			btchAvanMap.put(para.getClav(), pvFechperiod);
		}
	}

	//Obtención de la fecha/hora asignada al proceso batch
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void getFbatch() throws Exception {
	
		//Leemos los datos de proceso del parámetro correspondiente
		BsParaGetArea paraGetArea = new BsParaGetArea();
		paraGetArea.IN.tbla = ParaData.PARA_TBLA_CPER;
		paraGetArea.IN.clav = ParaData.PARA_ELEM_CPER_FBTC;
		bsParaGet.executeBS(paraGetArea);
		
		PVCtrlperiod fbtcParavalo = (PVCtrlperiod)paraGetArea.OUT.para.getPval();
		
		//Cargamos los datos en la Sesión
		SESSION.get().fbtc = fbtcParavalo.fbtc;
		SESSION.get().fbop = fbtcParavalo.fepr;
		SESSION.get().hbop = fbtcParavalo.hopr / 100;
	}

	//Servicio encargado de avanzar la fecha/hora del proceso
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void nextFbatch() throws Exception {
		
		//Obtenemos el parámetro
		BsParaGetArea paraGetArea = new BsParaGetArea();
		paraGetArea.IN.tbla = ParaData.PARA_TBLA_FPER;
		paraGetArea.IN.clav = ParaData.PARA_ELEM_FPER_RBTC;
		bsParaGet.executeBS(paraGetArea);
		
		PVFechperiod rbtcParavalo = (PVFechperiod)paraGetArea.OUT.para.getPval();
		
		//Obtenemos el periodo de avance configurado en la aplicación
		paraGetArea = new BsParaGetArea();
		paraGetArea.IN.tbla = ParaData.PARA_TBLA_CPER;
		paraGetArea.IN.clav = ParaData.PARA_ELEM_CPER_FBTC;
		bsParaGet.executeBS(paraGetArea);
		
		PVCtrlperiod fbtcParavalo = (PVCtrlperiod)paraGetArea.OUT.para.getPval();
		
		//Avanzamos la fecha/hora
		DateTimeData dtd = new DateTimeData();
		dtd.fech = fbtcParavalo.fepr;
		dtd.hora = fbtcParavalo.hopr;
		
		dtd = DateTimeUtil.fechPeriod(dtd, rbtcParavalo);
		
		//Formatemos en el parámetro los datos obtenidos
		fbtcParavalo.feul = fbtcParavalo.fepr;
		fbtcParavalo.houl = fbtcParavalo.hopr;
		fbtcParavalo.fepr = dtd.fech;
		fbtcParavalo.hopr = dtd.hora;
		
		paraGetArea.OUT.para.setValo(fbtcParavalo.format());
		
		//Grabamos el parámetro
		BsParaSaveArea paraSaveArea = new BsParaSaveArea();
		paraSaveArea.IN.para = paraGetArea.OUT.para;
		bsParaSave.executeBS(paraSaveArea);
	}

	//Servicio que indica si la franja de ejecución está activa
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public Mpla getConfiguration() throws Exception {
	
		//Obtenemos la configuración del Maestro de planificación
		BsMplaGetkArea bsMplaGetkArea = new BsMplaGetkArea();
		bsMplaGetkArea.IN.hora = SESSION.get().hbop;
		bsMplaGetk.executeBS(bsMplaGetkArea);
		
		Mpla mpla = bsMplaGetkArea.OUT.mpla;

		return mpla;
	}

	//Servicio que devuelve el registro de planificación correspondiente al proceso
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public Plan getPlan() throws Exception {
	
		//Obtenemos la planificación
		BsPlanGetkArea bsPlanGetkArea = new BsPlanGetkArea();
		bsPlanGetkArea.IN.fech = SESSION.get().fbop;
		bsPlanGetkArea.IN.hora = SESSION.get().hbop;
		bsPlanGetk.executeBS(bsPlanGetkArea);
		
		Plan plan = bsPlanGetkArea.OUT.plan;

		//Si no existe la planificación, la creamos.
		if (plan == null) {
			BsPlanNuevArea bsPlanNuevArea = new BsPlanNuevArea();
			bsPlanNuevArea.IN.fech = SESSION.get().fbop;
			bsPlanNuevArea.IN.hora = SESSION.get().hbop;
			bsPlanNuevArea.IN.fbtc = SESSION.get().fbtc;
			bsPlanNuev.executeBS(bsPlanNuevArea);
			
			plan = bsPlanNuevArea.OUT.plan;
		} else {
			//Si existe, validamos que esté pendiente
			if (!LiteData.LT_EL_PLANESTA_PENDIENTE.equals(plan.getEsta())) {
				notify(CoreNotify.BTC_CTRL_PLAN_ERRO);
			}
		}
		return plan;
	}

	//Servicio que graba el estado del registro de planificación
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void doPlan(Plan plan) throws Exception {
	
		//Grabamos la planificación
		BsPlanSaveArea bsPlanSaveArea = new BsPlanSaveArea();
		bsPlanSaveArea.IN.plan = plan;
		bsPlanSave.executeBS(bsPlanSaveArea);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public List<Ejec> getJobs() throws Exception {

		BsEjecListArea bsEjecListArea = new BsEjecListArea();
		bsEjecListArea.IN.tipo = BsEjecList.EJEC_LIST_EJEC;
		bsEjecListArea.IN.fech = SESSION.get().fbop;
		bsEjecListArea.IN.hora = SESSION.get().hbop;
		bsEjecListArea.IN.esta = LiteData.LT_EL_EJECESTA_PENDIENTE;
		
		bsEjecList.executeBS(bsEjecListArea);
		
		List<Ejec> ejecList = bsEjecListArea.OUT.ejecList;
		return ejecList;
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void doJob(String iden) throws Exception {
		Btc btc = getBatch(iden);
		
		if (btc == null) {
			notify(CoreNotify.CORE_BTCH_NO_IMPL);
		} 
				
		btc.executeBtc();
		
	}
	
	private Btc getBatch(String btch) {
		
		switch(btch) {
		case "StstAgrup":
			return btcStstAgrup;
		case "StdiAgrup":
			return btcStdiAgrup;
		case "Planificador":
			return btcPlanificador;
		case "FbtcAvance":
			return btcFbtcAvance;
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
	
			BsBtchGetkArea btchGetkArea = new BsBtchGetkArea();
			btchGetkArea.IN.iden = ejec.getBtch();			
			bsBtchGetk.executeBS(btchGetkArea);
			
			Btch btch = btchGetkArea.OUT.btch;

			if (btch == null) {
				notify(CoreNotify.BTC_CTRL_BTCH_NF);
			}
			
			btch.setFeul(SESSION.get().fbop);
			btch.setHoul(SESSION.get().hbop);
			
			if (btch.getFein() == 0) {
				btch.setFein(SESSION.get().fbop);
				btch.setHoin(SESSION.get().hbop);
			}

			BsBtchSaveArea btchSaveArea = new BsBtchSaveArea();
			btchSaveArea.IN.btch = btch;
			bsBtchSave.executeBS(btchSaveArea);
		}
	}
}
