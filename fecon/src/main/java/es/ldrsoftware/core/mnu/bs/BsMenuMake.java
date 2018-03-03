package es.ldrsoftware.core.mnu.bs;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.mnu.entity.Ctmn;
import es.ldrsoftware.core.mnu.entity.Dtmn;

@Component
public class BsMenuMake extends BaseBS {

	@Autowired
	BsCtmnList bsCtmnList;

	@Autowired
	BsDtmnList bsDtmnList;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsMenuMakeArea area = (BsMenuMakeArea)a;

		//Obtenemos el listado de categorías de menú, según el perfil cargado en la Sesión
		BsCtmnListArea bsCtmnListArea = new BsCtmnListArea();
		bsCtmnListArea.IN.tipo = BsCtmnList.CTMN_LIST_PERF_ACTI;
		bsCtmnListArea.IN.perf = SESSION.get().perf;
		bsCtmnListArea.IN.acti = LiteData.LT_EL_BOOL_SI;
		bsCtmnList.executeBS(bsCtmnListArea);
		
		List<Ctmn> ctmnList = bsCtmnListArea.OUT.ctmnList;
		
		ListIterator<Ctmn> ctmnIt = ctmnList.listIterator();
		
		while (ctmnIt.hasNext()) {
			Ctmn ctmn = ctmnIt.next();
		
			//Por cada categoría de menú, obtenemos la lista de detalle de menú y la añadimos
			BsDtmnListArea bsDtmnListArea = new BsDtmnListArea();
			bsDtmnListArea.IN.tipo = BsDtmnList.DTMN_LIST_CTMN_ACTI;
			bsDtmnListArea.IN.ctmn = ctmn.getIden();
			bsDtmnListArea.IN.acti = LiteData.LT_EL_BOOL_SI;
			bsDtmnList.executeBS(bsDtmnListArea);
			
			List<Dtmn> dtmnList = bsDtmnListArea.OUT.dtmnList;
			ctmn.dtmnList = dtmnList;
		}
		
		area.OUT.ctmnList = ctmnList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsMenuMakeArea area = (BsMenuMakeArea)a;		
	}

}
