package guiLayer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import modelLayer.Merchandise;
import modelLayer.Miscellaneous;
import exceptionsLayer.DatabaseException;

public class OrderEndUIRightPanel extends JPanel {

	private ListenerForEverything listenerForEverything;
	private int a;
	private int c;
	private ArrayList<Merchandise> allMerchandise;
	private ArrayList<Merchandise> allExistingMerchandise;

	public OrderEndUIRightPanel(ArrayList<Merchandise> all) {
		allMerchandise=all;
		System.out.println(allMerchandise.size());
		allExistingMerchandise = new ArrayList<>();
		Border innerBorder = BorderFactory
				.createTitledBorder("Choose Merchandise");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		//createTestObjects();

		if (!allMerchandise.isEmpty()) {
			getAllExisteingMerchandise();
			System.out.println("I am here");
		}

		for (int i = 0; i < allExistingMerchandise.size(); ++i) {
			Merchandise mer = allExistingMerchandise.get(i);

			JButton b = new JButton(mer.getName());
			gc.weightx = 1;
			gc.weighty = 1;

			gc.gridwidth = 1;

			gc.gridx = a;
			gc.gridy = c;

			gc.fill = GridBagConstraints.BOTH;
			gc.anchor = GridBagConstraints.NORTH;
			gc.insets = new Insets(0, 0, 0, 0);
			add(b, gc);

			if (a < 4) {
				a++;
				// System.out.println(a);
			} else {
				a = 0;
				c++;
				System.out.println(c);
			}
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					AnyEvent anyEvent = new AnyEvent(this, b.getLabel());
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
		}

	}

	public void setListenerForEverything(ListenerForEverything listener) {
		this.listenerForEverything = listener;
	}

	public void setAllMerchandise(ArrayList<Merchandise> allMerchandise) {
		this.allMerchandise = allMerchandise;
	}

	public void getAllExisteingMerchandise() {
		//allExistingMerchandise = new ArrayList<>();
		for (Merchandise m : allMerchandise) {
			System.out.println(m.getName());
			if (m.getExists()) {
				allExistingMerchandise.add(m);
			}
		}
	}

	public void createTestObjects() {
		allMerchandise = new ArrayList<>();
		Miscellaneous m1 = new Miscellaneous();
		m1.setExists(true);
		m1.setName("Potato");
		allMerchandise.add(m1);
		Miscellaneous m2 = new Miscellaneous();
		m2.setExists(true);
		m2.setName("Potato");
		allMerchandise.add(m2);
		Miscellaneous m3 = new Miscellaneous();
		m3.setExists(true);
		m3.setName("Potato");
		allMerchandise.add(m3);
		Miscellaneous m4 = new Miscellaneous();
		m4.setExists(true);
		m4.setName("Potato");
		allMerchandise.add(m4);
		Miscellaneous m5 = new Miscellaneous();
		m5.setExists(true);
		m5.setName("Potato");
		allMerchandise.add(m5);
		Miscellaneous m6 = new Miscellaneous();
		m6.setExists(true);
		m6.setName("Potato");
		allMerchandise.add(m6);
		Miscellaneous m7 = new Miscellaneous();
		m7.setExists(false);
		m7.setName("Potato");
		allMerchandise.add(m7);
	}
}
