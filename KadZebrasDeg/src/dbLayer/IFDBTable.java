package dbLayer;

import java.util.ArrayList;

import modelLayer.Table;
import exceptionsLayer.DatabaseException;

public interface IFDBTable {
	// create one
	public int insertTable(Table table) throws DatabaseException;

	// read all
	public ArrayList<Table> getAllTables();

	// read one
	public Table findTable(int id) throws DatabaseException;

	// update one
	public int updateTable(int tableNo, Table table) throws DatabaseException;
	
	// find table by table no
	public Table findTableByTableNo(int tableNo) throws DatabaseException;
}
