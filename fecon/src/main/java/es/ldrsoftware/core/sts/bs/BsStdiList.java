package es.ldrsoftware.core.sts.bs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.sts.entity.Stdi;
import es.ldrsoftware.core.sts.entity.StdiDao;

@Component
public class BsStdiList extends BaseBS {

	@Autowired
	StdiDao stdiDao;
	
	public final static String STDI_LIST_FECH      = "LT-01";
	public final static String STDI_LIST_BTWN_FECH = "LT-02";
	
	protected void execute(BaseBSArea a) throws Exception {
		BsStdiListArea area = (BsStdiListArea)a;
		
		List<Stdi> stdiList = new ArrayList<Stdi>();
		
		switch(area.IN.tipo) {
		case STDI_LIST_FECH:
			 stdiList = stdiDao.getListByFech(StdiDao.ORDER_CTRL, area.IN.fech);
			 break;
		case STDI_LIST_BTWN_FECH:
			 stdiList = stdiDao.getListBetweenFech(StdiDao.ORDER_CTRL_FECH, area.IN.fein, area.IN.fefi);
			 break;
		}
		area.OUT.stdiList = stdiList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsStdiListArea area = (BsStdiListArea)a;

		validateStringRequired(area.IN.tipo, CoreNotify.STDI_LIST_TIPO_RQRD);
		
		switch(area.IN.tipo) {
		case STDI_LIST_FECH:
			 validateIntRequired(area.IN.fech, CoreNotify.STDI_LIST_FECH_RQRD);
			 break;
		case STDI_LIST_BTWN_FECH:
			 validateIntRequired(area.IN.fein, CoreNotify.STDI_LIST_FEIN_RQRD);
			 validateIntRequired(area.IN.fefi, CoreNotify.STDI_LIST_FEFI_RQRD);
			 break;
		default:
			 notify(CoreNotify.STDI_LIST_TIPO_ERRO);
			 break;
		}
	}

}
