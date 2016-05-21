package modelLayer;

public class OrderLine {
private Merchandise merchandise;
private double quantity;
private boolean isDone;
private int orderId;

//constructor
public OrderLine(){
	
}

//sets
public void setOrderId(int orderId) {this.orderId = orderId;}
public void setMerchandise(Merchandise merchandise){this.merchandise=merchandise;}
public void setQuantity(double quantity){this.quantity=quantity;}
public void setDone(boolean isDone) {this.isDone = isDone;}
//gets
public Merchandise getMerchandise(){return merchandise;}
public double getQuantity(){return quantity;}
public boolean isDone() {return isDone;}
public int getOrderId() {return orderId;}
}
