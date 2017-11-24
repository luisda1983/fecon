package ldrsoftware.app.service;

import ldrsoftware.app.comunication.request.CuenRequest;
import ldrsoftware.app.comunication.response.BaseResponse;
import ldrsoftware.app.work.WorkClass;

public interface ICuenService {
	void tras       (CuenRequest request, BaseResponse response, WorkClass work) throws Exception;
	void cuad       (CuenRequest request, BaseResponse response, WorkClass work) throws Exception;
}
