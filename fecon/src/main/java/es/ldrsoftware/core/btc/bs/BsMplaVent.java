package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.btc.entity.Mpla;
import es.ldrsoftware.core.btc.entity.MplaDAO;

@Component
public class BsMplaVent extends BaseBS {

	@Autowired
	MplaDAO mplaDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsMplaVentArea area = (BsMplaVentArea)a;
		
		//FIXME: no está restando horas.
		int vent = 0;
		
		//Obtenemos del maestro de planificación, la siguiente activa
		Mpla mpla = mplaDao.getGreaterByHora(area.IN.hora);
		
		//Si no existe, calculamos la ventana con lo que queda de día
		if (mpla == null) {
			vent = 2400 - area.IN.hora;
			//Le sumamos la ventana del inicio del dia
			mpla = mplaDao.getGreaterByHora(0);
			
			//Si no existe, estaría todo desactivado
			if (mpla == null) {
				notify(CoreNotify.MPLA_VENT_ACTI_ERRO);
			} else {
				vent = vent + mpla.getHora();
			}
		} else {
			//Si existe la ventana es la diferencia de horas
			vent = mpla.getHora() - area.IN.hora;
		}
		
		area.OUT.mpla = mpla;
		area.OUT.vent = vent;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsMplaVentArea area = (BsMplaVentArea)a;
	}

}
