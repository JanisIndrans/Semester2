package modelLayer;

public class Miscellaneous extends Merchandise{
private int quantityInStock;
public int minQuantityInStock;
//constructor
public Miscellaneous(){
	
}


//sets
public void setQuantityInStock(int quantityInStock){ this.quantityInStock=quantityInStock;}
public void setMinQuantityInStock(int minQuantityInStock) {this.minQuantityInStock = minQuantityInStock;}


//gets
public int getQuantityInStock(){return quantityInStock;}
public int getMinQuantityInStock() {return minQuantityInStock;}
}
