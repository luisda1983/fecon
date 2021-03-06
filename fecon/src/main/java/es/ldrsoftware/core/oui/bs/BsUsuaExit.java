package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.bs.BsSesiExit;
import es.ldrsoftware.core.fwk.bs.BsSesiExitArea;
import es.ldrsoftware.core.fwk.entity.Sesi;

@Component
public class BsUsuaExit extends BaseBS {

	@Autowired
	BsSesiExit bsSesiExit;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaExitArea area = (BsUsuaExitArea)a;
		
		//Cerramos la sesisón
		BsSesiExitArea bsSesiExitArea = new BsSesiExitArea();
		bsSesiExitArea.IN.iden = area.IN.sesi;
		bsSesiExit.executeBS(bsSesiExitArea);
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsUsuaExitArea area = (BsUsuaExitArea)a;

		//Validamos que el identificador de sesión está informado
		validateInputField(area.IN.sesi, Sesi.IDEN);
	}
}