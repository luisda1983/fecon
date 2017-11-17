package ldrsoftware.app.service.impl;

import java.util.ListIterator;

import ldrsoftware.app.comunication.response.BaseResponse;
import ldrsoftware.app.dao.IHMensDAO;
import ldrsoftware.app.dao.IHParmDAO;
import ldrsoftware.app.domain.HMens;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {

	@Autowired
	public IHMensDAO hmensDao;
	
	@Autowired
	public IHParmDAO parmDao;
	
	protected void mensaje(BaseResponse response, String mensaje) throws Exception {
		HMens mens = getMens(mensaje);
		switch(mens.getTipo()) {
		case "E":
			response.setError(mens);
			response.setStatus(BaseResponse.ST_ERROR);
			throw new Exception("APP");
		case "W":
			response.addWarning(mens);
			break;
		case "I":
			response.addInfo(mens);
			break;
		default:
			mens = new HMens();
			mens.setIden("DES0000003");
			mens.setDesc("Development error. Missing type of error. [" + mensaje + " - " + mens.getTipo() + "]");
			mens.setTipo("E");
			response.setError(mens);
			throw new Exception("APP");
		}
	}
	
	protected void mensaje(BaseResponse response, HMens mens) throws Exception {
		if (mens == null) {
			mens = new HMens();
			mens.setIden("DES0000001");
			mens.setDesc("Development error. Missing message.");
			mens.setTipo("E");
			throw new Exception("APP");
		}
		switch(mens.getTipo()) {
		case "E":
			response.setError(mens);
			throw new Exception("APP");
		case "W":
			response.addWarning(mens);
			break;
		case "I":
			response.addInfo(mens);
			break;
		default:
			mens = new HMens();
			mens.setIden("DES0000003");
			mens.setDesc("Development error. Missing type of error. [" + mens.getIden() + " - " + mens.getTipo() + "]");
			mens.setTipo("E");
			response.setError(mens);
			throw new Exception("APP");
		}
	}
	
	private HMens getMens(String mensaje) {
		HMens mens;
		
		if (mensaje == null) {
			mens = new HMens();
			mens.setIden("DES0000001");
			mens.setDesc("Development error. Missing message.");
			mens.setTipo("E");
			return mens;
		}
		mens = hmensDao.getByIden(mensaje);
		if (mens == null) {
			mens = new HMens();
			mens.setIden("DES0000002");
			mens.setDesc("Installation error. A Message haven't been created[" + mensaje + "].");
			mens.setTipo("E");
			return mens;
		}
		return mens;
	}
	
	protected void multiService(BaseResponse master, BaseResponse slave) {
		if (BaseResponse.ST_ERROR.equals(master.getStatus())) {
			return;
		}
		if (BaseResponse.ST_ERROR.equals(slave.getStatus())) {
			return;
		}
		if (slave.getWarnList() != null) {
			ListIterator<HMens> it = slave.getWarnList().listIterator();
			while (it.hasNext()) {
				HMens mens = it.next();
				master.addWarning(mens);
			}
		}
		if (slave.getInfoList() != null) {
			ListIterator<HMens> it = slave.getInfoList().listIterator();
			while (it.hasNext()) {
				HMens mens = it.next();
				master.addWarning(mens);
			}
		}
	}
}
