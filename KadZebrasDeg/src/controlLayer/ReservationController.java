package controlLayer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import modelLayer.Order;
import modelLayer.Reservation;
import modelLayer.Table;
import dbLayer.DBConnect;
import dbLayer.DBOrder;
import dbLayer.DBReservation;
import dbLayer.DBTable;
import exceptionsLayer.DatabaseException;

public class ReservationController {
	private DBReservation dBReservation;
	private TableController tableController;
	private ArrayList<Table> chosenTables;
	private ArrayList<Table> tablesToSuggest;
	private DBTable dbTable;
	private DBOrder dBOrder;
	private boolean isPossible;
	private int startingPointForUI;
	//ArrayList<Table> tables;

	public ReservationController() {
		dbTable = new DBTable();
		dBOrder = new DBOrder();
		dBReservation = new DBReservation();
		tableController = new TableController();
		chosenTables = new ArrayList<>();
		setPossible(true);
		startingPointForUI = 0;
		tablesToSuggest = new ArrayList<Table>();
		
	}

	public Reservation makeReservation(String customersName, String phoneNo,
			String reservationDate, String reservedTime, int numberOfGuests) throws DatabaseException {
		Reservation res = new Reservation();
		res.setCustomerName(customersName);
		res.setPhoneNo(phoneNo);
		res.setNumberOfGuests(numberOfGuests);
		res.setReservationDate(reservationDate);
		res.setReservedTime(reservedTime);
		Order o = new Order();
		o.setActive(true);
		res.setOrder(o);
		for (Table t : tablesToSuggest) {
			System.out.println("Table on the suggested list when making reservation: " + t.getTableNo());
			// you add here the tables to the
										// reservation
			// can be improved to popup some error or smth
			// TO BE IMPROVED
			//Table tbl = checkTables(t.getTableNo());
			
			//if (tbl != null)
			System.out.println("Table getting reserved: " + t.getTableNo());
				t.setAvailable(false);
				res.addTable(t.getTableNo());
			
				tableController.updateTable(t.getTableNo(), t);
		}
		if (confirmReservation(res) != -1){
			tablesToSuggest = new ArrayList<Table>();
			return res;
		}
		else{
			System.out.println("Success!");
		}
		// Just for test
		for (Table t : tablesToSuggest) {
			System.out.println("Table in arrayList : " + t.getTableNo());
		}
		return res;
	}
	/**
	public Table checkTables(int tableNo) throws DatabaseException {
		Table tbl;
		tbl = tableController.findTableByNo(tableNo);
		if (tableController.checkIfExists(tbl))
			if (tableController.checkIfAvialable(tbl))
				return tbl;
		return null;
	}
	public int getReservationId(int tableNo){
		return dBReservation.getReservationId(tableNo);
	}
*/
	public int confirmReservation(Reservation res) throws DatabaseException {
		try {
			DBConnect.startTransaction();
			
			int oId = dBOrder.insertOrder(res.getOrder());
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> " + oId);
			res.getOrder().setOrderId(oId);
			dBReservation.insertReservation(res);
			DBConnect.commitTransaction();
			return 1;
		} catch (Exception e) {
			DBConnect.rollbackTransaction();
		}
		return -1;
	}

	public Reservation findReservationByName(String name)
			throws DatabaseException {
		Reservation res = null;
		res = dBReservation.findReservationByName(name);
		return res;
	}

	// Returns all reservations with specified table no
	public Reservation findReservationByTableNo(int tableNo) {
		ArrayList<Reservation> aLR = dBReservation
				.findReservationByTableNo(tableNo);
		boolean isFound = false;
		Reservation res = null;
		int i = 0;
		if (aLR.isEmpty() == false) {
			while (!isFound) {
				res = aLR.get(i);
				if (res.getOrder().isActive() == true) {
					isFound = true;
				} else {
					i++;
				}
			}
		}
		return res;
	}

	public void addTableToReservation(Table table) {
		chosenTables.add(table);
		dbTable.updateTable(chosenTables.get(chosenTables.size() - 1)
				.getTableNo(), chosenTables.get(chosenTables.size() - 1));
	}

	/**
	 * Code I added public ArrayList
	 * <Table>
	 * getChosenTables(){ return chosenTables; } public void
	 * removeTableFromChosenTables(Table table){ chosenTables.remove(table); }
	 * public boolean checkIfTableHasBeenAlreadyAdded(int table){ for(Table t :
	 * chosenTables){ if(t.getTableNo() == table){ return true; } } return
	 * false; }
	 */
	
