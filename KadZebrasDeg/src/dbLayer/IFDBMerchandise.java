package dbLayer;

import java.util.ArrayList;

import modelLayer.Merchandise;
import exceptionsLayer.DatabaseException;

public interface IFDBMerchandise {
	// create one
	public int insertMerchandise(Merchandise merchandise)
			throws DatabaseException;

	// read all
	public ArrayList<Merchandise> getAllMerchandises();

	// read one
	public Merchandise findMerchandise(String name) throws DatabaseException;

	// update one
	public int updateMerchandise(String name) throws DatabaseException;
}
