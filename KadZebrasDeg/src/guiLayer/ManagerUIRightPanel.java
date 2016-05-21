package guiLayer;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import exceptionsLayer.DatabaseException;


public class ManagerUIRightPanel extends JPanel {
	
	private JButton managerMenuBtn;
	private JButton waiterMenuBtn;
	private JButton chefMenuBtn;
	private JButton tableMenuBtn;
	private JButton merchandiseMenuBtn;
	private JButton exitBtn;
	private ListenerForEverything listenerForEverything;
	
	public ManagerUIRightPanel(){
		
		managerMenuBtn = new JButton("Manager Menu");
		waiterMenuBtn = new JButton("Waiter Menu");
		chefMenuBtn = new JButton("Chef Menu");
		tableMenuBtn = new JButton("Table Menu");
		merchandiseMenuBtn = new JButton("Merchandise Menu");
		exitBtn = new JButton("Exit");
			
		
		exitBtn.setPreferredSize(new Dimension(150, 25));
		managerMenuBtn.setPreferredSize(new Dimension(150, 25));
		waiterMenuBtn.setPreferredSize(new Dimension(150, 25));
		chefMenuBtn.setPreferredSize(new Dimension(150, 25));
		tableMenuBtn.setPreferredSize(new Dimension(150, 25));
		merchandiseMenuBtn.setPreferredSize(new Dimension(150, 25));
		
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		Border innerBorder = BorderFactory.createTitledBorder("Choose menu");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				AnyEvent anyEvent = new AnyEvent(this, "exitBtn");
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
		
		managerMenuBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "managerMenuBtn");
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
		
		waiterMenuBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "waiterMenuBtn");
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
		
		chefMenuBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "chefMenuBtn");
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
		
		tableMenuBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "tableMenuBtn");
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
		
		merchandiseMenuBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "merchandiseMenuBtn");
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
		
		//First row
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(50, 0, 5, 0);
		add(managerMenuBtn, gc);
		
		//Second row
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 1;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(waiterMenuBtn, gc);
		
		//Third row
		
		gc.weightx = 0.1;
		gc.weighty = 1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 2;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(5, 0, 0, 0);
		add(chefMenuBtn, gc);
		
		//Fourth row
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 3;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(tableMenuBtn, gc);
		
		//Fifth row
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 4;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(9, 0, 0, 0);
		add(merchandiseMenuBtn, gc);
		
		//Sixth row
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 5;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(exitBtn, gc);
	}
	
	public void setListenerForEverything(ListenerForEverything listener){
		this.listenerForEverything = listener;
	}


}
