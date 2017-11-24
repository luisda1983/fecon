package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Para;
import es.ldrsoftware.core.fwk.entity.ParaDAO;

@Component
public class BsParaSave extends BaseBS {

	@Autowired
	ParaDAO paraDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsParaSaveArea area = (BsParaSaveArea)a;
		area.IN.para.setFemo(SESSION.get().feop);
		area.IN.para.setHomo(SESSION.get().hoop);
		area.IN.para.setUsmo(SESSION.get().usua);
		
		Para para = paraDao.save(area.IN.para);
			
		area.OUT.para = para;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsParaSaveArea area = (BsParaSaveArea)a;
		//TODO: validaciones de entrada
	}
}
