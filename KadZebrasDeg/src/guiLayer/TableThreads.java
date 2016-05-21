package guiLayer;

import java.util.ArrayList;

import modelLayer.Table;
import dbLayer.DBTable;
import exceptionsLayer.DatabaseException;

public class TableThreads extends Thread {
	private Object obj=new Object();
	private Table table;
	private ArrayList<Table> tables;
	private DBTable dbTable;
	private int numberOfTables;
	public TableThreads(int numberOfTables){
		this.numberOfTables=numberOfTables;
		dbTable=new DBTable();
		tables = new ArrayList<>();
	}
	public void run() {
		 synchronized(obj){
		for (int i=1;i<=numberOfTables;i++){
			try {
				table = dbTable.findTableByTableNo(i);
				tables.add(table);
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		 }
	}
	public ArrayList<Table> returnTables(){
		return tables;
	}
}
