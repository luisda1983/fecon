package es.ldrsoftware.core.oui.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.bs.BsParaGet;
import es.ldrsoftware.core.fwk.bs.BsParaGetArea;
import es.ldrsoftware.core.fwk.bs.BsRelaList;
import es.ldrsoftware.core.fwk.bs.BsRelaListArea;
import es.ldrsoftware.core.fwk.bs.BsSesiOpen;
import es.ldrsoftware.core.fwk.bs.BsSesiOpenArea;
import es.ldrsoftware.core.fwk.data.PVConfigmlti;
import es.ldrsoftware.core.fwk.data.ParaData;
import es.ldrsoftware.core.fwk.entity.Para;
import es.ldrsoftware.core.fwk.entity.Rela;
import es.ldrsoftware.core.oui.entity.Inst;
import es.ldrsoftware.core.oui.entity.Usua;

@Component
public class BsUsuaLgon extends BaseBS {

	@Autowired
	private BsUsuaGet bsUsuaGet;

	@Autowired
	private BsUsuaSave bsUsuaSave;
	
	@Autowired
	private BsRelaList bsRelaList;

	@Autowired
	private BsSesiOpen bsSesiOpen;

	@Autowired
	private BsInstGet  bsInstGet;

	@Autowired
	private BsInstSave bsInstSave;
	
	@Autowired
	private BsParaGet  bsParaGet;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaLgonArea area = (BsUsuaLgonArea)a;
		
		//Obtenemos el usuario de la BBDD
		BsUsuaGetArea bsUsuaGetArea = new BsUsuaGetArea();
		bsUsuaGetArea.IN.iden = area.IN.iden;
		bsUsuaGet.executeBS(bsUsuaGetArea);
		
		Usua usua = bsUsuaGetArea.OUT.usua;
		
		//Validamos su existencia
		if (usua == null) { notify(CoreNotify.USUA_LGON_USUA_NF); }
		
		//Validamos que el password introducido coindide
		if (!area.IN.pass.equals(usua.getPass())) { notify(CoreNotify.USUA_LGON_PASS_ERRO); }

		//Validamos que se encuentre activo
		if (!"S".equals(usua.getActi())) { notify(CoreNotify.USUA_LGON_ACTI_NO); }
				
		//Actualizamos la última actividad del usuario
		usua.setFeul(SESSION.get().feop);
		usua.setHoul(SESSION.get().hoop);
		
		//Guardamos el usuario en la BBDD
		BsUsuaSaveArea bsUsuaSaveArea = new BsUsuaSaveArea();
		bsUsuaSaveArea.IN.usua = usua;
		bsUsuaSave.executeBS(bsUsuaSaveArea);
		
		long instIden = 0;
		
		//El perfil APM no se encuentra asociado a ninguna instalación
		if (!"APM".equals(usua.getPerf())) {
			
			//Consultamos las relaciones INST-USUA
			BsRelaListArea bsRelaListArea = new BsRelaListArea();
			bsRelaListArea.IN.rela = BsRelaList.RELA_LIST_INST_BY_USUA;
			bsRelaListArea.IN.clca = usua.getIden();
			bsRelaList.executeBS(bsRelaListArea);
			
			List<Rela> relaList = bsRelaListArea.OUT.relaList;
			
			//Validamos que el usuario esté relacionado a alguna instalación
			if (relaList == null || relaList.size() == 0) { notify(CoreNotify.USUA_LGON_INST_NO); }
			
			//Obtenemos el parámetro multiinstalación
			BsParaGetArea bsParaGetArea = new BsParaGetArea();
			bsParaGetArea.IN.tbla = ParaData.PARA_TBLA_APCF;
			bsParaGetArea.IN.clav = ParaData.PARA_ELEM_APCF_MLTI;
			bsParaGet.executeBS(bsParaGetArea);
			
			Para para = bsParaGetArea.OUT.para;
			PVConfigmlti pvConfigmlti = (PVConfigmlti) para.getPval();
			
			//Tratamiento de logon en modo instalación única: abrimos directamente en la instalación asociada
			if (!"S".equals(pvConfigmlti.mlti)) {
				//Validamos que no exista más de una instalación si el parámetro está desactivado
				if (relaList.size() > 1) { notify(CoreNotify.USUA_LGON_INST_MLTI_NO); }
				
				//Obenemos la instalación asociada al usuario
				BsInstGetArea bsInstGetArea = new BsInstGetArea();
				bsInstGetArea.IN.iden = relaList.get(0).getCln1();
				bsInstGet.executeBS(bsInstGetArea);
				
				Inst inst = bsInstGetArea.OUT.inst;
				
				//Validamos su existencia
				if (inst == null) { notify(CoreNotify.USUA_LGON_INST_NF); }
				
				//Validamos que se encuentre activa
				if (!"A".equals(inst.getEsta())) { notify(CoreNotify.USUA_LGON_INST_ACTI_NO); }
				
				//Nos guardamos el identificador de la instalación, para la apertura de sesión
				instIden = inst.getIden();
				
				//Actualizamos la última actividad de la instalación
				inst.setFeul(SESSION.get().feop);
				
				//Guardamos la instalación en BBDD
				BsInstSaveArea bsInstSaveArea = new BsInstSaveArea();
				bsInstSaveArea.IN.inst = inst;
				bsInstSave.executeBS(bsInstSaveArea);
				
			}
		}
		
		BsSesiOpenArea bsSesiOpenArea = new BsSesiOpenArea();
		bsSesiOpenArea.IN.usua = usua.getIden();
		bsSesiOpenArea.IN.perf = usua.getPerf();
		bsSesiOpenArea.IN.inst = instIden;
		bsSesiOpen.executeBS(bsSesiOpenArea);
		
		area.OUT.usua = usua;
		area.OUT.sesi = bsSesiOpenArea.OUT.sesi.getIden();
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsUsuaLgonArea area = (BsUsuaLgonArea)a;
		
		if (area.IN.iden == null || "".equals(area.IN.iden)) { notify(CoreNotify.USUA_LGON_IDEN_RQRD); }
		if (area.IN.pass == null || "".equals(area.IN.pass)) { notify(CoreNotify.USUA_LGON_PASS_RQRD); }
		
	}
}