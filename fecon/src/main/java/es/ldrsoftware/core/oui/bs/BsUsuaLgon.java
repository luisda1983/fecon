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
import es.ldrsoftware.core.fwk.entity.Para;
import es.ldrsoftware.core.fwk.entity.Rela;
import es.ldrsoftware.core.oui.entity.Inst;
import es.ldrsoftware.core.oui.entity.Usua;

@Component
public class BsUsuaLgon extends BaseBS {

	@Autowired
	private BsUsuaGetk bsUsuaGet;

	@Autowired
	private BsUsuaSave bsUsuaSave;
	
	@Autowired
	private BsRelaList bsRelaList;

	@Autowired
	private BsSesiOpen bsSesiOpen;

	@Autowired
	private BsInstGetk  bsInstGet;

	@Autowired
	private BsInstSave bsInstSave;
	
	@Autowired
	private BsParaGet  bsParaGet;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaLgonArea area = (BsUsuaLgonArea)a;
		
		//Obtenemos el usuario de la BBDD
		BsUsuaGetkArea bsUsuaGetkArea = new BsUsuaGetkArea();
		bsUsuaGetkArea.IN.iden = area.IN.iden;
		bsUsuaGet.executeBS(bsUsuaGetkArea);
		
		Usua usua = bsUsuaGetkArea.OUT.usua;
	
		//Validamos que exista el usuario
		validateDto(usua, CoreNotify.USUA_LGON_USUA_NF);

		//Validamos que el password introducido coindide
		validateStringEqual(area.IN.pass, usua.getPass(), CoreNotify.USUA_LGON_PASS_ERRO);
		
		//Validamos que el usuario se encuentre activo
		validateStringEqual(LiteData.LT_EL_BOOL_SI, usua.getActi(), CoreNotify.USUA_LGON_ACTI_NO);
				
		//Actualizamos la  última actividad del usuario
		usua.setFeul(SESSION.get().feop);
		usua.setHoul(SESSION.get().hoop);
		
		//Guardamos el usuario en la BBDD
		BsUsuaSaveArea bsUsuaSaveArea = new BsUsuaSaveArea();
		bsUsuaSaveArea.IN.usua = usua;
		bsUsuaSave.executeBS(bsUsuaSaveArea);
		
		usua = bsUsuaSaveArea.OUT.usua;
		
		long instIden = 0;
		
		//El perfil APM no se encuentra asociado a ninguna instalación
		if (!testStringEqual(LiteData.LT_EL_USUAPERF_APM, usua.getPerf())) {
			
			//Consultamos las relaciones INST-USUA
			BsRelaListArea bsRelaListArea = new BsRelaListArea();
			bsRelaListArea.IN.rela = BsRelaList.RELA_LIST_INST_BY_USUA;
			bsRelaListArea.IN.clca = usua.getIden();
			bsRelaList.executeBS(bsRelaListArea);
			
			List<Rela> relaList = bsRelaListArea.OUT.relaList;
			
			//Validamos que el usuario esté relacionado a alguna instalación
			validateListRequired(relaList, CoreNotify.USUA_LGON_RELA_INST_NF);
			
			//Obtenemos el parámetro multiinstalación
			BsParaGetArea bsParaGetArea = new BsParaGetArea();
			bsParaGetArea.IN.tbla = ParaData.PARA_TBLA_APCF;
			bsParaGetArea.IN.clav = ParaData.PARA_ELEM_APCF_MLTI;
			bsParaGet.executeBS(bsParaGetArea);
			
			Para para = bsParaGetArea.OUT.para;
			PVConfigmlti pvConfigmlti = (PVConfigmlti) para.getPval();
			
			//Tratamiento de logon en modo instalación única: abrimos directamente en la instalación asociada
			if (!testStringEqual(LiteData.LT_EL_BOOL_SI, pvConfigmlti.mlti)) {
				//Validamos que no exista más de una instalación si el parámetro está desactivado
				validateListSize(relaList, 1, CoreNotify.USUA_LGON_INST_MLTI_NO);
				
				//Obenemos la instalación asociada al usuario
				BsInstGetkArea bsInstGetArea = new BsInstGetkArea();
				bsInstGetArea.IN.iden = relaList.get(0).getCln1();
				bsInstGet.executeBS(bsInstGetArea);
				
				Inst inst = bsInstGetArea.OUT.inst;
				
				//Validamos que la instalación exista
				validateDto(inst, CoreNotify.USUA_LGON_INST_NF);
				
				//Validamos que se encuentre activa
				validateStringEqual(LiteData.LT_EL_INSTESTA_ACTIVA, inst.getEsta(), CoreNotify.USUA_LGON_INST_ACTI_NO);
				
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

		validateStringRequired(area.IN.iden, CoreNotify.USUA_LGON_IDEN_RQRD);
		validateStringRequired(area.IN.pass, CoreNotify.USUA_LGON_PASS_RQRD);
	}
}