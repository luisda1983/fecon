package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Hcon;
import es.ldrsoftware.fecon.cnt.entity.HconDAO;
import es.ldrsoftware.fecon.data.LiteData;

@Component
public class BsHconSave extends BaseBS {
	
	@Autowired
	public HconDAO hconDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsHconSaveArea area = (BsHconSaveArea)a;
		
		Hcon hcon = hconDao.save(area.IN.hcon);
		
		area.OUT.hcon = hcon;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsHconSaveArea area = (BsHconSaveArea)a;
		
		Hcon hcon = (Hcon)validateDto(area.IN.hcon, LiteData.LT_EL_DTO_HCON); 
	}
}