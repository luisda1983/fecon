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
import es.ldrsoftware.core.fwk.data.ParaData;
import es.ldrsoftware.core.fwk.entity.Rela;
import es.ldrsoftware.core.oui.entity.Inst;
import es.ldrsoftware.core.oui.entity.Usua;

@Component
public class BsUsuaRegi extends BaseBS {

	@Autowired
	BsUsuaGetk bsUsuaGet;

	@Autowired
	BsUsuaGetm bsUsuaGetm;
	
	@Autowired
	BsUsuaSave bsUsuaSave;

	@Autowired
	BsUsuaVali bsUsuaVali;
	
	@Autowired
	BsInstGetk bsInstGetk;

	@Autowired
	BsInstGetc bsInstGetc;
	
	@Autowired
	BsRelaInstUsua bsRelaInstUsua;
	
	@Autowired
	BsParaGet bsParaGet;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaRegiArea area = (BsUsuaRegiArea)a;

		//Obtenemos la configuración del registro en la aplicación
		BsParaGetArea bsParaGetArea = new BsParaGetArea();
		bsParaGetArea.IN.tbla = ParaData.PARA_TBLA_APCF;
		bsParaGetArea.IN.clav = ParaData.PARA_ELEM_APCF_CFRG;
		bsParaGet.executeBS(bsParaGetArea);
		
		PVConfregist pvConfregist = (PVConfregist)bsParaGetArea.OUT.para.getPval();
		
		//Si el registro está cerrado, no lo permitimos
		if (LiteData.LT_EL_CONFREGIST_CERRADO.equals(pvConfregist.esta)) {
			notify(CoreNotify.USUA_REGI_CERR);
		}
		
		//Si el registro está restringido por invitación, se debe indicar que la invitación está validada
		if (LiteData.LT_EL_CONFREGIST_INVITACION.equals(pvConfregist.esta)) {
			if (!area.IN.invi) {
				notify(CoreNotify.USUA_REGI_INVI_NO);
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
				notify(CoreNotify.USUA_REGI_MLTI_NO);
			}
		}

		//Obtenemos la instalación (por identificador o por codigo)
		Inst inst;
		
		if (area.IN.inst != 0) {
			BsInstGetkArea bsInstGetkArea = new BsInstGetkArea();
			bsInstGetkArea.IN.iden = area.IN.inst;
			bsInstGetk.executeBS(bsInstGetkArea);
			
			inst = bsInstGetkArea.OUT.inst;
			validateDtoNotFound(inst, LiteData.LT_EL_DTO_INST, Inst.key(area.IN.inst));
		} else {
			validateInputField(area.IN.codi, Inst.CODI);
			
			BsInstGetcArea bsInstGetcArea = new BsInstGetcArea();
			bsInstGetcArea.IN.codi = area.IN.codi;
			bsInstGetc.executeBS(bsInstGetcArea);
			
			inst = bsInstGetcArea.OUT.inst;
			validateDtoNotFound(inst, LiteData.LT_EL_DTO_INST, Inst.keyCodi(area.IN.codi));
		}
		
		Usua usua;
		
