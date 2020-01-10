package es.ldrsoftware.core.oui.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Invi;
import es.ldrsoftware.core.oui.entity.InviDAO;

@Component
public class BsInviList extends BaseBS {

	//TODO: implementar opciones de consulta explícitas.
	@Autowired
	InviDAO inviDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInviListArea area = (BsInviListArea)a;
	
		List<Invi> inviList;
		
		//Consultamos las invitaciones.
		if (data(area.IN.esta) && data(area.IN.mail)) {
			inviList = inviDao.getListByTipoEstaMail(area.IN.tipo, area.IN.esta, area.IN.mail);
		} else {
			if (data(area.IN.inst)) {
				if (data(area.IN.esta)) {
					inviList = inviDao.getListByInstTipoEsta(area.IN.inst, area.IN.tipo, area.IN.esta);
				} else {
					inviList = inviDao.getListByInstTipo(area.IN.inst, area.IN.tipo);
				}
			} else {
				if (data(area.IN.esta)) {
					inviList = inviDao.getListByTipoEsta(area.IN.tipo, area.IN.esta);
				} else {
					inviList = inviDao.getListByTipo(area.IN.tipo);
				}
			}
		}
		
		area.OUT.inviList = inviList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInviListArea area = (BsInviListArea)a;
		
		//Validamos que el tipo de invitación está informado
		validateInputField(area.IN.tipo, Invi.TIPO);

	}
}