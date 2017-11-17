package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.oui.entity.Usua;
import es.ldrsoftware.core.oui.entity.UsuaDAO;

@Component
public class BsUsuaGet extends BaseBS {

	@Autowired
	private UsuaDAO usuaDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaGetArea area = (BsUsuaGetArea)a;
		
		Usua usua = null;
		
		if (testString(area.IN.iden)) {
			usua = usuaDao.getByIden(area.IN.iden);
		} else if (testString(area.IN.mail)) {
			usua = usuaDao.getByMail(area.IN.mail);
		} else {
			notify(CoreNotify.USUA_GETU_ERRO);
		}
		
		area.OUT.usua = usua;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsUsuaGetArea area = (BsUsuaGetArea)a;
		
	}
}