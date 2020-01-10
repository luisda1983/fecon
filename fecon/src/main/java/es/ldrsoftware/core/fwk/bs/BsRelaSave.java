package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.entity.Rela;
import es.ldrsoftware.core.fwk.entity.RelaDAO;

@Component
public class BsRelaSave extends BaseBS {

	@Autowired
	public RelaDAO relaDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsRelaSaveArea area = (BsRelaSaveArea)a;
	
		Rela rela = relaDao.save(area.IN.rela);
		
		area.OUT.rela = rela;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsRelaSaveArea area = (BsRelaSaveArea)a;
		
		Rela rela = (Rela)validateDto(area.IN.rela, LiteData.LT_EL_DTO_RELA);
		rela.validate();
		
	}
}