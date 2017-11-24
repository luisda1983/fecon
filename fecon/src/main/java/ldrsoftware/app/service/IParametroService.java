package ldrsoftware.app.service;

import ldrsoftware.app.comunication.request.ParmRequest;
import ldrsoftware.app.comunication.response.ParametroResponse;
import ldrsoftware.app.core.parm.valo.BaseParmValo;
import ldrsoftware.app.work.WorkClass;

public interface IParametroService {

	void obtenerListaParametro(ParametroResponse response, String tabla) throws Exception;
	BaseParmValo valo(String tabl, String clav) throws Exception;

	void list(ParmRequest request, ParametroResponse response, WorkClass work) throws Exception;
}
