package ldrsoftware.app.service;

import ldrsoftware.app.comunication.request.BaseRequest;
import ldrsoftware.app.comunication.request.PrepRequest;
import ldrsoftware.app.comunication.response.BaseResponse;
import ldrsoftware.app.comunication.response.PrepResponse;
import ldrsoftware.app.work.WorkClass;

public interface IPrepService {

	void list(PrepRequest request, PrepResponse response, WorkClass work) throws Exception;
	void save(PrepRequest request, BaseResponse response, WorkClass work) throws Exception;
	void anua(BaseRequest request, PrepResponse response, WorkClass work) throws Exception;
	void borr(PrepRequest request, BaseResponse response, WorkClass work) throws Exception;
	void copy(PrepRequest request, BaseResponse response, WorkClass work) throws Exception;
}
