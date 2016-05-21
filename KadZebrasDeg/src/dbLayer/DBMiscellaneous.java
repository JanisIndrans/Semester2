package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Course;
import modelLayer.Drink;
import modelLayer.Merchandise;
import modelLayer.Miscellaneous;
import exceptionsLayer.DatabaseException;

public class DBMiscellaneous implements IFDBMiscellaneous {
	private Connection con;

	public DBMiscellaneous() {
		con = DBConnect.getInstance().getDBcon();
	}

	@Override
	public int insertMiscellaneous(Miscellaneous miscellaneous)
			throws DatabaseException {
		int rc = -1;
		String query = "";
		query = "INSERT INTO Merchandise (name, price, mExists, mType) VALUES ('"
				+ miscellaneous.getName() + "'," +miscellaneous.getPrice()  + ","+1 + "," + 1 + ")";

		System.out.println("insert : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query, stmt.RETURN_GENERATED_KEYS);
			int id = new GeneratedKey().getGeneratedKey(stmt);
			miscellaneous.setId(id);
			insertIntoMerchandise(miscellaneous);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("Merchandise not inserted");
			throw new DatabaseException(
					"Something else is wrong in merchandise");
		}
		return (rc);

	}

	private int insertIntoMerchandise(Miscellaneous miscellaneous)
			throws DatabaseException {
		int rc = -1;
		String query = "";
		query = "INSERT INTO Miscellaneous (id, quantityInStock, minQuantityInStock) VALUES ("
				+ miscellaneous.getId()
				+ ","
				+ miscellaneous.getQuantityInStock()
				+ ","
				+ miscellaneous.getMinQuantityInStock() + ")";

		System.out.println("insert : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("Miscellaneous not inserted");
			throw new DatabaseException(
					"Something else is wrong in DBMiscellaneous");
		}
		return (rc);

	}

	@Override
	public ArrayList<Miscellaneous> getAllMiscellaneous() {
		return miscWhere("");
	}

	@Override
	public Miscellaneous findMiscellaneous(String name)
			throws DatabaseException {
		String wClause = "  merchandise.name = '" + name
				+ "' AND course.id=miscellaneous.mId";
		return singleWhere(wClause);
	}

	@Override
	public int updateMiscellaneous(String name, Miscellaneous m) {
		String q = "";
		int res = 0;
		// New: using a prepared statement (note, this prepared statement is not
		// reused, but it could be.)
		q = "update merchandise set name=?, price=?, mExists=?, mType=? where name="
				+ name;
		try (PreparedStatement s = DBConnect.getInstance().getDBcon()
				.prepareStatement(q)) {

			s.setString(1, m.getName());
			s.setFloat(2, m.getPrice());
			s.setBoolean(3, m.getExists());
			s.setInt(4, 2);
			res = s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {

		}
		// New: using a prepared statement (note, this prepared statement is not
		// reused, but it could be.)
		q = "update Miscellaneous set mId= ?, name=?, price=?, quantityInStock=?, minQuantityInStock=? where name="
				+ name;
		res = 0;
		try (PreparedStatement s = DBConnect.getInstance().getDBcon()
				.prepareStatement(q)) {
			s.setInt(1, m.getId());
			s.setString(2, m.getName());
			s.setFloat(3, m.getPrice());
			s.setInt(4, m.getQuantityInStock());
			s.setInt(5, m.getMinQuantityInStock());
			res = s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {

		}
		return res;
	}

	// private method
	private Miscellaneous getMiscellaneous(String wClause) {
		ResultSet results;
		Miscellaneous miscellaneousObj = new Miscellaneous();

		String query = buildQuery(wClause);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				miscellaneousObj = buildMiscellaneous(results);
				stmt.close();
			} else {
				miscellaneousObj = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return miscellaneousObj;
	}

	// course misc where
	private ArrayList<Miscellaneous> miscWhere(String wClause) {
		ResultSet results;
		ArrayList<Miscellaneous> list = new ArrayList<Miscellaneous>();
		String query = buildQuery(wClause);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Miscellaneous miscellaneousObj = new Miscellaneous();
				miscellaneousObj = buildMiscellaneous(results);
				
					list.add(miscellaneousObj);
					
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
	private Miscellaneous singleWhere(String wClause) {
		ResultSet results;
		Miscellaneous miscellaneousObj = new Miscellaneous();
		String query = buildQuery(wClause);
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				miscellaneousObj = buildMiscellaneous(results);
				stmt.close();
			} else {
				miscellaneousObj = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		
		return miscellaneousObj;
	}

	// method to build the query
	private String buildQuery(String wClause) {
		String query = "SELECT *  FROM Merchandise inner join Miscellaneous on merchandise.mid = Miscellaneous.id";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	// method to build an employee object
	private Miscellaneous buildMiscellaneous(ResultSet results) {
		Miscellaneous miscellanousObj = new Miscellaneous();
		try {
			miscellanousObj.setId(results.getInt("id"));
			miscellanousObj.setName(results.getString("name"));
			miscellanousObj.setPrice(results.getFloat("price"));
			miscellanousObj.setQuantityInStock(results
					.getInt("quantityInStock"));
			miscellanousObj.setMinQuantityInStock(results
					.getInt("minQuantityInStock"));
			if(results.getInt("mExists")==1){
				miscellanousObj.setExists(true);
			}else
			if(results.getInt("mExists")==0){
				miscellanousObj.setExists(false);
			}
		} catch (Exception e) {
			System.out.println("Error in building the miscellaneous object");
		}
		return miscellanousObj;
	}

	public Merchandise findMiscellaneousById(int id) {
		String wClause = "  merchandise.mId = " + id
				+ " AND miscellaneous.id= " + id;
		return singleWhere(wClause);
	}
	public boolean checkIfObjectAllreadyExist(ArrayList<Miscellaneous> list, Miscellaneous obj){
		for(Miscellaneous c : list){
			if(c.getName().equals(obj.getName())){
				return true;
			}
		}
		return false;
	}

}
