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
import exceptionsLayer.DatabaseException;

public class DBDrink implements IFDBDrink {
	private Connection con;

	public DBDrink() {
		con = DBConnect.getInstance().getDBcon();
	}

	@Override
	public int insertDrink(Drink drink) throws DatabaseException {
		int rc = -1;
		String query = "";
		query = "INSERT INTO Merchandise (name, price, mExists, mType) VALUES ('"
				+ drink.getName() + "'," +drink.getPrice()  + ","+1 + "," + 1 + ")";

		System.out.println("insert : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query, stmt.RETURN_GENERATED_KEYS);
			int id = new GeneratedKey().getGeneratedKey(stmt);
			drink.setId(id);
			insertIntoMerchandise(drink);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("Merchandise not inserted");
			throw new DatabaseException(
					"Something else is wrong in merchandise");
		}
		return (rc);

	}

	private int insertIntoMerchandise(Drink drink) throws DatabaseException {
		int rc = -1;
		String query = "";
		query = "INSERT INTO DRINK(id,quantityInStock, alcoholConcentration, minQuantityInStock) VALUES ("
				+ drink.getId()
				+ ","
				+ drink.getQuantityInStock()
				+ ","
				+ drink.getAlcoholConcetration()
				+ ","
				+ drink.getMinQuantityInStock() + ")";

		System.out.println("insert : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("Drink not inserted");
			throw new DatabaseException("Something else is wrong in DBDrink");
		}
		return (rc);

	}

	@Override
	public ArrayList<Drink> getAllDrinks() {
		return miscWhere("");
	}

	@Override
	public Drink findDrink(String name) throws DatabaseException {
		String wClause = "  merchandise.name = '" + name
				+ "' AND drink.id=merchandise.mId";
		return singleWhere(wClause);
	}

	@Override
	public int updateDrink(String name, Drink d) {
		String q = "";
		int res = 0;
		// New: using a prepared statement (note, this prepared statement is not
		// reused, but it could be.)
		q = "update merchandise set name=?, price=?, mExists=?, mType=? where name="
				+ name;
		try (PreparedStatement s = DBConnect.getInstance().getDBcon()
				.prepareStatement(q)) {

			s.setString(1, d.getName());
			s.setFloat(2, d.getPrice());
			s.setBoolean(3, d.getExists());
			s.setInt(4, 3);
			res = s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {

		}
		// New: using a prepared statement (note, this prepared statement is not
		// reused, but it could be.)
		q = "update drink set id= ?  quantityInStock=?, alcoholConcentration=?, minQuantityInStock=? where name="
				+ name;
		res = 0;
		try (PreparedStatement s = DBConnect.getInstance().getDBcon()
				.prepareStatement(q)) {
			s.setInt(1, d.getId());
			s.setInt(2, d.getQuantityInStock());
			s.setDouble(3, d.getAlcoholConcetration());
			s.setInt(4, d.getMinQuantityInStock());
			res = s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {

		}
		return res;
	}

	// private method
	private Drink getDrink(String wClause) {
		ResultSet results;
		Drink drinkObj = new Drink();

		String query = buildQuery(wClause);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				drinkObj = buildDrink(results);
				stmt.close();
			} else {
				drinkObj = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return drinkObj;
	}

	// course misc where
	private ArrayList<Drink> miscWhere(String wClause) {
		ResultSet results;
		ArrayList<Drink> list = new ArrayList<Drink>();
		String query = buildQuery(wClause);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Drink drinkObj = new Drink();
				drinkObj = buildDrink(results);
				//if(!checkIfObjectAllreadyExist(list, drinkObj)){
					list.add(drinkObj);
					//}
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
	private Drink singleWhere(String wClause) {
		ResultSet results;
		Drink drinkObj = new Drink();
		String query = buildQuery(wClause);
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				drinkObj = buildDrink(results);
				stmt.close();
			} else {
				drinkObj = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return drinkObj;
	}

	// method to build the query
	private String buildQuery(String wClause) {
		String query = "SELECT *  FROM Merchandise inner join Drink on merchandise.mid = drink.id";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	// method to build an employee object
	private Drink buildDrink(ResultSet results) {
		Drink drinkObj = new Drink();
		try {
			drinkObj.setId(results.getInt("id"));
			drinkObj.setName(results.getString("name"));
			drinkObj.setPrice(results.getFloat("price"));
			drinkObj.setQuantityInStock(results.getInt("quantityInStock"));
			drinkObj.setAlcoholConcetration(results
					.getFloat("alcoholConcentration"));
			drinkObj.setMinQuantityInStock(results.getInt("minQuantityInStock"));
			if(results.getInt("mExists")==1){
				drinkObj.setExists(true);
			}else
			if(results.getInt("mExists")==0){
				drinkObj.setExists(false);
			}
			
		} catch (Exception e) {
			System.out.println("Error in building the drink object");
		}
		return drinkObj;
	}

	public Merchandise findDrinkById(int id) {
		String wClause = "  merchandise.mId = " + id + " AND drink.id= " + id;
		return singleWhere(wClause);
	}
	public boolean checkIfObjectAllreadyExist(ArrayList<Drink> list, Drink obj){
		for(Drink c : list){
			if(c.getName().equals(obj.getName())){
				return true;
			}
		}
		return false;
	}

}
