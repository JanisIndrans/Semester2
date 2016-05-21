package testLayer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dbLayer.DBConnect;

public class databaseTests {
	private  Connection con;
	@Before
	public void setUp(){
		con = DBConnect.getInstance().getDBcon();
	}
	
	@After
	public void setIn(){
		
	}
	
	@Test
	public void testDatabaseConnection() {
		DBConnect.startTransaction();
	}
	@Test
	public void testTransaction(){
		DBConnect.startTransaction();
		String query = "Insert into lLogin(username, pass, accessType) values ('manager','manager','2')";
		int rc = -1;
		Statement stmt;
		try {
			 stmt = con.createStatement();
			 stmt.setQueryTimeout(5);
			 rc = stmt.executeUpdate(query);
		     stmt.close();
		     System.out.println("Inserted.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 System.out.println("Error with insertion in test.");
		}
		DBConnect.commitTransaction();       
       
	}

}
