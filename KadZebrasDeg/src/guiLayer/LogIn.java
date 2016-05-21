package guiLayer;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import dbLayer.DBLogin;
import exceptionsLayer.DatabaseException;
public class LogIn extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsername;
	private JPasswordField passwordField ;
	private DBLogin dbLogin;
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try{
				LogIn lg =new LogIn();
				lg.setLocationRelativeTo(null);
				lg.setVisible(true);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	public LogIn() {
		
		dbLogin=new DBLogin();
		
		//JCheckBox cRemember = new JCheckBox("Remember username");
		JLabel lblIncorect = new JLabel("");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(87, 78, 100, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(87, 103, 100, 14);
		contentPane.add(lblPassword);
		
		tfUsername = new JTextField();
		tfUsername.setText("admin");
		tfUsername.setBounds(157, 75, 100, 20);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setText("admin");
		passwordField.setBounds(157, 103, 100, 20);
		contentPane.add(passwordField);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel.setBounds(81, 62, 233, 71);
		contentPane.add(panel);
		
		//read the username
		
		 
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(dbLogin.findLogin(tfUsername.getText(), passwordField.getText())!=null){
						lblIncorect.setText("");
						new MainUI();
						dispose();
					}else{
						lblIncorect.setText("Incorrect username or password.");
					}
				} catch (DatabaseException e) {
					lblIncorect.setText("Incorrect username or password.");
					e.printStackTrace();
				}
				}
			
		});
		btnLogIn.setBounds(156, 178, 89, 23);
		contentPane.add(btnLogIn);
		
		JButton btnCantLogIn = new JButton("Can't log in ? Inform management.");
		btnCantLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Send an e-mail to: management@mycompany.com");
			}
		});
		btnCantLogIn.setBounds(201, 238, 233, 23);
		contentPane.add(btnCantLogIn);
		
		JLabel lblNewLabel = new JLabel("Waiter Board");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(157, 11, 138, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Kad  Zebras Deg");
		lblNewLabel_1.setBounds(143, 36, 138, 14);
		contentPane.add(lblNewLabel_1);
		
		
		lblIncorect.setBounds(87, 134, 226, 14);
		contentPane.add(lblIncorect);
	}
}
