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

public class DrinkUIRightPanel extends JPanel {

	private JButton alcoholicBtn;
	private JButton nonAlcoholicBtn;
	private ListenerForEverything listenerForEverything;

	public DrinkUIRightPanel() {
		alcoholicBtn = new JButton("Alcoholic");
		nonAlcoholicBtn = new JButton("Non-alcoholic");

		alcoholicBtn.setPreferredSize(new Dimension(150, 80));
		alcoholicBtn.setFont(new Font("Serif", Font.BOLD, 20));
		nonAlcoholicBtn.setPreferredSize(new Dimension(150, 80));
		nonAlcoholicBtn.setFont(new Font("Serif", Font.BOLD, 20));

		Border innerBorder = BorderFactory
				.createTitledBorder("Choose Type Of A Drink");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		// First row first column

		alcoholicBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "alcoholicBtn");
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
		nonAlcoholicBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "nonAlcoholicBtn");
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

		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.gridy = 0;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(alcoholicBtn, gc);

		// First row first column

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.gridy = 1;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nonAlcoholicBtn, gc);

	}

	public void setListenerForEverything(ListenerForEverything listener) {
		this.listenerForEverything = listener;
	}

}
