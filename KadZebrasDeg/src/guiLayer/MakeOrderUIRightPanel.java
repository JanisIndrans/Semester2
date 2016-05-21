package guiLayer;

import exceptionsLayer.DatabaseException;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MakeOrderUIRightPanel extends JPanel {
	
	private JButton courseBtn;
	private JButton drinkBtn;
	private JButton otherBtn;
	private JButton confirmOrderBtn;
	private ListenerForEverything listenerForEverything;

	
	public MakeOrderUIRightPanel() {
		
		courseBtn = new JButton("Course");
		courseBtn.setFont(new Font("Serif", Font.BOLD, 30));
		drinkBtn = new JButton("Drink");
		drinkBtn.setFont(new Font("Serif", Font.BOLD, 30));
		otherBtn = new JButton("Other");
		otherBtn.setFont(new Font("Serif", Font.BOLD, 30));
		confirmOrderBtn = new JButton("Confirm Order");
		confirmOrderBtn.setFont(new Font("Serif", Font.BOLD, 17));
		
		courseBtn.setPreferredSize(new Dimension(150, 80));
		drinkBtn.setPreferredSize(new Dimension(150, 80));
		otherBtn.setPreferredSize(new Dimension(150, 80));
		confirmOrderBtn.setPreferredSize(new Dimension(150, 80));
		
		
		Border innerBorder = BorderFactory.createTitledBorder("Choose Order");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		courseBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this,
						"courseBtn");
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
		drinkBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this,
						"drinkBtn");
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
		otherBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this,
						"otherBtn");
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
		
		confirmOrderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this,
						"confirmOrderBtn");
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
				gc.weighty = 1;

				gc.gridwidth = 2;

				gc.gridx = 0;
				gc.gridy = 0;

				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.CENTER;
				gc.insets = new Insets(0, 0, 0, 0);
				add(courseBtn, gc);
				
				// First row first column

				gc.weightx = 1;
				gc.weighty = 1;

				gc.gridwidth = 2;

				gc.gridx = 0;
				gc.gridy = 1;

				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.CENTER;
				gc.insets = new Insets(0, 0, 0, 0);
				add(drinkBtn, gc);
				
				// First row first column

				gc.weightx = 1;
				gc.weighty = 1;

				gc.gridwidth = 2;

				gc.gridx = 0;
				gc.gridy = 2;

				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.CENTER;
				gc.insets = new Insets(0, 0, 0, 0);
				add(otherBtn, gc);
				
				// First row first column

				gc.weightx = 1;
				gc.weighty = 1;

				gc.gridwidth = 2;

				gc.gridx = 0;
				gc.gridy = 3;

				gc.fill = GridBagConstraints.CENTER;
				gc.anchor = GridBagConstraints.CENTER;
				gc.insets = new Insets(0, 0, 0, 0);
				add(confirmOrderBtn, gc);
				
		
	}
	public void setListenerForEverything(ListenerForEverything listener) {
		this.listenerForEverything = listener;
	}
}
