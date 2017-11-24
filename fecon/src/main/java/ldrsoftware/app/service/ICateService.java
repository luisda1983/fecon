package ldrsoftware.app.service;

import ldrsoftware.app.comunication.request.CateRequest;
import ldrsoftware.app.comunication.response.BaseResponse;
import ldrsoftware.app.comunication.response.CateResponse;

import ldrsoftware.app.work.WorkClass;

public interface ICateService {

	void getListFull  (CateRequest request, CateResponse response, WorkClass work) throws Exception;
	void getList      (CateRequest request, CateResponse response, WorkClass work) throws Exception;
	void saveEdit     (CateRequest request, BaseResponse response, WorkClass work) throws Exception;
	void saveNuev     (CateRequest request, BaseResponse response, WorkClass work) throws Exception;
	void acti         (CateRequest request, BaseResponse response, WorkClass work) throws Exception;
	void move         (CateRequest request, BaseResponse response, WorkClass work) throws Exception;
}
