package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.entity.Notf;

@Component
public class BsNotfEdit extends BaseBS {

	@Autowired
	public BsNotfGetk bsNotfGetk;

	@Autowired
	public BsNotfSave bsNotfSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsNotfEditArea area = (BsNotfEditArea)a;
	
		BsNotfGetkArea bsNotfGetkArea = new BsNotfGetkArea();
		bsNotfGetkArea.IN.iden = area.IN.iden;
		bsNotfGetk.executeBS(bsNotfGetkArea);
		
		Notf notf = bsNotfGetkArea.OUT.notf;
		
		validateDtoRequired(notf, CoreNotify.NOTF_EDIT_NOTF_NF);
		
		if (notf.getTipo().equals(area.IN.tipo) && notf.getDesc().equals(area.IN.desc)) {
			notify(CoreNotify.NOTF_EDIT_CHNG_NO);
		}
		
		notf.setTipo(area.IN.tipo);
		notf.setDesc(area.IN.desc);
		
		BsNotfSaveArea bsNotfSaveArea = new BsNotfSaveArea();
		bsNotfSaveArea.IN.notf = notf;
		bsNotfSave.executeBS(bsNotfSaveArea);
		
		area.OUT.notf = bsNotfSaveArea.OUT.notf;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsNotfEditArea area = (BsNotfEditArea)a;
		
		validateStringRequired(area.IN.iden, CoreNotify.NOTF_EDIT_IDEN_RQRD);		
	}
}