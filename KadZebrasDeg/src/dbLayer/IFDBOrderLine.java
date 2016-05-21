package dbLayer;

import java.util.ArrayList;

import modelLayer.OrderLine;
import exceptionsLayer.DatabaseException;

public interface IFDBOrderLine {
	// create one
	public int insertOrderLine(OrderLine orderLine) throws DatabaseException;

	// read all
	public ArrayList<OrderLine> getAllOrderLines();

	// read one
	public OrderLine findOrderLine(int id) throws DatabaseException;

	// update one
	public int updateOrderLine(int id, OrderLine ol) throws DatabaseException;

	ArrayList<OrderLine> findOrderLineByOrderId(int orderId) throws DatabaseException;
}
