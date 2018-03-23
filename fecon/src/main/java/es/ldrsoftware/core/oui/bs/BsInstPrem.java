package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.arq.util.DateTimeData;
import es.ldrsoftware.core.arq.util.DateTimeUtil;
import es.ldrsoftware.core.fwk.bs.BsParaGet;
import es.ldrsoftware.core.fwk.bs.BsParaGetArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.data.PVFechperiod;
import es.ldrsoftware.core.fwk.data.ParaData;
import es.ldrsoftware.core.oui.entity.Inst;

@Component
public class BsInstPrem extends BaseBS {

	@Autowired
	BsInstGetk bsInstGetk;
	
	@Autowired
	BsInstSave bsInstSave;
	
	@Autowired
	BsParaGet bsParaGet;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInstPremArea area = (BsInstPremArea)a;

		//Obtenemos la instalación
		BsInstGetkArea bsInstGetkArea = new BsInstGetkArea();
		bsInstGetkArea.IN.iden = area.IN.iden;
		bsInstGetk.executeBS(bsInstGetkArea);
		
		Inst inst = bsInstGetkArea.OUT.inst;
		
		//Validamos que exista la instalación
		validateDtoRequired(inst, CoreNotify.INST_PREM_INST_NF);
		
		//Validamos que la instalación sea de tipo Normal
		validateStringEqual(inst.getTipo(), LiteData.LT_EL_INSTTIPO_NORMAL, CoreNotify.INST_PREM_INST_NORM_NO);
	
		//Recuperamos el parámetro de configuración de periodo de instalación Premium
		BsParaGetArea paraGetArea = new BsParaGetArea();
		paraGetArea.IN.tbla = ParaData.PARA_TBLA_FPER;
		paraGetArea.IN.clav = ParaData.PARA_ELEM_FPER_PREM;
		bsParaGet.executeBS(paraGetArea);

		DateTimeData dataIn = new DateTimeData();
		dataIn.fech = SESSION.get().feop;
		dataIn.hora = SESSION.get().hoop;

		DateTimeData dataOut = DateTimeUtil.fechPeriod(dataIn, (PVFechperiod)paraGetArea.OUT.para.getPval());
				
		//Establecemos la instalación cómo Premium
		inst.setTipo(LiteData.LT_EL_INSTTIPO_PREMIUM);
		//Establecemos la fecha de caducidad Premium
		inst.setFeca(dataOut.fech);
		
		//Guardamos la instalación
		BsInstSaveArea bsInstSaveArea = new BsInstSaveArea();
		bsInstSaveArea.IN.inst = inst;
		bsInstSave.executeBS(bsInstSaveArea);

		area.OUT.inst = bsInstSaveArea.OUT.inst;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInstPremArea area = (BsInstPremArea)a;

		//Se valida que el identificador de instalación esté informado
		validateIntRequired(area.IN.iden, CoreNotify.INST_PREM_IDEN_RQRD);
	}

}
