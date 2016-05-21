package modelLayer;

public abstract class Merchandise {
private String name;
private float price;
private boolean exists;
private int id;
//constructor
public Merchandise(){
	
}

//sets
public void setName(String name){this.name=name;}
public void setPrice(float price){this.price=price;}
public void setExists(boolean exists){this.exists=exists;}
public void setId(int id){this.id=id;}
//gets
public String getName(){return name;}
public float getPrice(){return price;}
public boolean getExists(){return exists;}
public int getId() {	return id;}
	
	
}
