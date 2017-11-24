package es.ldrsoftware.core.fwk.bs;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.PVParser;
import es.ldrsoftware.core.fwk.entity.Para;
import es.ldrsoftware.core.fwk.entity.ParaDAO;

@Component
public class BsParaTbla extends BaseBS {

	@Autowired
	ParaDAO paraDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsParaTblaArea area = (BsParaTblaArea)a;
		
		List<Para> paraList = paraDao.getListByTbla(area.IN.tbla);
		
		if (paraList == null) {
			notify(CoreNotify.PARA_NF);
		}
	
		ListIterator<Para> it = paraList.listIterator();
		
		while (it.hasNext()) {
			PVParser.parse(it.next());
		}
		
		area.OUT.paraList = paraList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsParaTblaArea area = (BsParaTblaArea)a;
		
		if (area.IN.tbla == null || "".equals(area.IN.tbla)) { notify(CoreNotify.PARA_GETP_TBLA_RQRD); }
		
	}
}
