//Normalizado
package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.data.PVParser;
import es.ldrsoftware.core.fwk.entity.Para;
import es.ldrsoftware.core.fwk.entity.ParaDAO;

@Component
public class BsParaGet extends BaseBS {

	@Autowired
	ParaDAO paraDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsParaGetArea area = (BsParaGetArea)a;
		
		Para para = paraDao.getByTblaClav(area.IN.tbla, area.IN.clav);
		
		validateDtoNotFound(para, LiteData.LT_EL_DTO_PARA, Para.key(area.IN.tbla, area.IN.clav));
		
		PVParser.parse(para);
		
		area.OUT.para = para;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsParaGetArea area = (BsParaGetArea)a;

		validateInputField(area.IN.tbla, Para.TBLA);
		validateInputField(area.IN.clav, Para.CLAV);
		
	}
}