		//Si el tipo de registro indica que el usuario es nuevo, hacemos las operaciones propias del registro
		if (LiteData.LT_EL_REGTIPOUSU_NUEVO.equals(area.IN.numo)) {
			//Validamos si ya existe un usuario con el mismo identificador
			BsUsuaGetkArea bsUsuaGetkArea = new BsUsuaGetkArea();
			bsUsuaGetkArea.IN.iden = area.IN.iden;
			bsUsuaGet.executeBS(bsUsuaGetkArea);
		
			usua = bsUsuaGetkArea.OUT.usua;
		
			testData(usua, CoreNotify.USUA_REGI_IDEN_DP); 
		
			//Validamos si ya existe un usuario con el mismo email
			BsUsuaGetmArea bsUsuaGetmArea = new BsUsuaGetmArea();
			bsUsuaGetmArea.IN.mail = area.IN.mail;
			bsUsuaGetm.executeBS(bsUsuaGetmArea);
		
			usua = bsUsuaGetmArea.OUT.usua;
		
			testData(usua, CoreNotify.USUA_REGI_MAIL_DP);
		
			//Validamos que el password introducido coindide
			test(false, area.IN.pass, area.IN.cpas, CoreNotify.USUA_REGI_PASS_CPAS_ERRO);
		
			//No se permite el registro de usuarios con perfil APM. Aunque queda cubierto por la siguiente validación,
			//la hacemos a parte, para controlarlo
			if (area.IN.perf.equals(LiteData.LT_EL_USUAPERF_APM)) { 
				notify(CoreNotify.USUA_REGI_PERF_APM); 
			}
		
			//Validamos el perfil no permitido.
			test(true, area.IN.perf, LiteData.LT_EL_USUAPERF_APM, CoreNotify.USUA_REGI_PERF_ERRO);
		
			//Creamos el usuario
			usua = new Usua();
			usua.setIden(area.IN.iden);
			usua.setMail(area.IN.mail);
			usua.setPass(area.IN.pass);
			usua.setFeal(SESSION.get().feop);
			usua.setHoal(SESSION.get().hoop);
			usua.setFeul(SESSION.get().feop);
			usua.setHoul(SESSION.get().hoop);
			usua.setActi("S");
		
			//Guardamos el usuario en la BBDD
			BsUsuaSaveArea bsUsuaSaveArea = new BsUsuaSaveArea();
			bsUsuaSaveArea.IN.usua = usua;
			bsUsuaSave.executeBS(bsUsuaSaveArea);
		
			usua = bsUsuaSaveArea.OUT.usua;
		
		} else {
			//Validamos credenciales del usuario
			BsUsuaValiArea bsUsuaValiArea = new BsUsuaValiArea();
			bsUsuaValiArea.IN.iden = area.IN.iden;
			bsUsuaValiArea.IN.pass = area.IN.pass;
			bsUsuaVali.executeBS(bsUsuaValiArea);
			
			usua = bsUsuaValiArea.OUT.usua;
		}
		
		//Asociamos el usuario y la instalación
		BsRelaInstUsuaArea bsRelaInstUsuaArea = new BsRelaInstUsuaArea();
		bsRelaInstUsuaArea.IN.inst = inst.getIden();
		bsRelaInstUsuaArea.IN.usua = usua.getIden();
		bsRelaInstUsuaArea.IN.perf = area.IN.perf;
		bsRelaInstUsua.executeBS(bsRelaInstUsuaArea);
		
		area.OUT.usua = usua;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsUsuaRegiArea area = (BsUsuaRegiArea)a;

		//Validamos el tipo de usuario (nuevo, existente) en el registro)
		validateInputField(area.IN.numo, Inst.NUMO);
		
		//Si el usuario es nuevo, se validan mail, usuario, password y confirmación de password
		if (LiteData.LT_EL_REGTIPOUSU_NUEVO.equals(area.IN.numo)) {
			validateInputField(area.IN.mail, Usua.MAIL);
			validateInputField(area.IN.iden, Usua.IDEN);
			validateInputField(area.IN.pass, Usua.PASS);
			validateInputField(area.IN.cpas, Usua.CPAS);
			validateInputField(area.IN.perf, Rela.PERF);
		//Si el usuario es existente, validamos usuario y password
		} else if (LiteData.LT_EL_REGTIPOUSU_EXISTE.equals(area.IN.numo)) {
			validateInputField(area.IN.iden, Usua.IDEN);
			validateInputField(area.IN.pass, Usua.PASS);
			validateInputField(area.IN.perf, Rela.PERF);
		} else {
			notify(CoreNotify.USUA_REGI_NUMO_ERRO);
		}
		
	}
}