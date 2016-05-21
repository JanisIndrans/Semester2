package guiLayer;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelLayer.OrderLine;
import exceptionsLayer.DatabaseException;

public class ChoosenTableDialog extends JDialog {

	private JLabel tableNo;
	private JLabel order;
	private JLabel numbersTable;
	private JLabel total;
	private JLabel amount;
	private JButton edit;
	private JButton addMerchandise;
	private JButton endOrder;
	private JButton back;
	private JTable table;
	private JScrollPane scroll;
	private int[] numbersOfTables;
	private ListenerForEverything listenerForEverything;
	private int tableNoFromLabel;
	private ChosenTableModel chosenTableModel;

	public ChoosenTableDialog(JFrame parent, String title, ArrayList<OrderLine> list) {
		super(parent, title);

		tableNo = new JLabel("Table No : ");
		order = new JLabel("Order");
		numbersTable = new JLabel("");
		chosenTableModel = new ChosenTableModel(list);
		table = new JTable(chosenTableModel);
		scroll = new JScrollPane(table);
		edit = new JButton("Edit");
		edit.setPreferredSize(new Dimension(150, 25));
		addMerchandise = new JButton("Add Merchandise");
		addMerchandise.setPreferredSize(new Dimension(150, 25));
		endOrder = new JButton("End order");
		endOrder.setPreferredSize(new Dimension(150, 25));
		back = new JButton("Back");
		back.setPreferredSize(new Dimension(150, 25));
		total = new JLabel("Total: ");
		amount = new JLabel("0,00 DKK");
		setAlwaysOnTop(true);
		setModal(true);

		setSize(350, 400);
		setResizable(false);
		setAlwaysOnTop(true);
		setModal(true);
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "backBtn");
				if(listenerForEverything != null){
					try {
						listenerForEverything.AnyEventOcurred(anyEvent);
					} catch (DatabaseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		endOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "endOrder");
				anyEvent.setTableNo(tableNoFromLabel);
				if(listenerForEverything != null){
					try {
						listenerForEverything.AnyEventOcurred(anyEvent);
					} catch (DatabaseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		addMerchandise.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "addMerchandise");
				anyEvent.setTableNo(tableNoFromLabel);
				if(listenerForEverything != null){
					try {
						listenerForEverything.AnyEventOcurred(anyEvent);
					} catch (DatabaseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});

		// first row first column
		gc.weightx = 1; // Size of of the cell width
		gc.weighty = 1; // Size of of the cell height

		gc.gridwidth = 2; // How many cells are merged

		gc.gridx = 0; // Positiion on x
		gc.gridy = 0; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(tableNo, gc);
		/**
		 * // first row second column gc.weightx = 1; // Size of of the cell
		 * width gc.weighty = 1; // Size of of the cell height
		 * 
		 * gc.gridwidth = 1; // How many cells are merged
		 * 
		 * gc.gridx = 1; // Positiion on x gc.gridy = 0; // Position on y
		 * 
		 * gc.fill = GridBagConstraints.CENTER; gc.anchor =
		 * GridBagConstraints.LINE_START; gc.insets = new Insets(0, 5, 0, 0);
		 * add(numbersTable, gc);
		 */
		// Second row first column
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 2;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 1; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		order.setFont(new Font("Serif", Font.BOLD, 20));
		;
		add(order, gc);

		// Third row
		gc.weightx = 1;
		gc.weighty = 2;

		gc.gridwidth = 2;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 2; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		scroll.setPreferredSize(new Dimension(300, 200));
		add(scroll, gc);

		// fourth row first column

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 1;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 3; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(back, gc);

		// fourth row second column

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 2;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 3; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 130);
		add(total, gc);

		// fourth row second column

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 1;

		gc.gridx = 1; // Positiion on x
		gc.gridy = 3; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 70);
		add(amount, gc);

		// fifth row first column

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 1;

		gc.gridx = 1; // Positiion on x
		gc.gridy = 4; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(10, 0, 0, 10);
		add(addMerchandise, gc);

		// sixth row frist column
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 1;

		gc.gridx = 1; // Positiion on x
		gc.gridy = 5; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 10, 10);
		add(endOrder, gc);

		// sixth row second column
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 1;

		gc.gridx = 0; // Positiion on x
		gc.gridy = 5; // Position on y

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 10, 10, 0);
		add(back, gc);

	}

	public void setTableNoLabel(String text) {
		tableNoFromLabel = Integer.parseInt(text);
		tableNo.setText("Table No: " + tableNoFromLabel);
		// numbersTable.setText(text);
		this.revalidate();
		this.repaint();
	}
	public void setListenerForEverything(ListenerForEverything listener){
		this.listenerForEverything = listener;
	}
	public void setOrderLines(ArrayList<OrderLine> lines){
		chosenTableModel.setOrderLines(lines);
	}
	
	public int getTableNo(){
		String[] str = tableNo.getText().split(" ");
		return Integer.parseInt(str[2]);
	}

}
