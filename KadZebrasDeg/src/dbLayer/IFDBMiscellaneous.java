package dbLayer;

import java.util.ArrayList;

import modelLayer.Miscellaneous;
import exceptionsLayer.DatabaseException;

public interface IFDBMiscellaneous {
	// create one
	public int insertMiscellaneous(Miscellaneous miscellaneous)
			throws DatabaseException;

	// read all
	public ArrayList<Miscellaneous> getAllMiscellaneous();

	// read one
	public Miscellaneous findMiscellaneous(String name)
			throws DatabaseException;

	// update one
	public int updateMiscellaneous(String name, Miscellaneous m)
			throws DatabaseException;
}
