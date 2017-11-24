///////////////////////////////////////////////////////////////////////////////////////////////
// Area de entrada comun a todos los servicios.                                              //
///////////////////////////////////////////////////////////////////////////////////////////////
// Version   | F. Fin Desa | F. Install | Comentarios de version                             //
// v.0.00.00 |  07/03/2015 | xx/xx/xxxx | Primera version del SW                             //
///////////////////////////////////////////////////////////////////////////////////////////////

package ldrsoftware.app.comunication.request;

public class BaseRequest {

	//Identificador de sesion
	private long idSesion;

	//Identificador de instalacion
	private int inst;
	
	public long getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(long idSesion) {
		this.idSesion = idSesion;
	}

	public int getInst() {
		return inst;
	}

	public void setInst(int inst) {
		this.inst = inst;
	}
	
}
