package guiLayer;

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

import exceptionsLayer.DatabaseException;

public class CourseUIRightPanel extends JPanel {

	private JButton regular1stBtn;
	private JButton vegetarian1stBtn;
	private JButton regular2ndBtn;
	private JButton vegetarian2ndBtn;
	private JButton desertBtn;
	ListenerForEverything listenerForEverything;

	public CourseUIRightPanel() {
		regular1stBtn = new JButton("Regular 1st");
		vegetarian1stBtn = new JButton("Vegetarian 1st");
		regular2ndBtn = new JButton("Regular 2nd");
		vegetarian2ndBtn = new JButton("Vegetarian 2nd");
		desertBtn = new JButton("Desert");

		regular1stBtn.setPreferredSize(new Dimension(150, 80));
		regular1stBtn.setFont(new Font("Serif", Font.BOLD, 15));

		vegetarian1stBtn.setPreferredSize(new Dimension(150, 80));
		vegetarian1stBtn.setFont(new Font("Serif", Font.BOLD, 15));

		regular2ndBtn.setPreferredSize(new Dimension(150, 80));
		regular2ndBtn.setFont(new Font("Serif", Font.BOLD, 15));

		vegetarian2ndBtn.setPreferredSize(new Dimension(150, 80));
		vegetarian2ndBtn.setFont(new Font("Serif", Font.BOLD, 15));

		desertBtn.setPreferredSize(new Dimension(150, 80));
		desertBtn.setFont(new Font("Serif", Font.BOLD, 15));

		Border innerBorder = BorderFactory.createTitledBorder("Choose Type");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		regular1stBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "regular1stBtn");
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
		vegetarian1stBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "vegetarian1stBtn");
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
		regular2ndBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "regular2ndBtn");
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
		vegetarian2ndBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "vegetarian2ndBtn");
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
		desertBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "desertBtn");
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
		add(regular1stBtn, gc);

		// First row first column

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 1;

		gc.gridx = 1;
		gc.gridy = 0;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(vegetarian1stBtn, gc);

		// First row first column

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.gridy = 1;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(regular2ndBtn, gc);

		// First row first column

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 1;

		gc.gridx = 1;
		gc.gridy = 1;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(vegetarian2ndBtn, gc);

		// First row first column

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 2;

		gc.gridx = 0;
		gc.gridy = 2;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(desertBtn, gc);

	}

	public void setListenerForEverything(ListenerForEverything listener) {
		this.listenerForEverything = listener;
	}

}