	//Moved from UI
	/**
	private void checkTables(int tableNo, int numberOfChairsNeeded){
		
		
		List<Integer> neighbors = new LinkedList<>();
		neighbors.add(tableController.getAllTables().get(tableNo)
				.getTableOnTheEast());
		neighbors.add(tableController.getAllTables().get(tableNo)
				.getTableOnTheNorth());
		neighbors.add(tableController.getAllTables().get(tableNo)
				.getTableOnTheSouth());
		neighbors.add(tableController.getAllTables().get(tableNo)
				.getTableOnTheWest());
				
		
		
		for(int i=0;i<neighbors.size();i++){
			
		if(numberOfChairsNeeded>0 && i>0){
			if(!checkIfReservationPossible(i)){
				setPossible(false);
				return;
			}
			if(!checkIfAllreadySuggested(i)){
			
		if (tableController.getAllTables().get(i).isAvailable()) {
			
			
			
			//System.out.println("First Table: NumberOfChairs: " + numberOfChairsNeeded);
			
			
			numberOfChairsNeeded -= tableController.getAllTables()
					.get(i).getNoOfSeats();
			
			
			//System.out.println("First Table: NumberOfChairs after: " + numberOfChairsNeeded
					//+ "NoOfSeats: "
					//+ tableController.getAllTables().get(i).getNoOfSeats());
			
			
			
			Table table = tableController.getAllTables().get(i);
			table.setTableNo(i+1);
			table.setExists(true);
			//System.out.println("Number of table: " + (i+1));
			
			/**after suggestion
			table.setAvailable(false);
			
			//need to check this
			tablesToSuggest.add(table);
			
			}
			//addTableToReservation(table);
			}
			
			//Not sure yet
			if (numberOfChairsNeeded > 0) {
				System.out.println("More tables needed!!!");
				neighbors = new LinkedList<>();
				neighbors.add(tableController.getAllTables().get(i)
						.getTableOnTheEast());
				neighbors.add(tableController.getAllTables().get(i)
						.getTableOnTheNorth());
				neighbors.add(tableController.getAllTables().get(i)
						.getTableOnTheSouth());
				neighbors.add(tableController.getAllTables().get(i)
						.getTableOnTheWest());
			}
		
		}
	}
	}
	*/
	/**
	public void createOrderAndAddTables(int noOfSeats) {
		//
		//tables = new ArrayList<>();
		int numberOfGuests = 0;
		numberOfGuests = noOfSeats;
		int numberOfChairsNeeded = numberOfGuests;
		int i = 0;
		while (!tableController.getAllTables().get(i).isAvailable()) {
			i++;
		}
		if (i < tableController.getAllTables().size()) {
			Table table = tableController.getAllTables().get(i);
			
			
			
			table.setTableNo(i+1);
			
			after suggestion
			
			table.setExists(true);
			table.setAvailable(false);
			
			
			//Need to check this
			tablesToSuggest.add(table);
			
			
			//System.out.println("TableEast: "+table.getTableOnTheEast());
			//addTableToReservation(table);
			
			//System.out.println("First Table: NumberOfChairs: " + numberOfChairsNeeded);
			numberOfChairsNeeded -= tableController.getAllTables().get(i)
					.getNoOfSeats();
			//System.out.println("First Table: NumberOfChairs after: " + numberOfChairsNeeded
				//	+ "NoOfSeats: "
				//	+ tableController.getAllTables().get(i).getNoOfSeats() + "TableNo: "+(i+1));
			if (numberOfChairsNeeded > 0) {
				System.out.println("First table: We do not have enought seats in table: "
						+ (i));
				checkTables(i, numberOfChairsNeeded);

			}
		}
		*/
			/** don't know yet
		Order order = new Order();
		order.setOrderId(orderController.getAllOrders().size());
		order.setActive(true);
		//order.setId(id);
		order.setTotalPrice(0);
			*/
		
		
		/**Should be placed in UI
	

			rc.makeReservation(nameTextField.getText(), phoneNoTextField
					.getText(), days.getSelectedItem().toString() + "_"
					+ monthsYear.getSelectedItem().toString(), time
					.getSelectedItem().toString(), Integer
					.parseInt(noOfSeatsTextField.getText()), order);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	/**
	//added int index
	public void suggestTables(int noOfSeats, int index){

		tablesToSuggest = new ArrayList<>();
		int numberOfGuests = 0;
		numberOfGuests = noOfSeats;
		int numberOfChairsNeeded = numberOfGuests;
		
		//changed from 0 to index
		int i = index;
		if(findSuitableTableAccordingToSeats(noOfSeats) != null){
			tablesToSuggest.add(findSuitableTableAccordingToSeats(noOfSeats));
			return;
		}
		while (tableController.getAllTables().size() > i && !tableController.getAllTables().get(i).isAvailable()) {
			i++;
		}
		
		if (i < tableController.getAllTables().size()) {
			Table table = tableController.getAllTables().get(i);
			table.setTableNo(i + 1);
			tablesToSuggest.add(table);
			numberOfChairsNeeded -= tableController.getAllTables().get(i)
					.getNoOfSeats();
			if (numberOfChairsNeeded > 0) {
				checkTables(i, numberOfChairsNeeded);

			}
		
		}
			
	}
	*/
	public ArrayList<Table> getSuggestedTables(){
		return tablesToSuggest;
	}
	
