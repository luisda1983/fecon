package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.bs.BsRelaSave;
import es.ldrsoftware.core.fwk.bs.BsRelaSaveArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Inst;
import es.ldrsoftware.core.oui.entity.Invi;
import es.ldrsoftware.core.oui.entity.Usua;

@Component
public class BsInviProc extends BaseBS {

	@Autowired
	private BsInstRegi bsInstRegi;
	
	@Autowired
	private BsInstGetk bsInstGetk;
	
	@Autowired
	private BsInviGetk  bsInviGet;

	@Autowired
	private BsInviSave bsInviSave;
	
	@Autowired
	private BsUsuaRegi bsUsuaRegi;
	
	@Autowired
	private BsRelaSave bsRelaSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInviProcArea area = (BsInviProcArea)a;
	
		//Consultamos la invitación
		BsInviGetkArea bsInviGetArea = new BsInviGetkArea();
		bsInviGetArea.IN.iden = area.IN.iden;
		bsInviGet.executeBS(bsInviGetArea);
		
		Invi invi = bsInviGetArea.OUT.invi;
		
		if (invi == null) {
			notify(CoreNotify.INVI_PROC_INVI_NF);
		}
		
		if (LiteData.LT_EL_INVITIPO_INSTALACION.equals(invi.getTipo())) {
			BsInstRegiArea bsInstRegiArea = new BsInstRegiArea();
			bsInstRegiArea.IN.invi = area.IN.iden;
			bsInstRegiArea.IN.usua = area.IN.usua;
			bsInstRegiArea.IN.pass = area.IN.pass;
			bsInstRegiArea.IN.cpas = area.IN.cpas;
			bsInstRegiArea.IN.mail = area.IN.mail;
			bsInstRegi.executeBS(bsInstRegiArea);
			
			//area.OUT.invi = bsInstRegiArea.OUT.invi;
		} else if (LiteData.LT_EL_INVITIPO_USUARIO.equals(invi.getTipo())) {
			
			//Obtenemos la instalación para confirmar que exista y esté en vigor
			BsInstGetkArea bsInstGetkArea = new BsInstGetkArea();
			bsInstGetkArea.IN.iden = invi.getInst();
			bsInstGetk.executeBS(bsInstGetkArea);
			
			Inst inst = bsInstGetkArea.OUT.inst;
			
			validateDto(inst, CoreNotify.INVI_PROC_INST_NF);
			
			if (!LiteData.LT_EL_INSTESTA_ACTIVA.equals(inst.getEsta())) {
				notify(CoreNotify.INVI_PROC_INST_ACTI_NO);
			}
			
			//FIXME: validación máximo de usuarios
			
			//Registramos el usuario
			BsUsuaRegiArea bsUsuaRegiArea = new BsUsuaRegiArea();
			bsUsuaRegiArea.IN.iden = area.IN.usua;
			bsUsuaRegiArea.IN.pass = area.IN.pass;
			bsUsuaRegiArea.IN.cpas = area.IN.cpas;
			bsUsuaRegiArea.IN.mail = area.IN.mail;
			bsUsuaRegiArea.IN.perf = "USR";
			bsUsuaRegi.executeBS(bsUsuaRegiArea);
			
			Usua usua = bsUsuaRegiArea.OUT.usua;
			
			//Asociamos el usuario y la instalación
			BsRelaSaveArea bsRelaSaveArea = new BsRelaSaveArea();
			bsRelaSaveArea.IN.rela = BsRelaSave.RELA_REGI_INST_USUA;
			bsRelaSaveArea.IN.cln1 = inst.getIden();
			bsRelaSaveArea.IN.clc2 = usua.getIden();
			bsRelaSave.executeBS(bsRelaSaveArea);

			//Finalizamos la invitación
			invi.setEsta(LiteData.LT_EL_INVIESTA_FINALIZADA);
			invi.setFemo(SESSION.get().feop);
			invi.setHomo(SESSION.get().hoop);
			invi.setInst(inst.getIden());
			invi.setUsua(usua.getIden());
				
			BsInviSaveArea bsInviSaveArea = new BsInviSaveArea();
			bsInviSaveArea.IN.invi = invi;
			bsInviSave.executeBS(bsInviSaveArea);

		} else {
			notify("NOIMPLEMEN");
		}
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInviProcArea area = (BsInviProcArea)a;
		
		validateStringRequired(area.IN.iden, CoreNotify.INVI_PROC_IDEN_RQRD);

	}
}