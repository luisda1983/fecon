package ldrsoftware.app.dao;

/**
 * Interfaz de operaciones disponibles sobre la tabla THSECU.
 * @author Luis David
 *
 */
public interface IHSecuDAO {
	int getNext(long inst, String iden);
}
