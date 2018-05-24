package es.ldrsoftware.core.btc.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Logp;
import es.ldrsoftware.core.btc.entity.LogpDAO;

@Component
public class BsLogpList extends BaseBS {

	@Autowired
	LogpDAO logpDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsLogpListArea area = (BsLogpListArea)a;
		
		//Obtenemos el Log solicitado
		List<Logp> logpList = logpDao.getListByTipoIdenFechHora(area.IN.tipo, area.IN.iden, area.IN.fech, area.IN.hora);
		
		area.OUT.logpList = logpList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsLogpListArea area = (BsLogpListArea)a;		
	}

}
