package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Inst;
import es.ldrsoftware.core.oui.entity.Invi;
import es.ldrsoftware.core.oui.entity.Usua;

@Component
public class BsInviProc extends BaseBS {

	@Autowired
	BsInstRegi bsInstRegi;
	
	@Autowired
	BsInviGetk bsInviGetk;

	@Autowired
	BsInviSave bsInviSave;
	
	@Autowired
	BsUsuaRegi bsUsuaRegi;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsInviProcArea area = (BsInviProcArea)a;
	
		//Consultamos la invitación
		BsInviGetkArea bsInviGetkArea = new BsInviGetkArea();
		bsInviGetkArea.IN.iden = area.IN.iden;
		bsInviGetk.executeBS(bsInviGetkArea);
		
		Invi invi = bsInviGetkArea.OUT.invi;
		
		validateDtoNotFound(invi, LiteData.LT_EL_DTO_INVI, Invi.key(area.IN.iden));
		
		//Si la invitación es de tipo instalación, registramos la instalación
		if (LiteData.LT_EL_INVITIPO_INSTALACION.equals(invi.getTipo())) {
			BsInstRegiArea bsInstRegiArea = new BsInstRegiArea();
			bsInstRegiArea.IN.invi = true;
			bsInstRegiArea.IN.desc = area.IN.desc;
			bsInstRegiArea.IN.numo = area.IN.numo;
			bsInstRegiArea.IN.mail = area.IN.mail;
			bsInstRegiArea.IN.usua = area.IN.usua;
			bsInstRegiArea.IN.pass = area.IN.pass;
			bsInstRegiArea.IN.cpas = area.IN.cpas;
			bsInstRegi.executeBS(bsInstRegiArea);
			
			Inst inst = bsInstRegiArea.OUT.inst;
			Usua usua = bsInstRegiArea.OUT.usua;
			
			//Finalizamos la invitación
			invi.setEsta(LiteData.LT_EL_INVIESTA_FINALIZADA);
			invi.setFemo(SESSION.get().feop);
			invi.setHomo(SESSION.get().hoop);
			invi.setInst(inst.getIden());
			invi.setUsua(usua.getIden());

			area.OUT.inst = inst;
			area.OUT.usua = usua;
		
		//Si la invitación es de tipo usuario, registramos el usuario
		} else if (LiteData.LT_EL_INVITIPO_USUARIO.equals(invi.getTipo())) {
			
			BsUsuaRegiArea bsUsuaRegiArea = new BsUsuaRegiArea();
			bsUsuaRegiArea.IN.invi = true;
			bsUsuaRegiArea.IN.inst = invi.getInst();
			bsUsuaRegiArea.IN.codi = null;
			bsUsuaRegiArea.IN.numo = area.IN.numo;
			bsUsuaRegiArea.IN.iden = area.IN.usua;
			bsUsuaRegiArea.IN.pass = area.IN.pass;
			bsUsuaRegiArea.IN.cpas = area.IN.cpas;
			bsUsuaRegiArea.IN.mail = area.IN.mail;
			bsUsuaRegiArea.IN.perf = "USR";
			bsUsuaRegi.executeBS(bsUsuaRegiArea);
			
			Usua usua = bsUsuaRegiArea.OUT.usua;
			
			//Finalizamos la invitación
			invi.setEsta(LiteData.LT_EL_INVIESTA_FINALIZADA);
			invi.setFemo(SESSION.get().feop);
			invi.setHomo(SESSION.get().hoop);
			invi.setUsua(usua.getIden());

			area.OUT.usua = usua;
		} 

		//Guardamos la invitación
		BsInviSaveArea bsInviSaveArea = new BsInviSaveArea();
		bsInviSaveArea.IN.invi = invi;
		bsInviSave.executeBS(bsInviSaveArea);

		invi = bsInviSaveArea.OUT.invi;
		
		area.OUT.invi = invi;
		
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInviProcArea area = (BsInviProcArea)a;

		//Validamos que el identificador de la invitación esté informado
		validateInputField(area.IN.iden, Invi.IDEN);

	}
}