package guiLayer;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import modelLayer.Merchandise;
import exceptionsLayer.DatabaseException;


public class MerchandiseMenuRightPanel extends JPanel {

	private JComboBox type;
	private JComboBox typeOfCourse;
	private JLabel type1;
	private JLabel typeOfCourse2;
	private JCheckBox isVegeterian;
	private JLabel name;
	private JComboBox name1;
	private JLabel ingridients;
	private JScrollPane ingridients1;
	private JTextArea ingridients2;
	private JLabel alcoholConcentration;
	private JTextField alcoholConcentration1;
	private JLabel quantity;
	private JTextField quantity1;
	private JButton updateBtn;
	private JButton deleteBtn;
	private ListenerForEverything listenerForEverything;
	private ArrayList<Merchandise> merchandise;
	private JTextField priceTF;
	private JLabel priceLbl;
	
	
	public MerchandiseMenuRightPanel() {
		merchandise = new ArrayList<>();
		Border innerBorder = BorderFactory.createTitledBorder("Update and Delete Merchandise");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		setPreferredSize(new Dimension(420,0));
		
		String[] typeOfMerchandise = {"Course", "Drink", "Miscellaneous"};
		type = new JComboBox(typeOfMerchandise);
		type1 = new JLabel("Type : ");
		String[] typeOfCourse1 = {"1st", "2nd", "Dessert"};
		typeOfCourse = new JComboBox(typeOfCourse1);
		typeOfCourse2 = new JLabel("Type of course : ");
		isVegeterian = new JCheckBox(new String("Vegetarian"));
		name = new JLabel("Name : ");
		String[] names= {};
		
		name1 = new JComboBox();
		ingridients = new JLabel("Ingridients : ");
		ingridients2 = new JTextArea(10,10);
		ingridients2.setLineWrap(true);
		ingridients1 = new JScrollPane(ingridients2);
		alcoholConcentration = new JLabel("Alcohol Concentration : ");
		alcoholConcentration1 = new JTextField(10);
		alcoholConcentration1.setText("0.0");
		quantity = new JLabel("Quantity : ");
		quantity1 = new JTextField(10);
		quantity1.setText("1");
		updateBtn = new JButton("Update");
		deleteBtn = new JButton("Delete");
		deleteBtn.setEnabled(false);
		priceLbl = new JLabel("Price: ");
		priceTF = new JTextField(10);
		
		updateBtn.setPreferredSize(new Dimension(150, 25));
		deleteBtn.setPreferredSize(new Dimension(150, 25));
		
		alcoholConcentration1.setEnabled(false);
		quantity1.setText("1");
		quantity1.setEnabled(false);
		
		name1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		type.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(type.getSelectedItem().equals("Drink")){
					ingridients2.setEnabled(false);
					typeOfCourse.setEnabled(false);
					isVegeterian.setEnabled(false);
					quantity1.setEnabled(true);
				}
				if(type.getSelectedItem().equals("Miscellaneous")){
					ingridients2.setEnabled(false);
					typeOfCourse.setEnabled(false);
					isVegeterian.setEnabled(false);
					alcoholConcentration1.setEnabled(false);
					quantity1.setEnabled(true);
				}
				if(type.getSelectedItem().equals("Course")){
					ingridients2.setEnabled(true);
					typeOfCourse.setEnabled(true);
					isVegeterian.setEnabled(true);
					alcoholConcentration1.setEnabled(false);
					quantity1.setText("1");
					quantity1.setEnabled(false);
				}
				
				
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "deleteBtn");
				if (listenerForEverything != null) {
					try {
						listenerForEverything.AnyEventOcurred(anyEvent);
					} catch (DatabaseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		updateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "updateBtn");
				anyEvent.setPrice(Float.parseFloat(priceTF.getText()));
				anyEvent.setTypeOfMerchandise(type.getSelectedItem().toString());
				anyEvent.setTypeOfCourse(typeOfCourse.getSelectedItem().toString());
				anyEvent.setVegetarian(isVegeterian.isSelected());
				anyEvent.setName(name1.getSelectedItem().toString());
				anyEvent.setIngredients(ingridients2.getText());
				anyEvent.setAlcoholConcentration(Float.parseFloat(alcoholConcentration1.getText()));
				anyEvent.setQuantity(Integer.parseInt(quantity1.getText()));
				if (listenerForEverything != null) {
					try {
						listenerForEverything.AnyEventOcurred(anyEvent);
					} catch (DatabaseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		
		// First row first column
				gc.weightx = 0.1; // Size of of the cell width
				gc.weighty = 1;  // Size of of the cell height
				
				gc.gridwidth = 1; // How many cells are merged 
				
				gc.gridx = 0; // Positiion on x
				gc.gridy = 0; // Position on y
				
				gc.fill = GridBagConstraints.WEST;
				gc.anchor = GridBagConstraints.EAST;
				gc.insets = new Insets(0, 0, 0, 0);
				add(name, gc);
				
				// First row second column
				gc.weightx = 1; // Size of of the cell width
				gc.weighty = 1;  // Size of of the cell height

				gc.gridwidth = 1; // How many cells are merged 

				gc.gridx = 1; // Positiion on x
				gc.gridy = 0; // Position on y

				gc.fill = GridBagConstraints.EAST;
				gc.anchor = GridBagConstraints.WEST;
				gc.insets = new Insets(0, 10, 0, 0);
				add(name1, gc);
							
				// Second row first column
				gc.weightx = 0.1; 
				gc.weighty = 1;  

				gc.gridwidth = 1;  

				gc.gridx = 0; // Positiion on x
				gc.gridy = 1; // Position on y

				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.EAST;
				gc.insets = new Insets(0, 0, 0, 0);
				add(type1, gc);

				// Second row second column 
				gc.weightx = 1; 
				gc.weighty = 1;  

				gc.gridwidth = 1;  

				gc.gridx = 1; // Positiion on x
				gc.gridy = 1; // Position on y

				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.WEST;
				gc.insets = new Insets(0, 10, 0, 0);
				add(type, gc);

				

				//third row first column
				gc.weightx = 0.1; 
				gc.weighty = 1;  
				
				gc.gridwidth = 1;  
				
				gc.gridx = 0; // Positiion on x
				gc.gridy = 2; // Position on y
				
				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.EAST;
				gc.insets = new Insets(0, 0, 0, 0);
				add(typeOfCourse2, gc);
				
				//third row and second column
				gc.weightx = 1; 
				gc.weighty = 1;  
				
				gc.gridwidth = 1;  
				
				gc.gridx = 1; // Positiion on x
				gc.gridy = 2; // Position on y
				
				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.WEST;
				gc.insets = new Insets(0, 10, 0, 0);
				add(typeOfCourse, gc);
				
				
				
				// fourth row and second column
				gc.weightx = 1; 
				gc.weighty = 1;  
				
				gc.gridwidth = 1;  
				
				gc.gridx = 1; // Positiion on x
				gc.gridy = 3; // Position on y
				
				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.WEST;
				gc.insets = new Insets(0, 0, 0, 0);
				add(isVegeterian, gc);
				
				//fifth row and first column 
				gc.weightx = 0.1; 
				gc.weighty = 1;  
				
				gc.gridwidth = 1;  
				
				gc.gridx = 0; // Positiion on x
				gc.gridy = 4; // Position on y
				
				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.EAST;
				gc.insets = new Insets(0, 0, 0, 0);
				add(ingridients, gc);
				
				//fifth row and second column 
				gc.weightx = 1; 
				gc.weighty = 1;  
				
				gc.gridwidth = 1;  
				
				gc.gridx = 1; // Positiion on x
				gc.gridy = 4; // Position on y
				
				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.WEST;
				gc.insets = new Insets(0, 10, 0, 0);
				add(ingridients1, gc);
				
				// sixth row and first column 
				gc.weightx = 0.1; 
				gc.weighty = 1;  
				
				gc.gridwidth = 1;  
				
				gc.gridx = 0; // Positiion on x
				gc.gridy = 5; // Position on y
				
				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.EAST;
				gc.insets = new Insets(0, 0, 0, 0);
				add(alcoholConcentration, gc);
				
				//sixth row and second column 
				gc.weightx = 1; 
				gc.weighty = 1;  
				
				gc.gridwidth = 1;  
				
				gc.gridx = 1; // Positiion on x
				gc.gridy = 5; // Position on y
				
				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.WEST;
				gc.insets = new Insets(0, 10, 0, 0);
				add(alcoholConcentration1, gc);
				
				// seventh row and first column 
				gc.weightx = 0.1; 
				gc.weighty = 1;  
				
				gc.gridwidth = 1;  
				
				gc.gridx = 0; // Positiion on x
				gc.gridy = 6; // Position on y
				
				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.EAST;
				gc.insets = new Insets(0, 0, 0, 0);
				add(quantity, gc);
				
				// seventh row and second column 
				gc.weightx = 1; 
				gc.weighty = 1;  
				
				gc.gridwidth = 1;  
				
				gc.gridx = 1; // Positiion on x
				gc.gridy = 6; // Position on y
				
				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.WEST;
				gc.insets = new Insets(0, 10, 0, 0);
				add(quantity1, gc);
				
				// Eighth row and first column
				gc.weightx = 0.1;
				gc.weighty = 1;

				gc.gridwidth = 1;

				gc.gridx = 0; // Positiion on x
				gc.gridy = 7; // Position on y

				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.EAST;
				gc.insets = new Insets(0, 0, 0, 0);
				add(priceLbl, gc);

				// Eighth row and second column
				gc.weightx = 1;
				gc.weighty = 1;

				gc.gridwidth = 1;

				gc.gridx = 1; // Positiion on x
				gc.gridy = 7; // Position on y

				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.WEST;
				gc.insets = new Insets(0, 10, 0, 0);
				add(priceTF, gc);
				
				// ninth row first column 
				gc.weightx = 1; 
				gc.weighty = 1;  
				
				gc.gridwidth = 2;  
				
				gc.gridx = 0; // Positiion on x
				gc.gridy = 8; // Position on y
				
				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.SOUTH;
				gc.insets = new Insets(0, 0, 0, 0);
				add(updateBtn, gc);
				
				// ninth row second column 
				gc.weightx = 1; 
				gc.weighty = 1;  
				
				gc.gridwidth = 2;  
				
				gc.gridx = 0; // Positiion on x
				gc.gridy = 9; // Position on y
				
				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.NORTH;
				gc.insets = new Insets(5, 0, 0, 0);
				add(deleteBtn, gc);
	}


	public void setListenerForEverything(ListenerForEverything listener){
		this.listenerForEverything = listener;
	}
	public void populateMerchandise(ArrayList<Merchandise> m){
		merchandise = m;
		DefaultComboBoxModel name = new DefaultComboBoxModel<>();
		for(Merchandise me : m){
			name.addElement(me.getName());
		}
		name1.setModel(name);
	}
	
	
	
}
