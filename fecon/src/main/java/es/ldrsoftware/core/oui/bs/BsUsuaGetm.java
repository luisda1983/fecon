//Normalizado
package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.oui.entity.Usua;
import es.ldrsoftware.core.oui.entity.UsuaDAO;

@Component
public class BsUsuaGetm extends BaseBS {

	@Autowired
	private UsuaDAO usuaDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaGetmArea area = (BsUsuaGetmArea)a;
		
		Usua usua = usuaDao.getByMail(area.IN.mail);
		
		area.OUT.usua = usua;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsUsuaGetmArea area = (BsUsuaGetmArea)a;
		
		validateStringRequired(area.IN.mail, CoreNotify.USUA_GETM_MAIL_RQRD);
		
	}
}