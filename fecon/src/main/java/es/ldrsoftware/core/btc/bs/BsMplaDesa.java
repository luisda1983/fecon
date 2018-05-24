package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.btc.entity.Mpla;
import es.ldrsoftware.core.fwk.data.LiteData;

@Component
public class BsMplaDesa extends BaseBS {

	@Autowired
	BsMplaGetk bsMplaGetk;
	
	@Autowired
	BsMplaSave bsMplaSave;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsMplaDesaArea area = (BsMplaDesaArea)a;
		
		//Obtenemos el maestro de planificación
		BsMplaGetkArea bsMplaGetkArea = new BsMplaGetkArea();
		bsMplaGetkArea.IN.hora = area.IN.hora;
		bsMplaGetk.executeBS(bsMplaGetkArea);
		
		Mpla mpla = bsMplaGetkArea.OUT.mpla;
		
		validateDtoRequired(mpla, CoreNotify.MPLA_DESA_MPLA_NF);
		
		//Validamos que el maestro se encuentre activado
		if (LiteData.LT_EL_MPLAESTA_DESACTIVADO.equals(mpla.getEsta())) {
			notify(CoreNotify.MPLA_DESA_ESTA_ERRO);
		}
		
		//Desactivamos el maestro de planificación
		mpla.setEsta(LiteData.LT_EL_MPLAESTA_DESACTIVADO);
		
		//Grabamos el maestro de planificación
		BsMplaSaveArea bsMplaSaveArea = new BsMplaSaveArea();
		bsMplaSaveArea.IN.mpla = mpla;
		bsMplaSave.executeBS(bsMplaSaveArea);
		
		area.OUT.mpla = bsMplaSaveArea.OUT.mpla;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsMplaDesaArea area = (BsMplaDesaArea)a;
	}

}
