package modelLayer;

public class Table {

private int noOfSeats;
private int tableNo;
private boolean isAvailable;
private boolean exists;
private int tableOnTheNorth;
private int tableOnTheEast;
private int tableOnTheSouth;
private int tableOnTheWest;
//constructor
public Table(){

}
//sets and gets
public int getNoOfSeats() {return noOfSeats;}
public void setNoOfSeats(int noOfSeats) {this.noOfSeats = noOfSeats;}
public int getTableNo() {return tableNo;}
public void setTableNo(int tableNo) {this.tableNo = tableNo;}
public boolean isAvailable() {return isAvailable;}
public void setAvailable(boolean isAvailable) {this.isAvailable = isAvailable;}
public boolean isExists() {return exists;}
public void setExists(boolean exists) {this.exists = exists;}
public int getTableOnTheNorth() {return tableOnTheNorth;}
public void setTableOnTheNorth(int tableOnTheNorth) {this.tableOnTheNorth = tableOnTheNorth;}
public int getTableOnTheEast() {return tableOnTheEast;}
public void setTableOnTheEast(int tableOnTheEast) {this.tableOnTheEast = tableOnTheEast;}
public int getTableOnTheSouth() {return tableOnTheSouth;}
public void setTableOnTheSouth(int tableOnTheSouth) {this.tableOnTheSouth = tableOnTheSouth;}
public int getTableOnTheWest() {return tableOnTheWest;}
public void setTableOnTheWest(int tableOnTheWest) {this.tableOnTheWest = tableOnTheWest;}
//end of sets and gets
}
