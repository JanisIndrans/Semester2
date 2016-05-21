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

import controlLayer.StaffController;
import exceptionsLayer.DatabaseException;

public class StaffUILeftPanel extends JPanel {
	private Label profession;
	private Label name;
	private Label bankAccount;
	private Label adress;
	private Label phone;
	private Label exists;
	private JComboBox typeOfStaff;
	private JComboBox exists2;
	private JTextField name1;
	private JTextField bankAccount1;
	private JTextField adress1;
	private JTextField phone1;
	private JButton create;
	private ListenerForEverything listenerForEverything;
	private JButton backBtn;
	private StaffController sc;
	private JLabel cprNoLbl;
	private JTextField cprNoTF;

	public StaffUILeftPanel() {
		sc = new StaffController();
		setPreferredSize(new Dimension(420, 0));

		Border innerBorder = BorderFactory.createTitledBorder("Create staff");
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
		String[] staffType = { "Waiter", "Cook", "Manager" };
		String[] existsOrNot = { "Yes", "No" };
		typeOfStaff = new JComboBox(staffType);
		exists2 = new JComboBox(existsOrNot);
		name1 = new JTextField(10);
		bankAccount1 = new JTextField(10);
		adress1 = new JTextField(10);
		phone1 = new JTextField(10);
		create = new JButton("Create");
		create.setPreferredSize(new Dimension(150, 25));
		backBtn = new JButton("Back");
		backBtn.setPreferredSize(new Dimension(150, 25));
		cprNoLbl = new JLabel("CPR No: ");
		cprNoTF = new JTextField(10);
		cprNoTF.setVisible(false);
		cprNoLbl.setVisible(false);

		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				AnyEvent anyEvent = new AnyEvent(this, "backBtn");
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
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "create");
				anyEvent.setCprNo(cprNoTF.getText());
				if (listenerForEverything != null) {
					try {
						listenerForEverything.AnyEventOcurred(anyEvent);
						if (!name1.getText().equals("")
								&& !bankAccount1.getText().equals("")
								&& !adress1.getText().equals("")
								&& !phone1.getText().equals("")) {
							sc.createStaff(name1.getText(), bankAccount1
									.getText(), adress1.getText(), phone1
									.getText(), typeOfStaff.getSelectedItem()
									.toString(), exists2.getSelectedItem()
									.toString());
							name1.setText("");
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
		gc.weighty = 0.2; // Size of of the cell height

		gc.gridwidth = 1; // How many cells are merged

		gc.gridx = 0; // Positiion on x
		gc.gridy = 0; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(profession, gc);

		// First row second column
		gc.weightx = 1; // Size of of the cell width
		gc.weighty = 0.2; // Size of of the cell height

		gc.gridwidth = 1; // How many cells are merged

		gc.gridx = 1; // Positiion on x
		gc.gridy = 0; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(typeOfStaff, gc);

		// Second row first column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 1; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(name, gc);

		// Second row second column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 1; // Positiion on x
		gc.gridy = 1; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(name1, gc);

		// thrid row first column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 2; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(bankAccount, gc);

		// third row and second column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 1; // Positiion on x
		gc.gridy = 2; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(bankAccount1, gc);

		// fourth row and first column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 3; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(adress, gc);

		// fourth row and second column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 1; // Positiion on x
		gc.gridy = 3; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(adress1, gc);

		// fifth row and first column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 4; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(phone, gc);

		// fifth row and second column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 1; // Positiion on x
		gc.gridy = 4; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(phone1, gc);
		/**
		 * // sixth row and first column gc.weightx = 1; gc.weighty = 0.2;
		 * 
		 * gc.gridwidth = 1;
		 * 
		 * gc.gridx = 0; // Positiion on x gc.gridy = 5; // Position on y
		 * 
		 * gc.fill = GridBagConstraints.CENTER; gc.anchor =
		 * GridBagConstraints.EAST; gc.insets = new Insets(0, 0, 0, 0);
		 * add(quantity, gc);
		 * 
		 * //sixth row and second column gc.weightx = 1; gc.weighty = 0.2;
		 * 
		 * gc.gridwidth = 1;
		 * 
		 * gc.gridx = 1; // Positiion on x gc.gridy = 5; // Position on y
		 * 
		 * gc.fill = GridBagConstraints.CENTER; gc.anchor =
		 * GridBagConstraints.WEST; gc.insets = new Insets(0, 0, 0, 0);
		 * add(quantity1, gc);
		 */
		// seventh row and first column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 6; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(exists, gc);

		// seventh row and second column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 1; // Positiion on x
		gc.gridy = 6; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(0, 0, 0, 0);
		add(exists2, gc);

		// eighth row and first column
		gc.weightx = 0.1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 7; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(0, 0, 0, 10);
		add(cprNoLbl, gc);

		// eighth row and second column
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridwidth = 1;

		gc.gridx = 1; // Positiion on x
		gc.gridy = 7; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
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
		add(create, gc);

		// ninth row first column
		gc.weightx = 1;
		gc.weighty = 0.6;

		gc.gridwidth = 2;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 9; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(10, 0, 0, 0);
		add(backBtn, gc);

	}

	public void setListenerForEverything(ListenerForEverything listener) {
		this.listenerForEverything = listener;
	}

}
