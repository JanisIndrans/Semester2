package guiLayer;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import modelLayer.Course;
import modelLayer.Drink;
import modelLayer.Miscellaneous;
import modelLayer.OrderLine;


public class ChosenTableModel extends AbstractTableModel {
	private ArrayList<OrderLine> lines;
	
	public ChosenTableModel(ArrayList<OrderLine> list) {
		lines = list;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lines.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		OrderLine orderLine = lines.get(rowIndex);
		switch(columnIndex) {
		case 0:
			if(orderLine.getMerchandise() instanceof Course == true){
				return "Course";
			}
			if(orderLine.getMerchandise() instanceof Drink == true){
				return "Drink";
			}
			if(orderLine.getMerchandise() instanceof Miscellaneous == true){
				return "Miscellaneous";
			}
		case 1:
			return orderLine.getMerchandise().getName();
		case 2:
			return orderLine.getQuantity();
		case 3:
			return orderLine.getMerchandise().getPrice();
		default:
			return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		switch(columnIndex){
		
		case(0):
			return String.class;
		case(1):
			return String.class;
		case(2):
			return Integer.class;
		case(3):
			return String.class;
		default: 
			return null;	
		}
		
	}
	
	
	@Override
	public String getColumnName(int column) {
		switch(column){
		
		case(0):
			return "Type";
		case(1):
			return "Name";
		case(2):
			return "Qty";
		case(3) :
			return "Price";
		default : 
			return null;
		}
	
	}
	public void setOrderLines(ArrayList<OrderLine> lines){
		this.lines = lines;
	}

}
