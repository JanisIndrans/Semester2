package guiLayer;
import java.util.EventObject;


public class AnyEvent extends EventObject {
	private String buttonTrigered;
	
	//Added by Janis
	//Some more fields for reservation
	private int numberOfSeats;
	private String name;
	private String phoneNo;
	private String monthsYear;
	private String days;
	private String time;
	private int tableNo;
	private String typeOfMerchandise;
	private String typeOfCourse;
	private boolean isVegetarian;
	private String ingredients;
	private float AlcoholConcentration;
	private int quantity;
	private float price;
	private String cprNo;
	private int tableN;
	private int tableE;
	private int tableS;
	private int tableW;
	
	public String getTypeOfMerchandise() {
		return typeOfMerchandise;
	}

	public void setTypeOfMerchandise(String typeOfMerchandise) {
		this.typeOfMerchandise = typeOfMerchandise;
	}

	public String getTypeOfCourse() {
		return typeOfCourse;
	}

	public void setTypeOfCourse(String typeOfCourse) {
		this.typeOfCourse = typeOfCourse;
	}

	public boolean isVegetarian() {
		return isVegetarian;
	}

	public void setVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public float getAlcoholConcentration() {
		return AlcoholConcentration;
	}

	public void setAlcoholConcentration(float alcoholConcentration) {
		AlcoholConcentration = alcoholConcentration;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public AnyEvent(Object source){
		super(source);
	}
	
	public AnyEvent(Object source, String buttonTrigered){
		super(source);
		this.buttonTrigered = buttonTrigered;
	}

	public String getButtonTrigered() {
		return buttonTrigered;
	}

	public void setButtonTrigered(String buttonTrigered) {
		this.buttonTrigered = buttonTrigered;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonthsYear() {
		return monthsYear;
	}

	public void setMonthsYear(String monthsYear) {
		this.monthsYear = monthsYear;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCprNo() {
		return cprNo;
	}

	public void setCprNo(String cprNo) {
		this.cprNo = cprNo;
	}

	public int getTableN() {
		return tableN;
	}

	public void setTableN(int tableN) {
		this.tableN = tableN;
	}

	public int getTableE() {
		return tableE;
	}

	public void setTableE(int tableE) {
		this.tableE = tableE;
	}

	public int getTableS() {
		return tableS;
	}

	public void setTableS(int tableS) {
		this.tableS = tableS;
	}

	public int getTableW() {
		return tableW;
	}

	public void setTableW(int tableW) {
		this.tableW = tableW;
	}
}
