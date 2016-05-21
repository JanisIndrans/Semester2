package guiLayer;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import modelLayer.Staff;
import controlLayer.StaffController;
import exceptionsLayer.DatabaseException;


public class StaffUIRightPanel extends JPanel {
	private JComboBox name2;
	private JComboBox typeOfStaff;
	private JComboBox exists2;
	private Label profession;
	private Label name;
	private Label bankAccount;
	private Label adress;
	private Label phone ;
	private Label exists;
	private JTextField bankAccount1;
	private JTextField adress1;
	private JTextField phone1;
	private JButton delete;
	private JButton update;
	private ListenerForEverything listenerForEverything;
	private StaffController sc;
	private JLabel cprNoLbl;
	private JTextField cprNoTF;
	
	
	 public StaffUIRightPanel(){
		 sc = new StaffController();
	        //setPreferredSize(new Dimension(400,0));
	        
			
			Border innerBorder = BorderFactory.createTitledBorder("Update staff");
			Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
			setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
			
			setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			
			

			profession = new Label("Profession : ");
			name = new Label("Name : ");
			bankAccount = new Label("Bank Account : ");
			adress = new Label("Adress : ");
			phone = new Label("Phone : ");
			exists = new Label("Exists : ");
			cprNoLbl = new JLabel("CPR No: ");
			cprNoTF = new JTextField(10);
			cprNoTF.setVisible(false);
			cprNoLbl.setVisible(false);
			
			String[] names = new String[sc.getAllStaff().size()];
			for(int i=0;i<sc.getAllStaff().size();i++){
				names[i]=sc.getAllStaff().get(i).getName();
			}
			name2 = new JComboBox(names);
			String[] staffType = {"Waiter", "Cook", "Manager"};
			typeOfStaff = new JComboBox(staffType);
			String[] existsOrNot = {"Yes", "No"};
			exists2 = new JComboBox(existsOrNot);
			bankAccount1 = new JTextField(10);
			adress1 = new JTextField(10);
			phone1 = new JTextField(10);
			delete = new JButton("Delete");
			delete.setEnabled(false);
            update = new JButton("Update");
            delete.setPreferredSize(new Dimension(150, 25));
            update.setPreferredSize(new Dimension(150, 25));
            
            
            delete.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					AnyEvent anyEvent = new AnyEvent(this,  "delete");
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
            
            update.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "update");
    				if (listenerForEverything != null) {
    					try {
							listenerForEverything.AnyEventOcurred(anyEvent);
							if(!bankAccount1.getText().equals("") && !adress1.getText().equals("") && !phone1.getText().equals("")){
								Staff staff = sc.findStaffByName(name2.getSelectedItem().toString());
								staff.setCprNo(cprNoTF.getText());
								staff.setName(name2.getSelectedItem().toString());
								staff.setAddress(adress1.getText());
								staff.setPhoneNo(phone1.getText());
								staff.setProfession(typeOfStaff.getSelectedItem().toString());
								staff.setBankAccount(bankAccount1.getText());
								staff.setCprNo("123");
								if(exists2.getSelectedItem().toString().equals("Yes")){
									staff.setExists(true);
								}else
								{
									staff.setExists(false);
								}
								sc.updateStaff(staff.getCprNo(), staff);
								bankAccount1.setText("");
								adress1.setText("");
								phone1.setText("");
							}
						} catch (DatabaseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
    				}
					
				}
			});
           
			
			// First row first column
			gc.weightx = 1; // Size of of the cell width
			gc.weighty = 0.2;  // Size of of the cell height
			
			gc.gridwidth = 1; // How many cells are merged 
			
			gc.gridx = 0; // Positiion on x
			gc.gridy = 0; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.LINE_END;
			gc.insets = new Insets(0, 0, 0, 0);
			add(name, gc);
			
			// First row second column
			gc.weightx = 1; // Size of of the cell width
			gc.weighty = 0.2;  // Size of of the cell height
			
			gc.gridwidth = 1; // How many cells are merged 
			
			gc.gridx = 1; // Positiion on x
			gc.gridy = 0; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.LINE_START;
			gc.insets = new Insets(0, 0, 0, 0);
			add(name2, gc);
			
			// Second row first column
			gc.weightx = 1; 
			gc.weighty = 0.2;  
			
			gc.gridwidth = 1;  
			
			gc.gridx = 0; // Positiion on x
			gc.gridy = 1; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.LINE_END;
			gc.insets = new Insets(0, 0, 0, 0);
			add(profession, gc);
			
			// Second row second column 
			gc.weightx = 1; 
			gc.weighty = 0.2;  
			
			gc.gridwidth = 1;  
			
			gc.gridx = 1; // Positiion on x
			gc.gridy = 1; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.LINE_START;
			gc.insets = new Insets(0, 0, 0, 0);
			add(typeOfStaff, gc);
			
			//third row first column
			gc.weightx = 1; 
			gc.weighty = 0.2;  
			
			gc.gridwidth = 1;  
			
			gc.gridx = 0; // Positiion on x
			gc.gridy = 2; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.LINE_END;
			gc.insets = new Insets(0, 0, 0, 0);
			add(bankAccount, gc);
			
			//third row and second column
			gc.weightx = 1; 
			gc.weighty = 0.2;  
			
			gc.gridwidth = 1;  
			
			gc.gridx = 1; // Positiion on x
			gc.gridy = 2; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.LINE_START;
			gc.insets = new Insets(0, 0, 0, 0);
			add(bankAccount1, gc);
			
			// fourth row and first column
			gc.weightx = 1; 
			gc.weighty = 0.2;  
			
			gc.gridwidth = 1;  
			
			gc.gridx = 0; // Positiion on x
			gc.gridy = 3; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.LINE_END;
			gc.insets = new Insets(0, 0, 0, 0);
			add(adress, gc);
			
			// fourth row and second column
			gc.weightx = 1; 
			gc.weighty = 0.2;  
			
			gc.gridwidth = 1;  
			
			gc.gridx = 1; // Positiion on x
			gc.gridy = 3; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.LINE_START;
			gc.insets = new Insets(0, 0, 0, 0);
			add(adress1, gc);
			
			//fifth row and first column 
			gc.weightx = 1; 
			gc.weighty = 0.2;  
			
			gc.gridwidth = 1;  
			
			gc.gridx = 0; // Positiion on x
			gc.gridy = 4; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.LINE_END;
			gc.insets = new Insets(0, 0, 0, 0);
			add(phone, gc);
			
			//fifth row and second column 
			gc.weightx = 1; 
			gc.weighty = 0.2;  
			
			gc.gridwidth = 1;  
			
			gc.gridx = 1; // Positiion on x
			gc.gridy = 4; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.LINE_START;
			gc.insets = new Insets(0, 0, 0, 0);
			add(phone1, gc);
			/**
			// sixth row and first column 
			gc.weightx = 0.1; 
			gc.weighty = 0.2;  
			
			gc.gridwidth = 1;  
			
			gc.gridx = 0; // Positiion on x
			gc.gridy = 5; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.EAST;
			gc.insets = new Insets(0, 100, 0, 0);
			add(quantity, gc);
			
			//sixth row and second column 
			gc.weightx = 1; 
			gc.weighty = 0.2;  
			
			gc.gridwidth = 1;  
			
			gc.gridx = 1; // Positiion on x
			gc.gridy = 5; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.WEST;
			gc.insets = new Insets(0, 0, 0, 0);
			add(quantity1, gc);
			*/
			// seventh row and first column 
			gc.weightx = 1; 
			gc.weighty = 0.2;  
			
			gc.gridwidth = 1;  
			
			gc.gridx = 0; // Positiion on x
			gc.gridy = 6; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.LINE_END;
			gc.insets = new Insets(0, 0, 0, 0);
			add(exists, gc);
			
			// seventh row and second column 
			gc.weightx = 1; 
			gc.weighty = 0.2;  
			
			gc.gridwidth = 1;  
			
			gc.gridx = 1; // Positiion on x
			gc.gridy = 6; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.LINE_START;
			gc.insets = new Insets(0, 0, 0, 0);
			add(exists2, gc);
			
			// eighth row and first column 
			gc.weightx = 0.1; 
			gc.weighty = 0.2;  
			
			gc.gridwidth = 1;  
			
			gc.gridx = 0; // Positiion on x
			gc.gridy = 7; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.LINE_END;
			gc.insets = new Insets(0, 0, 0, 10);
			add(cprNoLbl, gc);
			
			// eighth row and second column 
			gc.weightx = 1; 
			gc.weighty = 0.2;  
			
			gc.gridwidth = 1;  
			
			gc.gridx = 1; // Positiion on x
			gc.gridy = 7; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.LINE_START;
			gc.insets = new Insets(0, 0, 0, 0);
			add(cprNoTF, gc);
			
			// ninth row first column 
			gc.weightx = 1; 
			gc.weighty = 1;  
			
			gc.gridwidth = 2;  
			
			gc.gridx = 0; // Positiion on x
			gc.gridy = 8; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.SOUTH;
			gc.insets = new Insets(0, 0, 0, 0);
			add(update, gc);
			
			// tenth row first column 
			gc.weightx = 1; 
			gc.weighty = 0.6;  
			
			gc.gridwidth = 2;  
			
			gc.gridx = 0; // Positiion on x
			gc.gridy = 9; // Position on y
			
			gc.fill = GridBagConstraints.CENTER;
			gc.anchor = GridBagConstraints.NORTH;
			gc.insets = new Insets(10, 0, 0, 0);
			add(delete, gc);

		 
		 
	 }
	 public void setListenerForEverything(ListenerForEverything listener){
			this.listenerForEverything = listener;
		}
	
	

}
