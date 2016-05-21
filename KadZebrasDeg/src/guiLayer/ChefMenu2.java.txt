package guiLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.UIManager;



public class ChefMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	
	
	private JPanel pane1;
	private JScrollPane tablePane;
	private String[] columNames = {"Table","Minutes Elapsed","Time(minutes)","Status",""};
	private Object[][] data = {{"1","4","30","in process","show"},{"2","11","45","Started","show"}};
	private Table tableSample;
	private static OrderPopup orderPopup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChefMenu frame = new ChefMenu();
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
	public ChefMenu() {
		
		try {
		
		pane1 = new JPanel();
		setBounds(400, 50, 450, 600);
		pane1.setBorder(BorderFactory.createTitledBorder(null, "chef menu", 0, TitledBorder.CENTER, Font.getFont(getName()), Color.BLACK));
		setContentPane(pane1);
		pane1.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 527, 89, 23);
		pane1.add(btnBack);
		
		tableSample = new Table();
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private class Table {
		DefaultTableModel model = new DefaultTableModel(data, columNames) {

		    private static final long serialVersionUID = 1L;
		    public boolean isCellEditable(int row, int column)
		    {
		      return column == 4;
		    }
		}; 
		
		private JTable table = new JTable(model);
		
		public Table(){
		
			table.getColumnModel().getColumn(4).setCellRenderer(new ClientsTableButtonRenderer());
		    table.getColumnModel().getColumn(4).setCellEditor(new ClientsTableRenderer(new JCheckBox()));
		
		
		table.setFillsViewportHeight(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
	table.setBounds(10, 44, 389, 467);
	TableColumn column = null;
	for (int i = 0; i < 5; i++) {
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
		tablePane.setBounds(9, 45, 410, 468);
		pane1.add(tablePane);
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
	        orderPopup = new OrderPopup();
	        orderPopup.setVisible(true);
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
