package es.ldrsoftware.core.btc.bs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.btc.entity.Btch;
import es.ldrsoftware.core.btc.entity.BtchDAO;
import es.ldrsoftware.core.fwk.data.LiteData;

@Component
public class BsBtchList extends BaseBS {

	@Autowired
	BtchDAO btchDao;
	
	public final static String BTCH_LIST_AUTO = "LT-01";
	public final static String BTCH_LIST_FULL = "LT-02";
	
	protected void execute(BaseBSArea a) throws Exception {
		BsBtchListArea area = (BsBtchListArea)a;
		
		List<Btch> btchList = new ArrayList<Btch>();
		
		switch(area.IN.tipo) {
		case BTCH_LIST_AUTO:
			 btchList = btchDao.getListByEstaAuto(LiteData.LT_EL_BTCHESTA_ACTIVO, LiteData.LT_EL_BOOL_SI);
			 break;
		case BTCH_LIST_FULL:
			 btchList = btchDao.getList();
		}
		
		area.OUT.btchList = btchList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsBtchListArea area = (BsBtchListArea)a;

		validateInputField(area.IN.tipo, Btch.TIPO_LIST);
		
		switch(area.IN.tipo) {
		case BTCH_LIST_AUTO:
			 break;
		case BTCH_LIST_FULL:
			 break;
		default:
			 notify(CoreNotify.BTCH_LIST_TIPO_ERRO);
			 break;
		}
	}

}
