package guiLayer;

import exceptionsLayer.DatabaseException;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;

import modelLayer.OrderLine;

public class MakeOrderUILeftPanel extends JPanel {

	private JTable table;
	private AbstractTableModel model;
	private JScrollPane sp;
	private JButton backBtn;
	private ListenerForEverything listenerForEverything;

	public MakeOrderUILeftPanel() {

		table = new JTable();
		sp = new JScrollPane(table);
		model = new MakeOrderMenuTableModel();
		table.setModel(model);
		backBtn = new JButton("Back");
		backBtn.setPreferredSize(new Dimension(100, 25));
		Dimension dim = getPreferredSize();
		dim.width = 350;
		setPreferredSize(dim);

		Border innerBorder = BorderFactory.createTitledBorder("Current Order");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

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

		// First row first column
		gc.weightx = 1;
		gc.weighty = 5;

		gc.gridwidth = 2;

		gc.gridx = 0;
		gc.gridy = 0;

		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(sp, gc);

		// First row first column

		gc.weightx = 2;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.gridy = 1;

		gc.ipadx = 50;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(backBtn, gc);

	}

	public void setListenerForEverything(ListenerForEverything listener) {
		this.listenerForEverything = listener;
	}
	public void setOrderList(ArrayList<OrderLine>  ol){
		((MakeOrderMenuTableModel)model).setOrderList(ol);
	}
	public void refresh(){
		model.fireTableDataChanged();
	}

}
