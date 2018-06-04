package es.ldrsoftware.core.fwk.bs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.entity.Ctrl;
import es.ldrsoftware.core.fwk.entity.CtrlDAO;

@Component
public class BsCtrlList extends BaseBS {

	@Autowired
	public CtrlDAO ctrlDao;
	
	public final static String CTRL_LIST_FULL = "LT-01";
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCtrlListArea area = (BsCtrlListArea)a;
		
		List<Ctrl> ctrlList = new ArrayList<Ctrl>();
		
		switch(area.IN.tipo) {
		case CTRL_LIST_FULL:
			 ctrlList = ctrlDao.getList(CtrlDAO.ORDER_IDEN);
			 break;
		}
				
		area.OUT.ctrlList = ctrlList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCtrlListArea area = (BsCtrlListArea)a;
		
		validateStringRequired(area.IN.tipo, CoreNotify.CTRL_LIST_TIPO_RQRD);
		
		switch(area.IN.tipo) {
		case CTRL_LIST_FULL:
		     break;
		default:
			notify(CoreNotify.CTRL_LIST_TIPO_ERRO);
		}
	}
}