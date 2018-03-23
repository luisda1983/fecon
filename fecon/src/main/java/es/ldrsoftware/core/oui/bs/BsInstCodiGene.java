//Normalizado
package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.arq.util.StringUtil;
import es.ldrsoftware.core.oui.entity.Inst;

@Component
public class BsInstCodiGene extends BaseBS {
	
	@Autowired
	BsInstGetc bsInstGetc;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInstCodiGeneArea area = (BsInstCodiGeneArea)a;
		
		int intentos = 0;
		int maxIntentos = 20;
		boolean generado = false;
		String codi = "";
		
		while (intentos < maxIntentos && !generado) {
			codi = StringUtil.random(10);
			
			BsInstGetcArea bsInstGetcArea = new BsInstGetcArea();
			bsInstGetcArea.IN.codi = codi;
			bsInstGetc.executeBS(bsInstGetcArea);
			
			Inst inst = bsInstGetcArea.OUT.inst;
			
			if (inst == null) {
				generado = true;
			}
			intentos++;
		}
		
		if (!generado) {
			notify(CoreNotify.INST_CODI_GENE_TOUT);
		}
		
		area.OUT.codi = codi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsInstCodiGeneArea area = (BsInstCodiGeneArea)a;
	}
}