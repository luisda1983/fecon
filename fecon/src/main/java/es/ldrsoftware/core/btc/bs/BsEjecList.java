package es.ldrsoftware.core.btc.bs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.btc.entity.Ejec;
import es.ldrsoftware.core.btc.entity.EjecDAO;

@Component
public class BsEjecList extends BaseBS {

	@Autowired
	EjecDAO ejecDao;
	
	public final static String EJEC_LIST_EJEC = "LT-01";
	public final static String EJEC_LIST_FECH = "LT-02";
	public final static String EJEC_LIST_FEPL_HOPL = "LT-03";
	
	protected void execute(BaseBSArea a) throws Exception {
		BsEjecListArea area = (BsEjecListArea)a;
		
		List<Ejec> ejecList = new ArrayList<Ejec>();
		
		switch(area.IN.tipo) {
		case EJEC_LIST_EJEC:
			 ejecList = ejecDao.getListPendingExecution(area.IN.fech, area.IN.hora);
			 break;
		case EJEC_LIST_FECH:
			 ejecList = ejecDao.getListByFech(area.IN.fech);
			 break;
		case EJEC_LIST_FEPL_HOPL:
			 ejecList = ejecDao.getListByFeplHopl(area.IN.fech, area.IN.hora);
			 break;
		}
		area.OUT.ejecList = ejecList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsEjecListArea area = (BsEjecListArea)a;

		validateInputField(area.IN.tipo, Ejec.TIPO_LIST);
		
		switch(area.IN.tipo) {
		case EJEC_LIST_EJEC:
		case EJEC_LIST_FECH:
		case EJEC_LIST_FEPL_HOPL:
			 validateInputField(area.IN.fech, Ejec.FECH); 
			 break;
		default:
			 notify(CoreNotify.EJEC_LIST_TIPO_ERRO);
			 break;
		}
	}

}
