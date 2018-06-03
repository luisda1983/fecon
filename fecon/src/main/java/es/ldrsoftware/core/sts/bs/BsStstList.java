package es.ldrsoftware.core.sts.bs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.sts.entity.Stst;
import es.ldrsoftware.core.sts.entity.StstDao;

@Component
public class BsStstList extends BaseBS {

	@Autowired
	StstDao ststDao;
	
	public final static String STST_LIST_FECH_BTCH = "LT-01";
	public final static String STST_LIST_FECH      = "LT-02";
	
	protected void execute(BaseBSArea a) throws Exception {
		BsStstListArea area = (BsStstListArea)a;
		
		List<Stst> ststList = new ArrayList<Stst>();
		
		switch(area.IN.tipo) {
		case STST_LIST_FECH:
			 ststList = ststDao.getListByFeej(StstDao.ORDER_IDEN, area.IN.fech);
			 break;
		case STST_LIST_FECH_BTCH:
			 ststList = ststDao.getListByFeej(StstDao.ORDER_CTRL_IDEN, area.IN.fech);
			 break;
		}
		area.OUT.ststList = ststList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsStstListArea area = (BsStstListArea)a;

		validateStringRequired(area.IN.tipo, CoreNotify.STST_LIST_TIPO_RQRD);
		
		switch(area.IN.tipo) {
		case STST_LIST_FECH:
		case STST_LIST_FECH_BTCH:
			 validateIntRequired(area.IN.fech, CoreNotify.STST_LIST_FECH_RQRD);
			 break;
		default:
			 notify(CoreNotify.STST_LIST_TIPO_ERRO);
			 break;
		}
	}

}
