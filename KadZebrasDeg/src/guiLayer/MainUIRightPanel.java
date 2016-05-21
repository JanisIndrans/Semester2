package guiLayer;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainUIRightPanel extends JPanel {
	
	private ImageIcon imageIcon;
	private JLabel logoLabel;
	private JLabel titleLabel;
	
	public MainUIRightPanel() {
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		logoLabel = new JLabel();
		imageIcon = new ImageIcon("Logo1.jpg");
		titleLabel = new JLabel("Welcome Back!");
		titleLabel.setFont(new Font("Serif", Font.BOLD, 40));
		
		logoLabel.setIcon(imageIcon);

		
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(10, 0, 0, 0);
		add(titleLabel, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 1;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(logoLabel, gc);
	}

}
