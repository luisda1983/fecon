package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.bs.BsParaGet;
import es.ldrsoftware.core.fwk.bs.BsParaGetArea;
import es.ldrsoftware.core.fwk.bs.BsRelaInstUsua;
import es.ldrsoftware.core.fwk.bs.BsRelaInstUsuaArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.data.PVConfigmlti;
import es.ldrsoftware.core.fwk.data.PVConfregist;
import es.ldrsoftware.core.fwk.data.PVDynamicfld;
import es.ldrsoftware.core.fwk.data.ParaData;
import es.ldrsoftware.core.oui.entity.Inst;
import es.ldrsoftware.core.oui.entity.Usua;

@Component
public class BsInstRegi extends BaseBS {
	
	@Autowired
	BsParaGet bsParaGet;

	@Autowired
	BsInstGetd bsInstGetd;
	
	@Autowired
	BsInstSave bsInstSave;

	@Autowired
	BsInstCodiGene bsInstCodiGene;
	
	@Autowired
	BsUsuaRegi bsUsuaRegi;

	@Autowired
	BsUsuaVali bsUsuaVali;
	
	@Autowired
	BsRelaInstUsua bsRelaInstUsua;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInstRegiArea area = (BsInstRegiArea)a;
		
		//Obtenemos la configuración del registro en la aplicación
		BsParaGetArea bsParaGetArea = new BsParaGetArea();
		bsParaGetArea.IN.tbla = ParaData.PARA_TBLA_APCF;
		bsParaGetArea.IN.clav = ParaData.PARA_ELEM_APCF_CFRG;
		bsParaGet.executeBS(bsParaGetArea);
		
		PVConfregist pvConfregist = (PVConfregist)bsParaGetArea.OUT.para.getPval();
		
		//Si el registro está cerrado, no lo permitimos
		if (LiteData.LT_EL_CONFREGIST_CERRADO.equals(pvConfregist.esta)) {
			notify(CoreNotify.INST_REGI_CERR);
		}
		
		//Si el registro está restringido por invitación, se debe indicar que la invitación está validada
		if (LiteData.LT_EL_CONFREGIST_INVITACION.equals(pvConfregist.esta)) {
			if (!area.IN.invi) {
				notify(CoreNotify.INST_REGI_INVI_NO);
			}
		}

		//Obtenemos la configuración de multiinstalación de la aplicación
		bsParaGetArea = new BsParaGetArea();
		bsParaGetArea.IN.tbla = ParaData.PARA_TBLA_APCF;
		bsParaGetArea.IN.clav = ParaData.PARA_ELEM_APCF_MLTI;
		bsParaGet.executeBS(bsParaGetArea);
		
		PVConfigmlti pvConfigmlti = (PVConfigmlti)bsParaGetArea.OUT.para.getPval();
		
		//Si no está permitida la multiinstalación, no se permite el registro con usuario existente
		if (LiteData.LT_EL_BOOL_NO.equals(pvConfigmlti.mlti)) {
			if (LiteData.LT_EL_REGTIPOUSU_EXISTE.equals(area.IN.numo)) {
				notify(CoreNotify.INST_REGI_MLTI_NO);
			}
		}
		
		//Generamos el código de instalación
		BsInstCodiGeneArea bsInstCodiGeneArea = new BsInstCodiGeneArea();
		bsInstCodiGene.executeBS(bsInstCodiGeneArea);
		
		String codi = bsInstCodiGeneArea.OUT.codi;
		
		//Obtenemos la configuración de la descripción de instalación
		bsParaGetArea = new BsParaGetArea();
		bsParaGetArea.IN.tbla = ParaData.PARA_TBLA_DYNF;
		bsParaGetArea.IN.clav = ParaData.PARA_ELEM_DYNF_RIDE;
		bsParaGet.executeBS(bsParaGetArea);
		
		PVDynamicfld pvDynamicfld = (PVDynamicfld)bsParaGetArea.OUT.para.getPval();
		
		String desc = "";

		//Si está configurada para ser capturada, validamos que esté informada. En caso contrario, generamos la descripción
		if (LiteData.LT_EL_BOOL_SI.equals(pvDynamicfld.show)) {
			validateInputField(area.IN.desc, Inst.DESC);
			desc = area.IN.desc;
		} else {
			desc = "Instalación " + codi;
		}
		
		//Validar que no exista la descripción
		BsInstGetdArea bsInstGetdArea = new BsInstGetdArea();
		bsInstGetdArea.IN.desc = desc;
		bsInstGetd.executeBS(bsInstGetdArea);
		