	public boolean checkIfAllreadySuggested(int i){
		for(Table t : tablesToSuggest){
			if(t.getTableNo() == tableController.getAllTables().get(i).getTableNo()){
				return true;
			}
		}
		return false;
	}
	
	public Table findSuitableTableAccordingToSeats(int numberOfGuests){
		int i = 0;
		boolean found = false;
		while(tableController.getAllTables().size() > i && !found){
			if(tableController.getAllTables().get(i).getNoOfSeats() == numberOfGuests && tableController.getAllTables().get(i).isAvailable()){
				found = true;
				System.out.println("Found table: " + tableController.getAllTables().get(i).getTableNo());
				return tableController.getAllTables().get(i);
			}
			else{
				i++;
			}
		}
		return null;
	}
	
	public boolean checkIfReservationPossible(int i){
		if(tableController.getAllTables().size() > i){
			return true;
		}
		return false;
	}

	public boolean isPossible() {
		return isPossible;
	}

	public void setPossible(boolean isPossible) {
		this.isPossible = isPossible;
	}
	
	
	public boolean checkIfAvailable(int t){
		Table table = tableController.getAllTables().get(t -1);
		if(table.isAvailable()){
			return true;
		}
		return false;
	}
	
	//get all neighbors of an object
	public ArrayList<Table> returnAllNeighbors(Table t){
		ArrayList<Table> neighbors = new ArrayList<Table>();
		
		if(t.getTableOnTheEast() != 0){
			neighbors.add(tableController.getAllTables().get(t.getTableOnTheEast() -1));
		}
		else{
			Table table = new Table();
			table.setAvailable(false);
			neighbors.add(table);
		}
		if(t.getTableOnTheWest() != 0){
		neighbors.add(tableController.getAllTables().get(t.getTableOnTheWest() -1));
		}
		else{
			Table table = new Table();
			table.setAvailable(false);
			neighbors.add(table);
		}
		if(t.getTableOnTheSouth() != 0){
		neighbors.add(tableController.getAllTables().get(t.getTableOnTheSouth() -1));
		}
		else{
			Table table = new Table();
			table.setAvailable(false);
			neighbors.add(table);
		}

		if(t.getTableOnTheNorth() != 0){
		neighbors.add(tableController.getAllTables().get(t.getTableOnTheNorth() -1));
		}
		else{
			Table table = new Table();
			table.setAvailable(false);
			neighbors.add(table);
		}
		return neighbors;
	}
	
	
	public Table findFirstAvailableTable(int startingPoint){
		int i = startingPoint;
		while (tableController.getAllTables().size() > i && !tableController.getAllTables().get(i).isAvailable()) {
			i++;
		}
		startingPointForUI = i;
		System.out.println("startingPointForUI" + startingPointForUI);
		
		return tableController.getAllTables().get(i);
	}
			
	public int getAmountOfSeatsInTheSuggestedTables(){
		int numberOfSeats = 0;
		for(Table t: tablesToSuggest){
			System.out.println("Lets see how many tables are there???? : " + t.getTableNo());
			numberOfSeats = numberOfSeats + t.getNoOfSeats();
		}
		return numberOfSeats;
	}
	
	public void getTheTables(int numberOfVisitors, int startingPoint){
		tablesToSuggest = new ArrayList<Table>();
		boolean done = false;
		Table table = tableController.getAllTables().get(findFirstAvailableTable(startingPoint).getTableNo() -1);
		if(findTheBestSuitableSingleTable(numberOfVisitors) != -1){
			tablesToSuggest.add(tableController.getAllTables().get(findTheBestSuitableSingleTable(numberOfVisitors) -1));
			done = true; }
		if(!done && findFirstTableWithConnection(startingPoint) != null){
			table = findFirstTableWithConnection(startingPoint); }
		else{
			done = true; }
		while(!done){
			if(!table.isExists()){
				tablesToSuggest = new ArrayList<Table>();
				done = true; }
			else{
			if(!checkListForExisting(table)){
				tablesToSuggest.add(table);
			}
		if(getAmountOfSeatsInTheSuggestedTables() < numberOfVisitors){
			if(tableHasAvailableNeighbors(table)){
			table = findNextTable(tablesToSuggest.get(tablesToSuggest.size() -1)); }
			else{
				if((startingPoint + 1) < tablesToSuggest.size()){
				table = tableController.getAllTables().get(findFirstAvailableTable(startingPoint + 1).getTableNo() -1); }
				else{
					tablesToSuggest = new ArrayList<Table>();
					table = tableController.getAllTables().get(findFirstAvailableTable(0).getTableNo() -1);
					tablesToSuggest.add(table); }
			}
		}
		else{
			done = true; }
			}
		if(table == null){
			table = tableController.getAllTables().get(findFirstAvailableTable(startingPoint).getTableNo() -1); }
		}
	}
	
