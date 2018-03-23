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
public class BsUsuaGetk extends BaseBS {

	@Autowired
	UsuaDAO usuaDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaGetkArea area = (BsUsuaGetkArea)a;
		
		//Obtenemos el usuario
		Usua usua = usuaDao.getByIden(area.IN.iden);
		
		area.OUT.usua = usua;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsUsuaGetkArea area = (BsUsuaGetkArea)a;
		
		//Validamos que el identificador del usuario esté informado
		validateStringRequired(area.IN.iden, CoreNotify.USUA_GETK_IDEN_RQRD);
		
	}
}