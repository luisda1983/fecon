package ldrsoftware.app.service;

import ldrsoftware.app.comunication.request.HconRequest;
import ldrsoftware.app.comunication.response.BaseResponse;
import ldrsoftware.app.comunication.response.HconResponse;
import ldrsoftware.app.work.WorkClass;

public interface IHconService {

	void apunteContable(HconRequest request, BaseResponse response, WorkClass work) throws Exception;
	void list(HconRequest request, HconResponse response, WorkClass work) throws Exception;
	void anul(HconRequest request, BaseResponse response, WorkClass work) throws Exception;

}
