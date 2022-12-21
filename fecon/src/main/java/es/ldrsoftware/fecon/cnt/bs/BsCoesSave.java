package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Coes;
import es.ldrsoftware.fecon.cnt.entity.CoesDAO;
import es.ldrsoftware.fecon.data.LiteData;

@Component
public class BsCoesSave extends BaseBS {
	
	@Autowired
	public CoesDAO coesDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCoesSaveArea area = (BsCoesSaveArea)a;
		
		Coes coes = coesDao.save(area.IN.coes);
		
		area.OUT.coes = coes;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCoesSaveArea area = (BsCoesSaveArea)a;
		
		Coes coes = (Coes)validateDto(area.IN.coes, LiteData.LT_EL_DTO_COES); 
	}
}