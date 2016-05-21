package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Staff;
import exceptionsLayer.DatabaseException;

public class DBStaff implements IFDBStaff {
	private Connection con;

	public DBStaff() {
		con = DBConnect.getInstance().getDBcon();
	}

	@Override
	public int insertStaff(Staff staff) throws DatabaseException {
		int rc = -1;
		String query = "";
		query = "INSERT INTO Staff(name, bankAccount, address, profession, phoneNo, cprNo, sExists) VALUES ('"
				+ staff.getName()
				+ "','"
				+ staff.getBankAccount()
				+ "','"
				+ staff.getAddress()
				+ "','"
				+ staff.getProfession()
				+ "','"
				+ staff.getPhoneNo()
				+ "','"
				+ staff.getCprNo()
				+ "','"
				+ staff.isExists() + "')";

		System.out.println("insert : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query, stmt.RETURN_GENERATED_KEYS);
			int id = new GeneratedKey().getGeneratedKey(stmt);
			staff.setStaffId(id);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("Staff not inserted");
			throw new DatabaseException("Something else is wrong in DBStaff");
		}
		return (rc);
	}

	@Override
	public ArrayList<Staff> getAllStaffs() {
		return miscWhere("");
	}

	@Override
	public Staff findStaff(String cprNo) throws DatabaseException {
		String wClause = "  cprNo = '" + cprNo + "'";
		return singleWhere(wClause);
	}

	@Override
	public Staff findStaffById(int id) throws DatabaseException {
		String wClause = "  id = '" + id + "'";
		return singleWhere(wClause);
	}
	@Override
	public Staff findStaffByName(String name) throws DatabaseException {
		String wClause = "  name = '" + name + "'";
		return singleWhere(wClause);
	}

	@Override
	public int updateStaff(String cprNo, Staff staff) {
		// New: using a prepared statement (note, this prepared statement is not
		// reused, but it could be.)
		String q = "update Staff set bankAccount=?, address=?, profession=?, phoneNo=?, cprNo=?, sExists=? where name='"+staff.getName()+"'";
		int res = 0;
		try (PreparedStatement s = DBConnect.getInstance().getDBcon()
				.prepareStatement(q)) {
			s.setString(1, staff.getBankAccount());
			s.setString(2, staff.getAddress());
			s.setString(3, staff.getProfession());
			s.setString(4, staff.getPhoneNo());
			s.setString(5, staff.getCprNo());
			s.setBoolean(6, staff.isExists());
			System.out.println("staff: "+ staff.getName() + staff.getBankAccount()+
					staff.getAddress()+ staff.getProfession() +staff.getPhoneNo() + staff.getCprNo() + staff.isExists()
					
					);
			res = s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {

		}
		return res;
	}

	// private method
	private Staff getStaff(String wClause) {
		ResultSet results;
		Staff staffObj = new Staff();

		String query = buildQuery(wClause);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				staffObj = buildStaff(results);
				stmt.close();
			} else {
				staffObj = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return staffObj;
	}

	// course misc where
	private ArrayList<Staff> miscWhere(String wClause) {
		ResultSet results;
		ArrayList<Staff> list = new ArrayList<Staff>();
		String query = buildQuery(wClause);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Staff staffObj = new Staff();
				staffObj = buildStaff(results);
				list.add(staffObj);
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
	private Staff singleWhere(String wClause) {
		ResultSet results;
		Staff staffObj = new Staff();
		String query = buildQuery(wClause);
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				staffObj = buildStaff(results);
				stmt.close();
			} else {
				staffObj = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return staffObj;
	}

	// method to build the query
	private String buildQuery(String wClause) {
		String query = "SELECT *  FROM Staff";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	// method to build an employee object
	private Staff buildStaff(ResultSet results) {
		Staff staffObj = new Staff();
		try {
			staffObj.setName(results.getString("name"));
			staffObj.setBankAccount(results.getString("bankAccount"));
			staffObj.setAddress(results.getString("address"));
			staffObj.setProfession(results.getString("profession"));
			staffObj.setPhoneNo(results.getString("phoneNo"));
			staffObj.setCprNo(results.getString("cprNo"));
			staffObj.setExists(results.getBoolean("sExists"));
		} catch (Exception e) {
			System.out.println("Error in building the staff object");
		}
		return staffObj;
	}
}
