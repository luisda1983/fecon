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
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.data.PVConfigmlti;
import es.ldrsoftware.core.fwk.data.ParaData;
import es.ldrsoftware.core.fwk.data.RDInstUsua;
import es.ldrsoftware.core.fwk.entity.Para;
import es.ldrsoftware.core.fwk.entity.Rela;
import es.ldrsoftware.core.fwk.entity.Sesi;
import es.ldrsoftware.core.oui.entity.Inst;
import es.ldrsoftware.core.oui.entity.Usua;

@Component
public class BsUsuaLgon extends BaseBS {

	@Autowired
	BsUsuaVali bsUsuaVali;
	
	@Autowired
	BsUsuaSave bsUsuaSave;
	
	@Autowired
	BsRelaList bsRelaList;

	@Autowired
	BsSesiOpen bsSesiOpen;

	@Autowired
	BsInstGetk  bsInstGet;

	@Autowired
	BsInstSave bsInstSave;
	
	@Autowired
	BsParaGet  bsParaGet;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaLgonArea area = (BsUsuaLgonArea)a;
		
		//Validamos credenciales del usuario
		BsUsuaValiArea bsUsuaValiArea = new BsUsuaValiArea();
		bsUsuaValiArea.IN.iden = area.IN.iden;
		bsUsuaValiArea.IN.pass = area.IN.pass;
		bsUsuaVali.executeBS(bsUsuaValiArea);
		
		Usua usua = bsUsuaValiArea.OUT.usua;
				
		//Actualizamos la  última actividad del usuario
		usua.setFeul(SESSION.get().feop);
		usua.setHoul(SESSION.get().hoop);
		
		//Guardamos el usuario en la BBDD
		BsUsuaSaveArea bsUsuaSaveArea = new BsUsuaSaveArea();
		bsUsuaSaveArea.IN.usua = usua;
		bsUsuaSave.executeBS(bsUsuaSaveArea);
		
		usua = bsUsuaSaveArea.OUT.usua;
			
		//Consultamos las relaciones INST-USUA
		BsRelaListArea bsRelaListArea = new BsRelaListArea();
		bsRelaListArea.IN.rela = BsRelaList.RELA_LIST_INST_BY_USUA;
		bsRelaListArea.IN.clc2 = usua.getIden();
		bsRelaList.executeBS(bsRelaListArea);
			
		List<Rela> relaList = bsRelaListArea.OUT.relaList;
			
		//Validamos que el usuario esté relacionado a alguna instalación
		testEmpty(relaList, CoreNotify.USUA_LGON_RELA_INST_NF);
		
		//Obtenemos el parámetro multiinstalación
		BsParaGetArea bsParaGetArea = new BsParaGetArea();
		bsParaGetArea.IN.tbla = ParaData.PARA_TBLA_APCF;
		bsParaGetArea.IN.clav = ParaData.PARA_ELEM_APCF_MLTI;
		bsParaGet.executeBS(bsParaGetArea);
			
		Para para = bsParaGetArea.OUT.para;
		PVConfigmlti pvConfigmlti = (PVConfigmlti) para.getPval();
			
		//Validamos que no exista más de una instalación si el parámetro está en modo instalación única 
		if (!equal(LiteData.LT_EL_BOOL_SI, pvConfigmlti.mlti)) {
			//Validamos que no exista más de una instalación si el parámetro está desactivado
			test(false, relaList.size(), 1, CoreNotify.USUA_LGON_INST_MLTI_NO);
		}
		
		//Abriremos la sesión sobre la primera instalación
		Rela rela = relaList.get(0);

		long instIden = 0;
		
		//El perfil APM no está asociado a ninguna instalación
		if (!LiteData.LT_EL_USUAPERF_APM.equals(((RDInstUsua)rela.getRdat()).perf)) {
			//Obenemos la instalación asociada al usuario
			BsInstGetkArea bsInstGetArea = new BsInstGetkArea();
			bsInstGetArea.IN.iden = rela.getCln1();
			bsInstGet.executeBS(bsInstGetArea);
			
			Inst inst = bsInstGetArea.OUT.inst;
			
			//Validamos que la instalación exista
			validateDtoNotFound(inst, LiteData.LT_EL_DTO_INST, Inst.key(rela.getCln1()));
			
			//Validamos que se encuentre activa
			test(false, LiteData.LT_EL_INSTESTA_ACTIVA, inst.getEsta(), CoreNotify.USUA_LGON_INST_ACTI_NO);
				
			//Nos guardamos el identificador de la instalación, para la apertura de sesión
			instIden = inst.getIden();
				
			//Actualizamos la última actividad de la instalación
			inst.setFeul(SESSION.get().feop);
				
			//Guardamos la instalación en BBDD
			BsInstSaveArea bsInstSaveArea = new BsInstSaveArea();
			bsInstSaveArea.IN.inst = inst;
			bsInstSave.executeBS(bsInstSaveArea);	
		}
		
		//Realizamos la apertura de la sesión
		BsSesiOpenArea bsSesiOpenArea = new BsSesiOpenArea();
		bsSesiOpenArea.IN.usua = usua.getIden();
		bsSesiOpenArea.IN.perf = ((RDInstUsua)rela.getRdat()).perf;
		bsSesiOpenArea.IN.inst = instIden;
		bsSesiOpen.executeBS(bsSesiOpenArea);
		
		Sesi sesi = bsSesiOpenArea.OUT.sesi;
		
		area.OUT.usua = usua;
		area.OUT.sesi = sesi.getIden();
		
		if (LiteData.LT_EL_USUAPERF_APM.equals(sesi.getPerf())) {
			area.OUT.diip = SESSION.get().AREA_SRCE.DIIP;
		} else {
			area.OUT.diip = "";
		}
		
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsUsuaLgonArea area = (BsUsuaLgonArea)a;

		//Validamos que el identificador de usuario está informado
		validateInputField(area.IN.iden, Usua.IDEN);
		
		//Validamos que el password está informado
		validateInputField(area.IN.pass, Usua.PASS);
	}
}