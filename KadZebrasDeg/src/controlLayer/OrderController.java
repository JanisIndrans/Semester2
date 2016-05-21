package controlLayer;

import java.util.ArrayList;

import modelLayer.Merchandise;
import modelLayer.Order;
import modelLayer.OrderLine;
import modelLayer.Reservation;
import modelLayer.Staff;
import dbLayer.DBConnect;
import dbLayer.DBOrder;
import dbLayer.DBOrderLine;
import exceptionsLayer.DatabaseException;

public class OrderController {
	private MerchandiseController merchandiseController;
	private StaffController staffController;
	private TableController tableController;
	private ReservationController reservationController;
	private DBOrderLine dBOrderLine;
	private Merchandise mer;
	private Order order;
	private DBOrder dbOrder;

	public OrderController() {
		dbOrder = new DBOrder();
		dBOrderLine = new DBOrderLine();
		merchandiseController = new MerchandiseController();
		staffController = new StaffController();
		tableController = new TableController();
		reservationController = new ReservationController();
	}

	public void makeOrder(String cprNo, boolean isActive)
			throws DatabaseException {
		Staff waiter = staffController.findWaiterByCpr(cprNo);
		order.setActive(isActive);
	}

	public ArrayList<Order> getAllOrders() {
		return dbOrder.getAllOrders();
	}

	public void insertOrder(Order order) {
		try {
			DBConnect.startTransaction();
			dbOrder.insertOrder(order);
			DBConnect.commitTransaction();
		} catch (DatabaseException e) {
			DBConnect.rollbackTransaction();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void findMerchandise(String name) {
		mer = merchandiseController.findMerchandise(name);
	}

	public void addMerchandises(double quantity) {
		if(merchandiseController.checkIfMerchandiseExistsInAL(mer, order)){
			for(OrderLine ol : order.getOrderLines()){
				if(ol.getMerchandise().getName().equals(mer.getName())){
					ol.setQuantity(ol.getQuantity() + 1);
				}
			}
		}
		else{
		OrderLine ol = new OrderLine();
		ol.setMerchandise(mer);
		ol.setQuantity(quantity);
		ol.setOrderId(order.getOrderId());
		order.addOrderLine(ol);
		}
	}

	public void saveOrder() throws DatabaseException {
		ArrayList<OrderLine> orderLines = order.getOrderLines();
		for (int x = 0; x < orderLines.size(); x++) {
			OrderLine orderL = orderLines.get(x);

			int olId = dBOrderLine.findOrderLineIdByMerchandiseIdAndOrderId(orderL.getMerchandise().
					getId(), order.getOrderId());
			System.out.println("Order Line Id: " + olId);
			if(olId == -1){
				dBOrderLine.insertOrderLine(orderL);
			}
			else{
				dBOrderLine.updateOrderLine(olId, orderL);
			}

		}
		/**
		try {
			DBConnect.startTransaction();
			dbOrder.insertOrder(order);
			DBConnect.commitTransaction();
		} catch (DatabaseException e) {
			DBConnect.rollbackTransaction();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	public ArrayList<OrderLine> compleateOrderLines(ArrayList<OrderLine> lines){
		ArrayList<OrderLine> realList = new ArrayList<OrderLine>();
		Merchandise me;
		for(OrderLine ol : lines){
			me = merchandiseController.findCorrectMerchandiseByMerchandiseId(ol.getMerchandise().getId());
			OrderLine oli = ol;
			oli.setMerchandise(me);
			realList.add(oli);
		}
		return realList;
	}

	public Order findOrder(int tableNo) {
		Reservation res = reservationController
				.findReservationByTableNo(tableNo);
		return res.getOrder();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Order findOrderById(int orderId) {
		try {
			if(order == null){
			order = dbOrder.findOrder(orderId);
			}
			else if(orderId != order.getOrderId()){
				order = dbOrder.findOrder(orderId);
			}
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}
	
	public int updateOrder(int id, Order o){
		int uO = dbOrder.updateOrder(id, o);
		for(OrderLine ol: o.getOrderLines()){
		try {
			dBOrderLine.insertOrderLine(ol);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return uO;
	}
	
	public Order findOrderByReservationId(int rId){
		return dbOrder.findOrderByReservationId(rId);
	}
	
	public ArrayList<OrderLine> getOrderLinesByOrderId(int oId){
		return dbOrder.getOrderLinesByOrderId(oId);
	}
	
	public void addOrderLinesToOrder(ArrayList<OrderLine> lines){
		order.setOrderLines(lines);
	}

}
