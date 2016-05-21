package dbLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import modelLayer.Login;
import exceptionsLayer.DatabaseException;

public class DBLogin {
	private Connection con;

	public DBLogin() {
		con = DBConnect.getInstance().getDBcon();
	}
	public Login findLogin(String name, String password) throws DatabaseException {
		String wClause = "  lLogin.username = '" + name 
				+ "' AND lLogin.pass= '"+ password +"'";
		return singleWhere(wClause);
	}
	// Single where is used when we only select one thing
		private Login singleWhere(String wClause) {
			ResultSet results;
			Login loginObj = new Login();
			String query = buildQuery(wClause);
			System.out.println(query);
			try {
				Statement stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				results = stmt.executeQuery(query);
				if (results.next()) {
					loginObj = buildLogin(results);
					stmt.close();
				} else {
					loginObj = null;
				}
			}// end try
			catch (Exception e) {
				System.out.println("Query exception: " + e);
			}
			return loginObj;
		}

		// method to build the query
		private String buildQuery(String wClause) {
			String query = "SELECT *  FROM lLogin";

			if (wClause.length() > 0)
				query = query + " WHERE " + wClause;

			return query;
		}
		// method to build an employee object
		private Login buildLogin(ResultSet results) {
			Login login = new Login();
			try {
				login.setUsername(results.getString("username"));
				login.setAccessType(results.getString("accessType"));
				
			} catch (Exception e) {
				System.out.println("Error in building the login object");
			}
			return login;
		}
}
