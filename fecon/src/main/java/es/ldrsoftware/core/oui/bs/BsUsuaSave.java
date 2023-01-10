//Normalizado
package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Usua;
import es.ldrsoftware.core.oui.entity.UsuaDAO;

@Component
public class BsUsuaSave extends BaseBS {

	@Autowired
	UsuaDAO usuaDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaSaveArea area = (BsUsuaSaveArea)a;
		
		//Guardamos el usuario
		Usua usua = usuaDao.save(area.IN.usua);
		
		area.OUT.usua = usua;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsUsuaSaveArea area = (BsUsuaSaveArea)a;
		
		//Validamos que el usuario está informado
		Usua usua = (Usua)validateDto(area.IN.usua, LiteData.LT_EL_DTO_USUA); 
	}
}