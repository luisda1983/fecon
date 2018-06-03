package es.ldrsoftware.core.sts.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.sts.entity.Stst;

@Component
public class BsStstRegi extends BaseBS {

	@Autowired
	private BsStstSave bsStstSave;

	protected void execute(BaseBSArea a) throws Exception {
		BsStstRegiArea area = (BsStstRegiArea)a;
		
		Stst stst = new Stst();
		stst.setCtrl(area.IN.ctrl);
		stst.setInst(SESSION.get().inst);
		String usua = SESSION.get().usua;
		if (usua == null || "".equals(usua)) { usua = "OFF"; }
		stst.setUsua(usua);
		stst.setFeej(SESSION.get().feop);
		stst.setHoej(SESSION.get().hoop);
		stst.setTiej(new Long(area.IN.fiej - area.IN.inej).intValue());
		stst.setReej(area.IN.reej);
		stst.setNotf(area.IN.notf);
		
		BsStstSaveArea bsStstSaveArea = new BsStstSaveArea();
		bsStstSaveArea.IN.stst = stst;
		bsStstSave.executeBS(bsStstSaveArea);

		area.OUT.stst = bsStstSaveArea.OUT.stst;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsStstRegiArea area = (BsStstRegiArea)a;
		
		validateStringRequired(area.IN.ctrl, CoreNotify.STST_REGI_CTRL_RQRD);
		
		validateIntRequired(area.IN.inej, CoreNotify.STST_REGI_INEJ_RQRD);
		validateIntRequired(area.IN.fiej, CoreNotify.STST_REGI_FIEJ_RQRD);

		if (area.IN.reej == null ) { area.IN.reej = ""; }
		if (area.IN.notf == null ) { area.IN.notf = ""; }
		
	}

}
