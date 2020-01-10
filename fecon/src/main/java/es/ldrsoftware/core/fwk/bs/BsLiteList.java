package es.ldrsoftware.core.fwk.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Lite;
import es.ldrsoftware.core.fwk.entity.LiteDAO;

@Component
public class BsLiteList extends BaseBS {

	@Autowired
	LiteDAO liteDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsLiteListArea area = (BsLiteListArea)a;
		
		List<Lite> liteList = liteDao.getListByTbla(area.IN.tbla);
				
		area.OUT.liteList = liteList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsLiteListArea area = (BsLiteListArea)a;

		validateInputField(area.IN.tbla, Lite.TBLA);
	}
}
