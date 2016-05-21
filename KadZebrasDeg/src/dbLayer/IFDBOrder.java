package dbLayer;

import java.util.ArrayList;

import modelLayer.Order;
import exceptionsLayer.DatabaseException;

public interface IFDBOrder {
	// create one
	public int insertOrder(Order order) throws DatabaseException;

	// read all
	public ArrayList<Order> getAllOrders();

	// read one
	public Order findOrder(int id) throws DatabaseException;

	// update one
	public int updateOrder(int id, Order o) throws DatabaseException;
}
