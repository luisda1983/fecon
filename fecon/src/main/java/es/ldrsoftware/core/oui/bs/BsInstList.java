package es.ldrsoftware.core.oui.bs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.oui.entity.Inst;
import es.ldrsoftware.core.oui.entity.InstDAO;

@Component
public class BsInstList extends BaseBS {

	@Autowired
	InstDAO instDao;

	public final static String INST_LIST_FULL = "LT-01";
	public final static String INST_LIST_ESTA = "LT-02";
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInstListArea area = (BsInstListArea)a;

		List<Inst> instList = new ArrayList<Inst>();

		switch(area.IN.tipo) {
		case INST_LIST_FULL:
			//Obtenemos la lista de instalaciones
			instList = instDao.getList();
			break;
		case INST_LIST_ESTA:
			//Obtenemos la lista de intalaciones por estado
			instList = instDao.getListByEsta(area.IN.esta);
			break;
		}
		
		area.OUT.instList = instList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInstListArea area = (BsInstListArea)a;

		//Validamos el tipo de listado, y validamos según el tipo
		validateInputField(area.IN.tipo, Inst.TIPO_LIST);
		
		switch(area.IN.tipo) {
		case INST_LIST_FULL:
			break;
		case INST_LIST_ESTA:
			//Se valida que el estado esté informado
			validateInputField(area.IN.esta, Inst.ESTA);
			break;
		default:
			notify(CoreNotify.INST_LIST_TIPO_ERRO);
			break;
		}
		
	}

}
