package es.ldrsoftware.core.oui.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
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
		if (testString(area.IN.esta) && testString(area.IN.mail)) {
			inviList = inviDao.getListByTipoEstaMail(area.IN.tipo, area.IN.esta, area.IN.mail);
		} else {
			if (testInt(area.IN.inst)) {
				if (testString(area.IN.esta)) {
					inviList = inviDao.getListByInstTipoEsta(area.IN.inst, area.IN.tipo, area.IN.esta);
				} else {
					inviList = inviDao.getListByInstTipo(area.IN.inst, area.IN.tipo);
				}
			} else {
				if (testString(area.IN.esta)) {
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
		validateStringRequired(area.IN.tipo, CoreNotify.INVI_LIST_TIPO_RQRD);

	}
}