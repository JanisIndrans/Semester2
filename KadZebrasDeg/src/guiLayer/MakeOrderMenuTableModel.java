package guiLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import modelLayer.Course;
import modelLayer.Drink;
import modelLayer.Miscellaneous;
import modelLayer.OrderLine;

public class MakeOrderMenuTableModel extends AbstractTableModel {
	
	private ArrayList<OrderLine> ol;
	private String[] colNames = {"Name", "Type", "Type of course", "Quantity"};

	
	public MakeOrderMenuTableModel() {
		ol = new ArrayList<>();		
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return ol.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		OrderLine orderLine = ol.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return orderLine.getMerchandise().getName();
		case 1:
			if(orderLine.getMerchandise() instanceof Course == true){
				return "Course";
			}
			if(orderLine.getMerchandise() instanceof Drink == true){
				return "Drink";
			}
			if(orderLine.getMerchandise() instanceof Miscellaneous == true){
				return "Miscellaneous";
			}
			
		case 2:
			if(orderLine.getMerchandise() instanceof Course == true){ 
				return ((Course)orderLine.getMerchandise()).getTypeOfCourse();
				}
			else{
				return "N/A";
			}
		case 3:
			return orderLine.getQuantity();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}
	/**
	public void setDataGGG(){
		Order o = new Order();
		for(int i = 0; i < 5; i++){
		al.add(o);
		}
	}
	*/
	
	public Class<?> getColumnClass(int col) {
		switch(col) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return Integer.class;
		default:
			return null;
		}
	}
	public void setOrderList(ArrayList<OrderLine>  ol){
		System.out.println("i am in serOrderList");
		for (OrderLine o: ol){
			System.out.println("lETS SEE THE NAME FOR LERCKJSADJH: "+o.getMerchandise().getName());
		}
		this.ol = ol;
		fireTableDataChanged();
	}
}
