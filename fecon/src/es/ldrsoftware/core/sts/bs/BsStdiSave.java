package es.ldrsoftware.core.sts.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.sts.entity.Stdi;
import es.ldrsoftware.core.sts.entity.StdiDao;

@Component
public class BsStdiSave extends BaseBS {

	@Autowired
	private StdiDao stdiDao;

	protected void execute(BaseBSArea a) throws Exception {
		BsStdiSaveArea area = (BsStdiSaveArea)a;
		
		Stdi stdi = new Stdi();
		stdi.setFech(area.IN.fech);
		stdi.setCtrl(area.IN.ctrl);
		stdi.setTota(area.IN.tota);
		stdi.setTime(area.IN.time);
		stdi.setTima(area.IN.tima);
		stdi.setTimi(area.IN.timi);
		stdi.setNuer(area.IN.nuer);
		
		stdiDao.save(stdi);
		
		area.OUT.stdi = stdi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsStdiSaveArea area = (BsStdiSaveArea)a;
		
		if (area.IN.fech == 0) { notify(CoreNotify.STDI_SAVE_FECH_RQRD); }
		if (area.IN.ctrl == null || "".equals(area.IN.ctrl)) { notify(CoreNotify.STDI_SAVE_CTRL_RQRD); }
		
	}

}
