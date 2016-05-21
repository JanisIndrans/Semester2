package guiLayer;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderPopup extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private JTable table;
	private JScrollPane tablePane;
	private String[] columNames = {"Type","Name","Quantity","Time (Minutes)","",""};
	private Object[][] data = {{"1st","Soup","1","15","Start","Done"},
			{"2nd","Mashed potatoes with gravy","1","20","Start","Done"},
			{"3rd","Vanilla Ice cream","1","3","Start","Done"}	
	};
	private Table tableSample;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderPopup frame = new OrderPopup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderPopup() {
		try {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 550, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTableNo = new JLabel("Table No: 5");
		lblTableNo.setBounds(10, 11, 84, 14);
		contentPane.add(lblTableNo);
		
		JLabel lblOrder = new JLabel("Order");
		lblOrder.setBounds(10, 28, 46, 14);
		contentPane.add(lblOrder);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnBack.setBounds(20, 525, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnDone.setBounds(415, 525, 89, 23);
		contentPane.add(btnDone);
		
		tableSample = new Table();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	private class Table {
		DefaultTableModel model = new DefaultTableModel(data, columNames) {

		    private static final long serialVersionUID = 1L;
		    public boolean isCellEditable(int row, int column)
		    {
		      if (column == 4 || column == 5){
		    	  return true;
		      }
		      else {
		    	  return false;
		      }
		    }
		    
		}; 
		
		private JTable table = new JTable(model);
		
		public Table(){
		
			table.getColumnModel().getColumn(4).setCellRenderer(new ClientsTableButtonRenderer());
		    table.getColumnModel().getColumn(4).setCellEditor(new ClientsTableRenderer(new JCheckBox()));
		    table.getColumnModel().getColumn(5).setCellRenderer(new ClientsTableButtonRenderer());
		    table.getColumnModel().getColumn(5).setCellEditor(new ClientsTableRenderer(new JCheckBox()));
		
		
		table.setFillsViewportHeight(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE); 
	table.setBounds(10, 44, 389, 467);
	TableColumn column = null;
	for (int i = 0; i < 6; i++) {
	    column = table.getColumnModel().getColumn(i);
	    if (i == 1) {
	        column.setPreferredWidth(100); //third column is bigger
	    } 
	    else if (i == 2) {
	        column.setPreferredWidth(75); //third column is bigger
	    }
	    else {
	        column.setPreferredWidth(50);
	    }
	}
		tablePane = new JScrollPane(table);
		tablePane.setBounds(9, 45, 500, 468);
		contentPane.add(tablePane);
	}
}
	 class ClientsTableButtonRenderer extends JButton implements TableCellRenderer
	  {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ClientsTableButtonRenderer()
	    {
	      setOpaque(true);
	    }

	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	    {
	      setForeground(Color.black);
	      setBackground(UIManager.getColor("Button.background"));
	      setText((value == null) ? "" : value.toString());
	      return this;
	    }
	  }
	 
	 public class ClientsTableRenderer extends DefaultCellEditor
	  {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JButton button;
	    private String label;
	    private boolean clicked;
	    private int row, col;
	    private JTable table;

	    public ClientsTableRenderer(JCheckBox checkBox)
	    {
	      super(checkBox);
	      button = new JButton();
	      button.setOpaque(true);
	      button.addActionListener(new ActionListener()
	      {
	        public void actionPerformed(ActionEvent e)
	        {
	          fireEditingStopped();
	        }
	      });
	    }
	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	    {
	      this.table = table;
	      this.row = row;
	      this.col = column;

	      button.setForeground(Color.black);
	      button.setBackground(UIManager.getColor("Button.background"));
	      label = (value == null) ? "" : value.toString();
	      button.setText(label);
	      clicked = true;
	      return button;
	    }
	    public Object getCellEditorValue()
	    {
	      if (clicked)
	      {
	    	  int i;
	    	  for(i=0; i<table.getColumnCount(); i++) {
	    	  if(table.getColumnName(i).equals(table.getColumnName(4)))
	    	{
	    		  System.out.println(table.getColumnName(i));
	    		  System.out.println(table.getColumnName(4));
	        JOptionPane.showMessageDialog(button, "Winter is coming "+table.getValueAt(row, i) + table.getValueAt(row, 4) +" -  Khal Drogo!");
	      }
	    	  else if (table.getColumnName(i).equals(table.getColumnName(5))) {
	    		  System.out.println(table.getColumnName(i));
	    		  System.out.println(table.getColumnName(5));
	    	JOptionPane.showMessageDialog(button, "Mother of dragons "+table.getValueAt(row, i) + table.getValueAt(row, 5 )+ " -  Khaleesi!");
	    	  }
	    	  }
	      }
	      clicked = false;
	      return new String(label);
	    }

	    public boolean stopCellEditing()
	    {
	      clicked = false;
	      return super.stopCellEditing();
	    }

	    protected void fireEditingStopped()
	    {
	      super.fireEditingStopped();
	    }
	  }
}
