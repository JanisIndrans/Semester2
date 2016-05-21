package controlLayer;

import java.util.ArrayList;

import modelLayer.Table;
import dbLayer.DBConnect;
import dbLayer.DBTable;
import exceptionsLayer.DatabaseException;

public class TableController {
	DBTable dBTable = new DBTable();

	public Table findTableByNo(int tableNo) throws DatabaseException {
		return dBTable.findTableByTableNo(tableNo);
	}

	public boolean checkIfExists(Table table) {
		return table.isExists();
	}

	public boolean checkIfAvialable(Table table) {
		return table.isAvailable();
	}

	public ArrayList<Table> getAllTables() {
		return dBTable.getAllTables();
	}
	
	//added by Janis 14.06.2015
	public ArrayList<Table> getAllExistingTables(){
		ArrayList<Table> existing = new ArrayList<Table>();
		for(Table t: dBTable.getAllTables()){
			if(t.isExists()){
				existing.add(t);
			}
		}
		return existing;
	}

	public void updateTable(int tableNo, Table table) {
		try {
			DBConnect.startTransaction();
			dBTable.updateTable(tableNo, table);
			DBConnect.commitTransaction();
		} catch (Exception e) {
			DBConnect.rollbackTransaction();
			e.printStackTrace();
		}
	}
	

	public void insertTable(Table table) {
		try {
			DBConnect.startTransaction();
			dBTable.insertTable(table);
			DBConnect.commitTransaction();
		} catch (DatabaseException e) {
			DBConnect.rollbackTransaction();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Added by Janis 12.06.2015
	public int countAllAvailableSeats(){
		int nOOfSeats = 0;
		ArrayList<Table> all = getAllTables();
		for(Table t : all){
			if(t.isAvailable()){
				nOOfSeats = nOOfSeats + t.getNoOfSeats();
			}
		}
		return nOOfSeats;
	}
	public ArrayList<Table> getReservedTables(int rId){
		return dBTable.getAllReservedTables(rId);
	}
	public int findTableIdByTableNo(int tableNo){
		return dBTable.findTableIdByTableNo(tableNo);
	}
}
