package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Reservation;
import modelLayer.Table;
import exceptionsLayer.DatabaseException;

public class DBTable implements IFDBTable {
	private Connection con;

	public DBTable() {
		con = DBConnect.getInstance().getDBcon();
	}

	@Override
	public int insertTable(Table table) throws DatabaseException {
		int rc = -1;
		String query = "";
		query = "INSERT INTO tTable (noOfSeats, tableNo, isAvailable, tExists, tableOnTheNorth, tableOnTheEast, tableOnTheSouth, tableOnTheWest) VALUES ('"
				+ table.getNoOfSeats()
				+ "','"
				+ table.getTableNo()
				+ "','"
				+ table.isAvailable()
				+ "','"
				+ table.isExists()
				+ "','"
				+ table.getTableOnTheNorth()
				+ "','"
				+ table.getTableOnTheEast()
				+ "','"
				+ table.getTableOnTheSouth()
				+ "','"
				+ table.getTableOnTheWest() + "')";
		System.out.println("insert : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("Table not inserted");
			throw new DatabaseException("Something else is wrong in DBTable");
		}
		return (rc);
	}

	@Override
	public ArrayList<Table> getAllTables() {
		return miscWhere("");
	}

	@Override
	public Table findTable(int id) throws DatabaseException {
		String wClause = "  id = '" + id + "'";
		return singleWhere(wClause);
	}
	
	@Override
	public Table findTableByTableNo(int tableNo) throws DatabaseException {
		String wClause = " tableNo = '" + tableNo + "'";
		return singleWhere(wClause);
	}

	@Override
	public int updateTable(int tableNo, Table table) {
		// New: using a prepared statement (note, this prepared statement is not
		// reused, but it could be.)
		String q = "update tTable set noOfSeats=?, isAvailable=?, tExists=?, tableOnTheNorth=?, tableOnTheEast=?, tableOnTheSouth=?, tableOnTheWest=? where tableNo="
				+ tableNo;
		int res = 0;
		try (PreparedStatement s = DBConnect.getInstance().getDBcon()
				.prepareStatement(q)) {
			s.setInt(1, table.getNoOfSeats());
			s.setBoolean(2, table.isAvailable());
			s.setBoolean(3, table.isExists());
			s.setInt(4, table.getTableOnTheNorth());
			s.setInt(5, table.getTableOnTheEast());
			s.setInt(6, table.getTableOnTheSouth());			
			s.setInt(7, table.getTableOnTheWest());
			
			res = s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {

		}
		return res;
	}

	// private method
	private Table getTable(String wClause) {
		ResultSet results;
		Table tableObj = new Table();

		String query = buildQuery(wClause);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				tableObj = buildTable(results);
				stmt.close();
			} else {
				tableObj = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return tableObj;
	}

	// course misc where
	private ArrayList<Table> miscWhere(String wClause) {
		ResultSet results;
		ArrayList<Table> list = new ArrayList<Table>();
		String query = buildQuery(wClause);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Table tableObj = new Table();
				tableObj = buildTable(results);
				list.add(tableObj);
			}// end while
			stmt.close();
		}// end try
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}

	// Single where is used when we only select one thing
	private Table singleWhere(String wClause) {
		ResultSet results;
		Table tableObj = new Table();
		String query = buildQuery(wClause);
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				tableObj = buildTable(results);
				stmt.close();
			} else {
				tableObj = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return tableObj;
	}

	// method to build the query
	private String buildQuery(String wClause) {
		String query = "SELECT *  FROM tTable";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	// method to build an employee object
	private Table buildTable(ResultSet results) {
		Table tableObj = new Table();
		try {
			tableObj.setNoOfSeats(results.getInt("noOfSeats"));
			tableObj.setTableNo(results.getInt("tableNo"));
			tableObj.setAvailable(results.getBoolean("isAvailable"));
			tableObj.setExists(results.getBoolean("tExists"));
			tableObj.setTableOnTheNorth(results.getInt("tableOnTheNorth"));
			tableObj.setTableOnTheEast(results.getInt("tableOnTheEast"));
			tableObj.setTableOnTheSouth(results.getInt("tableOnTheSouth"));
			tableObj.setTableOnTheWest(results.getInt("tableOnTheWest"));
		} catch (Exception e) {
			System.out.println("Error in building the table object");
		}
		return tableObj;
	}
	public ArrayList<Table> getAllReservedTables(int rId){
		ArrayList<Table> tempList = new ArrayList<Table>();
		String query = "select tId from ReservedTable where rId = (select id from Reservation where id =" + rId + ")";
		System.out.println(query);
		ResultSet results;
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Table temp = findTable(results.getInt("tId"));
				Table t = new Table();
				
				t.setAvailable(temp.isAvailable());
				t.setExists(temp.isExists());
				t.setNoOfSeats(temp.getNoOfSeats());
				t.setTableNo(temp.getTableNo());
				t.setTableOnTheEast(temp.getTableOnTheEast());
				t.setTableOnTheNorth(temp.getTableOnTheNorth());
				t.setTableOnTheSouth(temp.getTableOnTheSouth());
				t.setTableOnTheWest(temp.getTableOnTheWest());
				tempList.add(t);
			}// end while
			stmt.close();
		}// end try
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return tempList;

	}
	
	public int findTableIdByTableNo(int tableNo){
		int id = -1;
		String query = "select id from tTable where tableNo = " + tableNo;
		System.out.println(query);
		ResultSet results;
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				id = results.getInt("id");
			}// end while
			stmt.close();
		}// end try
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return id;
	}
}
