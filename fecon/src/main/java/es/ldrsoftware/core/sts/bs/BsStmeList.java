package es.ldrsoftware.core.sts.bs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.sts.entity.Stme;
import es.ldrsoftware.core.sts.entity.StmeDao;

@Component
public class BsStmeList extends BaseBS {

	@Autowired
	StmeDao stmeDao;
	
	public final static String STME_LIST_ANYO_MESS = "LT-01";
	
	protected void execute(BaseBSArea a) throws Exception {
		BsStmeListArea area = (BsStmeListArea)a;
		
		List<Stme> stmeList = new ArrayList<Stme>();
		
		switch(area.IN.tipo) {
		case STME_LIST_ANYO_MESS:
			 stmeList = stmeDao.getListByAnyoMess(StmeDao.ORDER_CTRL, area.IN.anyo, area.IN.mess);
			 break;
		}
		area.OUT.stmeList = stmeList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsStmeListArea area = (BsStmeListArea)a;

		validateStringRequired(area.IN.tipo, CoreNotify.STME_LIST_TIPO_RQRD);
		
		switch(area.IN.tipo) {
		case STME_LIST_ANYO_MESS:
			 validateIntRequired(area.IN.anyo, CoreNotify.STME_LIST_ANYO_RQRD);
			 validateIntRequired(area.IN.mess, CoreNotify.STME_LIST_MESS_RQRD);
			 break;
		default:
			 notify(CoreNotify.STME_LIST_TIPO_ERRO);
			 break;
		}
	}

}
