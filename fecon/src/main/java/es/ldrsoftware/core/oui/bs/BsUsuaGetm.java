//Normalizado
package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Usua;
import es.ldrsoftware.core.oui.entity.UsuaDAO;

@Component
public class BsUsuaGetm extends BaseBS {

	@Autowired
	UsuaDAO usuaDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaGetmArea area = (BsUsuaGetmArea)a;
		
		//Obtenemos si existe un usuario con el email de entrada
		Usua usua = usuaDao.getByMail(area.IN.mail);
		
		area.OUT.usua = usua;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsUsuaGetmArea area = (BsUsuaGetmArea)a;
		
		//Validamos que el email está informado
		validateInputField(area.IN.mail, Usua.MAIL);
		
	}
}