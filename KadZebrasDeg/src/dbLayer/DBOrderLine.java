package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Drink;
import modelLayer.Merchandise;
import modelLayer.OrderLine;
import exceptionsLayer.DatabaseException;

public class DBOrderLine implements IFDBOrderLine {
	private Connection con;

	public DBOrderLine() {
		con = DBConnect.getInstance().getDBcon();
	}

	@Override
	public int insertOrderLine(OrderLine orderLine) throws DatabaseException {
		int rc = -1;
		String query = "";
		if(orderLine.isDone()){
		
		query = "INSERT INTO ORDERLINE(quantity, oId, meId, isDone) VALUES ("
				+ orderLine.getQuantity() + "," + orderLine.getOrderId()
				+ "," + orderLine.getMerchandise().getId() + ","
				+ 1 + ")";
		System.out.println("if: " + query);
		}else{
			query = "INSERT INTO ORDERLINE(quantity, oId, meId, isDone) VALUES ("
					+ orderLine.getQuantity() + "," + orderLine.getOrderId()
					+ "," + orderLine.getMerchandise().getId() + ","
					+ 0 + ")";
			System.out.println("else: " + query);
		}
		

		System.out.println("insert : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("OrderLine not inserted");
			throw new DatabaseException(
					"Something else is wrong in DBOrderLine");
		}
		return (rc);
	}

	@Override
	public ArrayList<OrderLine> getAllOrderLines() {
		return miscWhere("");
	}

	@Override
	public OrderLine findOrderLine(int id) throws DatabaseException {
		String wClause = "  id = " + id + "";
		return singleWhere(wClause);
	}
	@Override
	public ArrayList<OrderLine> findOrderLineByOrderId(int orderId) throws DatabaseException {
		String wClause = "  oId = " + orderId + "";
		return miscWhere(wClause);
	}
	@Override
	public int updateOrderLine(int id, OrderLine ol) {
		// New: using a prepared statement (note, this prepared statement is not
		// reused, but it could be.)
		String q = "update orderline set quantity=?, oId=?, meId=?, isDone=? where id="
				+ id;
		int res = 0;
		try (PreparedStatement s = DBConnect.getInstance().getDBcon()
				.prepareStatement(q)) {
			s.setDouble(1, ol.getQuantity());
			s.setInt(2, ol.getOrderId());
			s.setInt(3, ol.getMerchandise().getId());
			s.setBoolean(4, ol.isDone());
			res = s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {

		}
		return res;
	}

	// private method
	private OrderLine getOrderLine(String wClause) {
		ResultSet results;
		OrderLine orderLineObj = new OrderLine();

		String query = buildQuery(wClause);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				orderLineObj = buildOrderLine(results);
				stmt.close();
			} else {
				orderLineObj = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return orderLineObj;
	}

	// course misc where
	private ArrayList<OrderLine> miscWhere(String wClause) {
		ResultSet results;
		ArrayList<OrderLine> list = new ArrayList<OrderLine>();
		String query = buildQuery(wClause);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				OrderLine orderLineObj = new OrderLine();
				orderLineObj = buildOrderLine(results);
				list.add(orderLineObj);
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
	private OrderLine singleWhere(String wClause) {
		ResultSet results;
		OrderLine orderLineObj = new OrderLine();
		String query = buildQuery(wClause);
		System.out.println(query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if (results.next()) {
				orderLineObj = buildOrderLine(results);
				stmt.close();
			} else {
				orderLineObj = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return orderLineObj;
	}

	// method to build the query
	private String buildQuery(String wClause) {
		String query = "SELECT *  FROM OrderLine";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	// method to build an employee object
	private OrderLine buildOrderLine(ResultSet results) {
		OrderLine orderLineObj = new OrderLine();
		Merchandise merchandise = null;
		int mType = 0;
		try {
			orderLineObj.setQuantity(results.getInt("quantity"));
			orderLineObj.setDone(results.getBoolean("isDone"));
			mType = findMerchandiseType(results.getInt("meId"));
			if (mType == 1) {
				merchandise = new DBCourse().findCourseById(results
						.getInt("meId"));
			} else if (mType == 2) {
				merchandise = new DBMiscellaneous()
						.findMiscellaneousById(results.getInt("meId"));
			} else if (mType == 2) {
				merchandise = new DBDrink().findDrinkById(results
						.getInt("meId"));
			}
			orderLineObj.setMerchandise(merchandise);
			orderLineObj.setOrderId(results.getInt("oId"));
		} catch (Exception e) {
			System.out.println("Error in building the orderLine object");
		}
		return orderLineObj;
	}

	private int findMerchandiseType(int id) throws DatabaseException {
		int mType = 0;
		String query = "";
		query = "Select mType from Merchandise where mId=" + id;
		System.out.println("insert : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			mType = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("Find merchandise by type failed");
			throw new DatabaseException(
					"Something else is wrong in DBOrderLine");
		}
		return mType;
	}
	public int findOrderLineIdByMerchandiseIdAndOrderId(int meId, int oId){
		String query = "select id from OrderLine where oId =" +  oId + " and meId =" + meId;
		System.out.println(query);
		int olId = -1;
		ResultSet results;
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				olId = results.getInt("id");
			}// end while
			stmt.close();
		}// end try
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return olId;
	}
}
