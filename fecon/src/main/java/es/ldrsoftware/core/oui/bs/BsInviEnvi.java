package es.ldrsoftware.core.oui.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.arq.util.DateTimeUtil;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Invi;

@Component
public class BsInviEnvi extends BaseBS {
	
	@Autowired
	BsInviSave bsInviSave;

	@Autowired
	BsInviList bsInviList;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInviEnviArea area = (BsInviEnviArea)a;

		//Verificamos que no exista ninguna invitación enviada con el mismo email
		BsInviListArea bsInviListArea = new BsInviListArea();
		bsInviListArea.IN.mail = area.IN.mail;
		bsInviListArea.IN.esta = LiteData.LT_EL_INVIESTA_ENVIADA;
		bsInviListArea.IN.tipo = area.IN.tipo;
		
		if (LiteData.LT_EL_INVITIPO_USUARIO.equals(area.IN.tipo)) {
			bsInviListArea.IN.inst = SESSION.get().inst;
		}
		
		bsInviList.executeBS(bsInviListArea);
		
		List<Invi> inviList = bsInviListArea.OUT.inviList;
		
		validateListEmpty(inviList, CoreNotify.INVI_ENVI_INVI_ENVI_EXIS);
		
		//Si la invitación a enviar es de tipo instalación, verificamos que no exista ninguna otra Solitada o Aceptada
		if (LiteData.LT_EL_INVITIPO_INSTALACION.equals(area.IN.tipo)) {
			bsInviListArea = new BsInviListArea();
			bsInviListArea.IN.mail = area.IN.mail;
			bsInviListArea.IN.esta = LiteData.LT_EL_INVIESTA_SOLICITADA;
			bsInviListArea.IN.tipo = area.IN.tipo;
			bsInviList.executeBS(bsInviListArea);
			inviList = bsInviListArea.OUT.inviList;
			
			validateListEmpty(inviList, CoreNotify.INVI_ENVI_INVI_SOLI_EXIS);
			
			bsInviListArea = new BsInviListArea();
			bsInviListArea.IN.mail = area.IN.mail;
			bsInviListArea.IN.esta = LiteData.LT_EL_INVIESTA_ACEPTADA;
			bsInviListArea.IN.tipo = area.IN.tipo;
			bsInviList.executeBS(bsInviListArea);
			inviList = bsInviListArea.OUT.inviList;
			
			validateListEmpty(inviList, CoreNotify.INVI_ENVI_INVI_ACEP_EXIS);
		}

		//Generamos la invitación
		Invi invi = new Invi();
		invi.setIden("INV" + DateTimeUtil.getNope());
		invi.setTipo(area.IN.tipo);
		invi.setEsta(LiteData.LT_EL_INVIESTA_ENVIADA);
		invi.setMail(area.IN.mail);
		invi.setInst(0);
		invi.setUsua("");
		invi.setFeal(SESSION.get().feop);
		invi.setHoal(SESSION.get().hoop);
		invi.setFemo(0);
		invi.setHomo(0);
		
		if (LiteData.LT_EL_INVITIPO_USUARIO.equals(area.IN.tipo)) {
			
			//Si es una invitación para unirse a una instalación, asignamos la instalación
			invi.setInst(SESSION.get().inst);
		}
		
		//Guardamos la invitación
		BsInviSaveArea bsInviSaveArea = new BsInviSaveArea();
		bsInviSaveArea.IN.invi = invi;
		bsInviSave.executeBS(bsInviSaveArea);
		
		area.OUT.invi = bsInviSaveArea.OUT.invi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInviEnviArea area = (BsInviEnviArea)a;

		//Validamos que el email esté informado
		validateStringRequired(area.IN.mail, CoreNotify.INVI_ENVI_MAIL_RQRD);
		
		//Validamos que el tipo de invitación esté informado y dentro del dominio permitido
		validateStringRequired(area.IN.tipo, CoreNotify.INVI_ENVI_TIPO_RQRD);
		validateStringDomain(CoreNotify.INVI_ENVI_TIPO_ERRO, area.IN.tipo, LiteData.LT_ST_INVITIPO);
		
	}
}