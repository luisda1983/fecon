package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.bs.BsParaGet;
import es.ldrsoftware.core.fwk.bs.BsParaGetArea;
import es.ldrsoftware.core.fwk.bs.BsRelaSave;
import es.ldrsoftware.core.fwk.bs.BsRelaSaveArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.data.PVConfregist;
import es.ldrsoftware.core.fwk.data.ParaData;
import es.ldrsoftware.core.oui.entity.Inst;
import es.ldrsoftware.core.oui.entity.Invi;
import es.ldrsoftware.core.oui.entity.Usua;

@Component
public class BsInstRegi extends BaseBS {
	
	@Autowired
	public BsParaGet bsParaGet;
	
	@Autowired
	public BsInviVali bsInviVali;

	@Autowired
	public BsInviSave bsInviSave;
	
	@Autowired
	public BsInstSave bsInstSave;
	
	@Autowired
	public BsUsuaRegi bsUsuaRegi;

	@Autowired
	public BsRelaSave bsRelaSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInstRegiArea area = (BsInstRegiArea)a;
		
		BsParaGetArea bsParaGetArea = new BsParaGetArea();
		bsParaGetArea.IN.tbla = ParaData.PARA_TBLA_APCF;
		bsParaGetArea.IN.clav = ParaData.PARA_ELEM_APCF_CFRG;
		bsParaGet.executeBS(bsParaGetArea);
		
		PVConfregist pvConfregist = (PVConfregist)bsParaGetArea.OUT.para.getPval();
		
		//Variable para guardar la invitación (en caso de ser necesaria)
		Invi invi = null;
		
		if ("C".equals(pvConfregist.esta)) {
			notify(CoreNotify.INST_REGI_CERR);
		} else if ("I".equals(pvConfregist.esta)) {
			validateStringRequired(area.IN.invi, CoreNotify.INST_REGI_INVI_RQRD);
			
			BsInviValiArea bsInviValiArea = new BsInviValiArea();
			bsInviValiArea.IN.iden = area.IN.invi;
			bsInviVali.executeBS(bsInviValiArea);
			
			invi = bsInviValiArea.OUT.invi;
		}
		
		Inst inst = new Inst();
		//TODO: La descripción dependerá de si se toma por pantalla o no. Formar una descripción en caso de no ser campo de entrada.
		inst.setDesc("Instalación ");
		inst.setEsta("A");
		inst.setFeal(SESSION.get().feop);
		inst.setFeul(SESSION.get().feop);
		inst.setTipo("N");
		
		BsInstSaveArea bsInstSaveArea = new BsInstSaveArea();
		bsInstSaveArea.IN.inst = inst;
		bsInstSave.executeBS(bsInstSaveArea);
		
		inst = bsInstSaveArea.OUT.inst;
		
		//Registramos el usuario
		BsUsuaRegiArea bsUsuaRegiArea = new BsUsuaRegiArea();
		bsUsuaRegiArea.IN.iden = area.IN.usua;
		bsUsuaRegiArea.IN.pass = area.IN.pass;
		bsUsuaRegiArea.IN.cpas = area.IN.cpas;
		bsUsuaRegiArea.IN.mail = area.IN.mail;
		bsUsuaRegiArea.IN.perf = "ADM";
		bsUsuaRegi.executeBS(bsUsuaRegiArea);
		
		Usua usua = bsUsuaRegiArea.OUT.usua;
		
		//Asociamos el usuario y la instalación
		BsRelaSaveArea bsRelaSaveArea = new BsRelaSaveArea();
		bsRelaSaveArea.IN.rela = BsRelaSave.RELA_REGI_INST_USUA;
		bsRelaSaveArea.IN.cln1 = inst.getIden();
		bsRelaSaveArea.IN.clc2 = usua.getIden();
		bsRelaSave.executeBS(bsRelaSaveArea);

		//En caso de tener invitación, la finalizamos
		if (invi != null) {
			invi.setEsta(LiteData.LT_EL_INVIESTA_FINALIZADA);
			invi.setFemo(SESSION.get().feop);
			invi.setHomo(SESSION.get().hoop);
			invi.setInst(inst.getIden());
			invi.setUsua(usua.getIden());
			
			BsInviSaveArea bsInviSaveArea = new BsInviSaveArea();
			bsInviSaveArea.IN.invi = invi;
			bsInviSave.executeBS(bsInviSaveArea);
		}
		
		area.OUT.inst = inst;
		
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInstRegiArea area = (BsInstRegiArea)a;
		
		validateStringRequired(area.IN.mail, CoreNotify.INST_REGI_MAIL_RQRD);
		validateStringRequired(area.IN.usua, CoreNotify.INST_REGI_USUA_RQRD);
		validateStringRequired(area.IN.pass, CoreNotify.INST_REGI_PASS_RQRD);
		validateStringRequired(area.IN.cpas, CoreNotify.INST_REGI_CPAS_RQRD);
		
	}
}