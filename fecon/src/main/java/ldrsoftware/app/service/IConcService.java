package ldrsoftware.app.service;

import ldrsoftware.app.comunication.request.BaseRequest;
import ldrsoftware.app.comunication.request.ConcRequest;
import ldrsoftware.app.comunication.response.ConceptoResponse;
import ldrsoftware.app.work.WorkClass;

public interface IConcService {

	void getListFull  (BaseRequest     request, ConceptoResponse response, WorkClass work) throws Exception;
	void getListByIdsu(ConcRequest request, ConceptoResponse response, WorkClass work) throws Exception;
	void saveEdit     (ConcRequest request, ConceptoResponse response, WorkClass work) throws Exception;
	void saveNew      (ConcRequest request, ConceptoResponse response, WorkClass work) throws Exception;
	void acti         (ConcRequest request, ConceptoResponse response, WorkClass work) throws Exception;
	void move         (ConcRequest request, ConceptoResponse response, WorkClass work) throws Exception;
}