		if (bsInstGetdArea.OUT.inst != null) {
			notify(CoreNotify.INST_REGI_DESC_DP);
		}
		
		//Generamos la instalación.
		Inst inst = new Inst();
		inst.setDesc(desc);
		inst.setEsta(LiteData.LT_EL_INSTESTA_ACTIVA);
		inst.setFeal(SESSION.get().feop);
		inst.setFeul(SESSION.get().feop);
		inst.setTipo(LiteData.LT_EL_INSTTIPO_NORMAL);
		inst.setCodi(codi);
		
		//Grabamos la instalación
		BsInstSaveArea bsInstSaveArea = new BsInstSaveArea();
		bsInstSaveArea.IN.inst = inst;
		bsInstSave.executeBS(bsInstSaveArea);
		
		inst = bsInstSaveArea.OUT.inst;
		
		Usua usua = new Usua();
		
		//Si el tipo de registro indica que el usuario es nuevo, lo registramos (el registro ya lo asocia a la instalación)
		if (LiteData.LT_EL_REGTIPOUSU_NUEVO.equals(area.IN.numo)) {
			//Registramos el usuario
			BsUsuaRegiArea bsUsuaRegiArea = new BsUsuaRegiArea();
			bsUsuaRegiArea.IN.inst = inst.getIden();
			bsUsuaRegiArea.IN.invi = area.IN.invi;
			bsUsuaRegiArea.IN.codi = null;
			bsUsuaRegiArea.IN.numo = area.IN.numo;
			bsUsuaRegiArea.IN.iden = area.IN.usua;
			bsUsuaRegiArea.IN.pass = area.IN.pass;
			bsUsuaRegiArea.IN.cpas = area.IN.cpas;
			bsUsuaRegiArea.IN.mail = area.IN.mail;
			bsUsuaRegiArea.IN.perf = LiteData.LT_EL_USUAPERF_ADM;
			bsUsuaRegi.executeBS(bsUsuaRegiArea);
			
			usua = bsUsuaRegiArea.OUT.usua;
		
		//Si el tipo de registro indica que el usuario existe, lo validamos y lo asociamos a la instalación
		} else if (LiteData.LT_EL_REGTIPOUSU_EXISTE.equals(area.IN.numo)) {
			//Validamos credenciales del usuario
			BsUsuaValiArea bsUsuaValiArea = new BsUsuaValiArea();
			bsUsuaValiArea.IN.iden = area.IN.usua;
			bsUsuaValiArea.IN.pass = area.IN.pass;
			bsUsuaValiArea.IN.mail = area.IN.mail;
			bsUsuaVali.executeBS(bsUsuaValiArea);
			
			usua = bsUsuaValiArea.OUT.usua;
			
			//Asociamos el usuario y la instalación
			BsRelaInstUsuaArea bsRelaInstUsuaArea = new BsRelaInstUsuaArea();
			bsRelaInstUsuaArea.IN.inst = inst.getIden();
			bsRelaInstUsuaArea.IN.usua = usua.getIden();
			bsRelaInstUsuaArea.IN.perf = LiteData.LT_EL_USUAPERF_ADM;
			bsRelaInstUsua.executeBS(bsRelaInstUsuaArea);

		}
		
		area.OUT.inst = inst;
		area.OUT.usua = usua;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInstRegiArea area = (BsInstRegiArea)a;
		
		//Validamos el tipo de usuario (nuevo, existente) en el registro)
		validateInputField(area.IN.numo, Inst.NUMO);
		
		//Si el usuario es nuevo, se validan mail, usuario, password y confirmación de password
		if (LiteData.LT_EL_REGTIPOUSU_NUEVO.equals(area.IN.numo)) {
			validateInputField(area.IN.mail, Usua.MAIL);
			validateInputField(area.IN.usua, Usua.IDEN);
			validateInputField(area.IN.pass, Usua.PASS);
			validateInputField(area.IN.cpas, Usua.CPAS);
		//Si el usuario es existente, validamos usuario y password
		} else if (LiteData.LT_EL_REGTIPOUSU_EXISTE.equals(area.IN.numo)) {
			validateInputField(area.IN.usua, Usua.IDEN);
			validateInputField(area.IN.pass, Usua.PASS);
		} else {
			notify(CoreNotify.INST_REGI_NUMO_ERRO);
		}

	}
}