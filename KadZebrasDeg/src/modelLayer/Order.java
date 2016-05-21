package modelLayer;

import java.util.ArrayList;

public class Order {
private ArrayList<OrderLine> orderLines;
private Staff staff;
private Staff id;
private double totalPrice;
private boolean isPaid;
private boolean isActive;
private Reservation reservation;
private int orderId;



//constructor
public Order(){
	orderLines=new ArrayList<>();
}


//sets and gets

public ArrayList<OrderLine> getOrderLines() {return orderLines;}
public void setOrderLines(ArrayList<OrderLine> orderLines) {this.orderLines = orderLines;}
public void addOrderLine(OrderLine ol){orderLines.add(ol);}
public Staff getId() {return id;}
public void setId(Staff id) {this.id = id;}
public int getOrderId() {return orderId;}
public void setOrderId(int orderId) {this.orderId = orderId;}
public double getTotalPrice() {return totalPrice;}
public void setTotalPrice(double totalPrice) {this.totalPrice = totalPrice;}
public Staff getStaff() {return staff;}
public void setStaff(Staff staff) {this.staff = staff;}
public boolean isPaid() {return isPaid;}
public void setPaid(boolean isPaid) {this.isPaid = isPaid;}
public boolean isActive() {return isActive;}
public void setActive(boolean isActive) {this.isActive = isActive;}
public Reservation getReservation() {return reservation;}
public void setReservation(Reservation reservation) {this.reservation = reservation;}
//end of sets and gets
}
