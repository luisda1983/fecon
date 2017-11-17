package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.oui.entity.Usua;

@Component
public class BsUsuaRegi extends BaseBS {

	@Autowired
	private BsUsuaGet bsUsuaGet;

	@Autowired
	private BsUsuaSave bsUsuaSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaRegiArea area = (BsUsuaRegiArea)a;
		
		//Obtenemos el usuario de la BBDD
		BsUsuaGetArea bsUsuaGetArea = new BsUsuaGetArea();
		bsUsuaGetArea.IN.iden = area.IN.iden;
		bsUsuaGet.executeBS(bsUsuaGetArea);
		
		Usua usua = bsUsuaGetArea.OUT.usua;
		
		//Validamos que no existe usuario con el mismo identificador
		if (usua != null) { notify(CoreNotify.USUA_REGI_IDEN_DP); }
		
		bsUsuaGetArea = new BsUsuaGetArea();
		bsUsuaGetArea.IN.mail = area.IN.mail;
		bsUsuaGet.executeBS(bsUsuaGetArea);
		
		usua = bsUsuaGetArea.OUT.usua;
		
		if (usua != null) { notify(CoreNotify.USUA_REGI_MAIL_DP); }
		
		//Validamos que el password introducido coindide
		if (!area.IN.pass.equals(area.IN.cpas)) { notify(CoreNotify.USUA_REGI_PASS_CPAS_ERRO); }

		//Validamos el perfil. No se permite el registro de usuarios con perfil APM
		//TODO: validar contra referencial
		if (area.IN.perf.equals("APM")) { notify(CoreNotify.USUA_REGI_PERF_APM); }
		
		if (!area.IN.perf.equals("ADM") && !area.IN.perf.equals("USR")) { notify(CoreNotify.USUA_REGI_PERF_ERRO); }
		
		//Creamos el usuario
		usua = new Usua();
		usua.setIden(area.IN.iden);
		usua.setMail(area.IN.mail);
		usua.setPass(area.IN.pass);
		usua.setPerf(area.IN.perf);
		usua.setFeal(SESSION.get().feop);
		usua.setHoal(SESSION.get().hoop);
		usua.setFeul(SESSION.get().feop);
		usua.setHoul(SESSION.get().hoop);
		usua.setActi("S");
		
		//Guardamos el usuario en la BBDD
		BsUsuaSaveArea bsUsuaSaveArea = new BsUsuaSaveArea();
		bsUsuaSaveArea.IN.usua = usua;
		bsUsuaSave.executeBS(bsUsuaSaveArea);
				
		area.OUT.usua = usua;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsUsuaRegiArea area = (BsUsuaRegiArea)a;
		
		validateStringRequired(area.IN.iden, CoreNotify.USUA_REGI_IDEN_RQRD);
		validateStringRequired(area.IN.pass, CoreNotify.USUA_REGI_PASS_RQRD);
		validateStringRequired(area.IN.cpas, CoreNotify.USUA_REGI_CPAS_RQRD);
		validateStringRequired(area.IN.mail, CoreNotify.USUA_REGI_MAIL_RQRD);
		validateStringRequired(area.IN.perf, CoreNotify.USUA_REGI_PERF_RQRD);
		
	}
}