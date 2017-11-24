package ldrsoftware.app.service;

import ldrsoftware.app.comunication.request.BaseRequest;
import ldrsoftware.app.comunication.response.BaseResponse;
import ldrsoftware.app.work.WorkClass;

public interface IUsuarioService {

	void valUsuario(BaseRequest request, BaseResponse response, WorkClass work) throws Exception;

}