	public Table findNextTable(Table table){
		Table repeatingTable = null;
		int counterOfRepeats = 0;
		Table anotherTable = null;
		ArrayList<Table> all = returnAllNeighbors(table);
		boolean done = false;
		int goBack = 1;
		while(!done){
			if(!done && tablesToSuggest.size() - goBack == -1){
				done = true; }
			if(!done){
		for(Table t: all){
			if(!done && t.isAvailable() && !checkListForExisting(t)){
				anotherTable = t;
				System.out.println("lets see which table adds: " +  t.getTableNo());
				done = true; } }
			}
		//goes here if reaches dead end
		if(!done && tablesToSuggest.size() - (goBack +1) != -1){
			all = returnAllNeighbors(tablesToSuggest.get(tablesToSuggest.size() - (goBack +1)));
			goBack++; }
		else{
			if(repeatingTable == null){
				repeatingTable = table; }
			if(repeatingTable.getTableNo() != table.getTableNo()){
				repeatingTable = table; }
			if(repeatingTable.getTableNo() == table.getTableNo()){
				counterOfRepeats ++; }
			if(counterOfRepeats == 5){
				Table t = new Table();
				t.setExists(false);
				System.out.println("Repeated 5 times");
				return t; }
		}
		}
		return anotherTable;
	}
	
	public boolean checkListForExisting(Table table){
		for(Table t: tablesToSuggest){
			if(t.getTableNo() == table.getTableNo()){
				return true;
			}
		}
		return false;
	}

	public int getStartingPointForUI() {
		return startingPointForUI;
	}

	public void setStartingPointForUI(int startingPointForUI) {
		this.startingPointForUI = startingPointForUI;
	}
	
	public boolean tableHasAvailableNeighbors(Table table){
		for(Table t: returnAllNeighbors(table)){
			if(t.isAvailable()){
				return true;
			}
		}
		
		return false;
	}
	
	public int findTheBestSuitableSingleTable(int numberOfSeats){
		ArrayList<Table> all = new ArrayList<Table>();
		Table tabel = null;
		for(Table t: tableController.getAllExistingTables()){
			if(t.isAvailable()){
			if(t.getNoOfSeats() >= numberOfSeats){
				all.add(t);
			}
			}
		}
		if(!all.isEmpty()){
			if(all.size() > 1){
				for(Table tbl : all){
					if(tabel == null){
						tabel = tbl;
					}
					else if(tabel.getNoOfSeats() > tbl.getNoOfSeats()){
						tabel = tbl;
					}
				}
			}
			else {
				return all.get(0).getTableNo();
			}
			return tabel.getTableNo();
		}
		return -1;
	}
	public boolean compareTwoListsIfIdentical(ArrayList<Table> list){
		int checkHowManySame = 0;
		for(Table t :tablesToSuggest){
			for(Table tbl : list){
				if(t.getTableNo() == tbl.getTableNo()){
					checkHowManySame++;
				}
			}
		}
		if(checkHowManySame == tablesToSuggest.size()){
			return true;
		}
		return false;
	}
	
	public Table findFirstTableWithConnection(int startingPoint){
		ArrayList<Table> tables = new ArrayList<Table>();
		Table chosenTable = null;
		boolean done = false;
		int i = startingPoint;
		while(!done && tableController.getAllExistingTables().size() > i){
			tables = returnAllNeighbors(tableController.getAllExistingTables().get(i));
			if(tableController.getAllExistingTables().get(i).isAvailable()){
			for(Table t : tables){
				if(t.isAvailable()){
					done = true;
					return tableController.getAllExistingTables().get(i);
				}
			}
			i++;
		}
			else{
				i++;
			}
		}
		
		return chosenTable;
	}
	
	public int findReservationIdByOrderId(int oId){
		int id = dBReservation.findReservationIdByOrderId(oId);
		return id;
	}
	public int findReservationIdByTableId(int tId){
		return dBReservation.findReservationIdByTableId(tId);
	}
	public Reservation findReservationById(int id){
		Reservation res = null;
		try {
			res = dBReservation.findReservation(id);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public int findReservationIdByCustomersName(String customerName){
		return dBReservation.findReservationIdByCustomersName(customerName);
	}
	
	public String findNameOnTheReservationByReservationId(int rId){
		return dBReservation.findNameOnTheReservationByReservationId(rId);
	}

}
