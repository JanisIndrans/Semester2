package guiLayer;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import exceptionsLayer.DatabaseException;


class ChefUIRightPanel extends JPanel
{
	private JTable table;
	private JScrollPane scrollPane,scrollPane1;
	private String[] columnNames= new String[3];
	private String[][] dataValues=new String[3][3];

	private ListenerForEverything listenerForEverything;
	
	JButton button = new JButton("Show");
	JButton backButton = new JButton("Back");
	
	
	public ChefUIRightPanel()
	{

		setSize(900, 600);
		backButton.setPreferredSize(new Dimension(150, 25));



		columnNames=new String[] {"Table" , "Minutes elapsed" , "Time(minutes)", "Status", ""};
		dataValues = new String[][]              {
				{"--","---","----","----"},
				{"---","----","---","---"},
				{"----","-----","----","---"}
		};
		TableModel model=new myTableModel("owntable");
		table =new JTable( );
		table.setModel(model);
		table.getColumn("").setCellRenderer(new ButtonRenderer());
		table.getColumn("").setCellEditor(new ButtonEditor(new JCheckBox()));

		scrollPane=new JScrollPane(table);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		
		Border innerBorder = BorderFactory.createTitledBorder("CheffMenu");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "backButton");
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
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(10, 0, 0, 0);
		add(scrollPane, gc);
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridwidth = 1;
		
		gc.gridx = 0;
		gc.gridy = 1;
		
		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(10, 20, 0, 0);
		add(backButton, gc);
		
		

		button.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						JOptionPane.showMessageDialog(null,"Button Clicked in JTable Cell");
					}
				}
				);
	}
	
	 public void setListenerForEverything(ListenerForEverything listener){
			this.listenerForEverything = listener;
		}
	
	
	class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer() {
			setOpaque(true);
		}
		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			setText((value == null) ? "Show" : value.toString());
			return this;
		}
	}
	
	
	
	class ButtonEditor extends DefaultCellEditor {
		private String label;
		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
		}
		
		
		public Component getTableCellEditorComponent(JTable table, Object value,
				boolean isSelected, int row, int column) {
			label = (value == null) ? "Show" : value.toString();
			button.setText(label);
			return button;
		}
		
		
		public Object getCellEditorValue() {
			return new String(label);
		}
	}
	
	
	
	public class myTableModel extends DefaultTableModel
	{
		String dat;
		JButton button=new JButton("Show");
		myTableModel(String tname){
			super(dataValues,columnNames);
			dat=tname;
		}

		public boolean isCellEditable(int row,int cols)
		{
			if( dat=="owntable"){
				if(cols==0){return false;}
			}
			return true;
		}
	}         

}