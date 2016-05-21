package guiLayer;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import modelLayer.Order;
import modelLayer.Table;
import controlLayer.OrderController;
import controlLayer.ReservationController;
import controlLayer.TableController;
import exceptionsLayer.DatabaseException;

public class WaiterUILeftPanel extends JPanel {

	private JComboBox<String> monthsYear;
	private JComboBox<String> days;
	private JComboBox<String> time;
	private JLabel noOfSeatsLabel;
	private JLabel monthLabel;
	private JLabel dayLabel;
	private JLabel nameLabel;
	private JLabel phoneNoLabel;
	private JLabel timeLabel;
	private JTextField noOfSeatsTextField;
	private JTextField nameTextField;
	private JTextField phoneNoTextField;
	private JButton systemSudgestionBtn;
	private JButton makeReservationBtn;
	private JButton searchBtn;
	private JButton backBtn;
	private ListenerForEverything listenerForEverything;
	Date currentTime;
	DefaultComboBoxModel timeComboBox;
	private JButton makeOrder;
	private JLabel numOfSeatsInRestaurant;
	private TableController tableController;

	public WaiterUILeftPanel() {
		monthsYear = new JComboBox<String>();
		days = new JComboBox<String>();
		time = new JComboBox<String>();
		noOfSeatsLabel = new JLabel("No. of seats:");
		monthLabel = new JLabel("Month/Year:");
		dayLabel = new JLabel("Day:");
		nameLabel = new JLabel("Name:");
		phoneNoLabel = new JLabel("Phone No.:");
		timeLabel = new JLabel("Time: ");
		noOfSeatsTextField = new JTextField(2);
		nameTextField = new JTextField(10);
		phoneNoTextField = new JTextField(10);
		systemSudgestionBtn = new JButton("System sudgestion");
		systemSudgestionBtn.setPreferredSize(new Dimension(150, 25));
		makeReservationBtn = new JButton("Make reservation");
		makeReservationBtn.setPreferredSize(new Dimension(150, 25));
		searchBtn = new JButton("Search");
		searchBtn.setEnabled(false);
		searchBtn.setPreferredSize(new Dimension(150, 25));
		backBtn = new JButton("Back");
		backBtn.setPreferredSize(new Dimension(150, 25));
		currentTime = new Date();
		makeOrder = new JButton("Make New Order");
		makeOrder.setEnabled(false);
		makeOrder.setPreferredSize(new Dimension(150, 25));
		tableController = new TableController();
		numOfSeatsInRestaurant = new JLabel("Available seats: " + tableController.countAllAvailableSeats());
		

		Border innerBorder = BorderFactory.createTitledBorder("Reservation");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		// sets months and years in combo box
		setMonthAndYearInComboBox();

		// sets days in combo box
		setDaysInDaysComboBox();
		adoptCurrentTimeToSystem();

		// sets the time in combo box
		// setTheCorrectTimesInComboBox();

		makeOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this, "makeOrder");
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

		days.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				adoptCurrentTimeToSystem();

			}
		});

		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this,
						"WaiterUILeftPanelSearchBtn");
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

		makeReservationBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this,
						"WaiterUILeftPanelmakeReservationBtn");

				// Something that I added to pass the info

				anyEvent.setName(nameTextField.getText());
				anyEvent.setPhoneNo(phoneNoTextField.getText());
				if(noOfSeatsTextField.getText().equals("")){
					anyEvent.setNumberOfSeats(0);
				}
				else{
				anyEvent.setNumberOfSeats(Integer.parseInt(noOfSeatsTextField
						.getText()));
				}
				anyEvent.setTime(time.getSelectedItem().toString());
				anyEvent.setMonthsYear(monthsYear.getSelectedItem().toString());
				anyEvent.setDays(days.getSelectedItem().toString());

				if (listenerForEverything != null) {
					try {
						listenerForEverything.AnyEventOcurred(anyEvent);
						//createOrderAndAddTables();

					} catch (DatabaseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		systemSudgestionBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this,
						"WaiterUILeftPanelSystemSudgestionBtn");
				if(noOfSeatsTextField.getText().equals("")){
					anyEvent.setNumberOfSeats(0);
				}
				else{
				anyEvent.setNumberOfSeats(Integer.parseInt(noOfSeatsTextField.getText()));
				}
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

		time.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// timeComboBox.addElement("now");

			}
		});

		// Sets the right amount of days in days combo box after choosing one of
		// the months.
		monthsYear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				adoptCurrentTimeToSystem();
				String currentSelection = monthsYear.getSelectedItem()
						.toString();
				String[] parts = currentSelection.split(" ");
				String monthOfSelection = parts[0];
				String yearOfSelection = parts[1];
				int yearInInt = Integer.parseInt(yearOfSelection);
				int monthInt = 12;
				// gets the name of the month from the combo box and finds the
				// right integer of month
				if (monthOfSelection.equals("January")) {
					monthInt = 0;
				}
				if (monthOfSelection.equals("February")) {
					monthInt = 1;
				}
				if (monthOfSelection.equals("March")) {
					monthInt = 2;
				}
				if (monthOfSelection.equals("April")) {
					monthInt = 3;
				}
				if (monthOfSelection.equals("May")) {
					monthInt = 4;
				}
				if (monthOfSelection.equals("June")) {
					monthInt = 5;
				}
				if (monthOfSelection.equals("July")) {
					monthInt = 6;
				}
				if (monthOfSelection.equals("August")) {
					monthInt = 7;
				}
				if (monthOfSelection.equals("September")) {
					monthInt = 8;
				}
				if (monthOfSelection.equals("October")) {
					monthInt = 9;
				}
				if (monthOfSelection.equals("November")) {
					monthInt = 10;
				}
				if (monthOfSelection.equals("December")) {
					monthInt = 11;
				}

				// Create a calendar object and set year and month
				Calendar mycal1 = new GregorianCalendar(yearInInt, monthInt, 1);

				// Get the number of days in that month
				int daysInMonth = mycal1
						.getActualMaximum(Calendar.DAY_OF_MONTH); // 28

				// Checks if the month is current month or any other and then
				// sets the right amount of days
				// in combo box after the month was chosen and prevents system
				// from choosing past days.
				DefaultComboBoxModel daysOfMonthComboBox1 = new DefaultComboBoxModel();
				Calendar cal = Calendar.getInstance();
				if (monthInt != cal.get(Calendar.MONTH)) {
					for (int x = 1; x <= daysInMonth; x++) {
						daysOfMonthComboBox1.addElement(x);
					}
				} else {
					try {
						for (int x = cal.get(Calendar.DAY_OF_MONTH); x <= cal
								.getActualMaximum(Calendar.DAY_OF_MONTH); x++) {
							daysOfMonthComboBox1.addElement(x);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				days.setModel(daysOfMonthComboBox1);

			}
		});

		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnyEvent anyEvent = new AnyEvent(this,
						"backBtnWaiterUILeftPanel");
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

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 2;

		gc.gridx = 0;
		gc.gridy = 0;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(10, 20, 0, 0);
		add(numOfSeatsInRestaurant, gc);

		// First row first column
		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.gridy = 1;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(10, 20, 0, 0);
		add(noOfSeatsLabel, gc);

		// First row second column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 1;
		gc.gridy = 1;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(10, 0, 0, 0);
		add(noOfSeatsTextField, gc);

		// Second row first column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 2;

		gc.gridx = 0;
		gc.gridy = 2;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(systemSudgestionBtn, gc);

		// Third row first column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.gridy = 3;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(monthLabel, gc);

		// Third row second column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 1;
		gc.gridy = 3;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(monthsYear, gc);

		// Fourth row first column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.gridy = 4;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(dayLabel, gc);

		// Fourth row second column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 1;
		gc.gridy = 4;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(days, gc);

		// Fifth row first column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.gridy = 5;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(timeLabel, gc);

		// Fifth row second column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 1;
		gc.gridy = 5;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(time, gc);

		// Sixth row first column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.gridy = 6;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(nameLabel, gc);

		// Sixth row second column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 1;
		gc.gridy = 6;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameTextField, gc);

		// Seventh row first column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.gridy = 7;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(phoneNoLabel, gc);

		// Seventh row second column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 1;

		gc.gridx = 1;
		gc.gridy = 7;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(phoneNoTextField, gc);

		// Eighth row first column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 2;

		gc.gridx = 0;
		gc.gridy = 8;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(makeReservationBtn, gc);

		// Ninth row first column

		gc.weightx = 0.1;
		gc.weighty = 0.1;

		gc.gridwidth = 2;

		gc.gridx = 0;
		gc.gridy = 9;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(searchBtn, gc);
		
		// Tenth row first column

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 2;

		gc.gridx = 0;
		gc.gridy = 10;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.insets = new Insets(0, 20, 0, 0);
		add(makeOrder, gc);

		// Eleventh row first column

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridwidth = 2;

		gc.gridx = 0;
		gc.gridy = 11;

		gc.fill = GridBagConstraints.CENTER;
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.insets = new Insets(0, 20, 10, 0);
		add(backBtn, gc);
	}

	public void setListenerForEverything(ListenerForEverything listener) {
		this.listenerForEverything = listener;
	}

	// set month and year in combo box
	public void setMonthAndYearInComboBox() {
		int iYear = Calendar.getInstance().get(Calendar.YEAR);
		int iMonth = Calendar.getInstance().get(Calendar.MONTH);
		int iDay = Calendar.getInstance().get(Calendar.DATE);

		// Create a calendar object and set year and month
		Calendar mycal = new GregorianCalendar(iYear, iMonth, iDay);

		// Get the number of days in that month
		int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); // 28

		// sets the months and year in combo box when this panel is created
		// (allows bookings 1 year in future)
		// This project uses Date and time from the system
		DefaultComboBoxModel monthsComboBox = new DefaultComboBoxModel();
		String[] monthss = new DateFormatSymbols().getMonths();
		for (int i = new Date().getMonth(); i <= 11; i++) {
			String month = monthss[i];
			monthsComboBox.addElement(month + " "
					+ Calendar.getInstance().get(Calendar.YEAR));
		}
		for (int i = 0; i < new Date().getMonth(); i++) {
			String month1 = monthss[i];
			monthsComboBox.addElement(month1 + " "
					+ (Calendar.getInstance().get(Calendar.YEAR) + 1));
		}

		monthsYear.setModel(monthsComboBox);
	}

	public void setDaysInDaysComboBox() {
		DefaultComboBoxModel daysOfMonthComboBox = new DefaultComboBoxModel();
		Calendar c = Calendar.getInstance();
		int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int x = c.get(Calendar.DAY_OF_MONTH); x <= monthMaxDays; x++) {
			daysOfMonthComboBox.addElement(x);
		}
		days.setModel(daysOfMonthComboBox);
	}

	public void revalidatePanel() {
		this.revalidate();
		this.repaint();
	}

	public void addTimeToComboBox(int hours, int minutes) {
		DefaultComboBoxModel timeBox = new DefaultComboBoxModel();
		int timeHours = hours;
		int timeMinutes = minutes;
		timeBox.addElement("Now");
		for (int x = timeHours; x <= 23; x++) {
			for (int y = timeMinutes; y <= 45; y += 15) {
				if (y == 0) {
					if (x < 10) {
						timeBox.addElement("0" + x + ":" + "00");
					} else {
						timeBox.addElement(x + ":" + "00");
					}
				} else {
					if (x < 10) {
						timeBox.addElement("0" + x + ":" + y);
					} else {
						timeBox.addElement(x + ":" + y);
					}
				}
			}
		}
		time.setModel(timeBox);
	}

	public void adoptCurrentTimeToSystem() {
		Date time = new Date();
		int curMinutes = time.getMinutes();
		int curHours = time.getHours();
		int monthInt = time.getMonth();
		int year = time.getYear();
		int day = time.getDay();
		String monthString = convertNumberMontInToName(monthInt);
		String getSelectedMonthArray[] = monthsYear.getSelectedItem()
				.toString().split(" ");
		String selectedMonth = getSelectedMonthArray[0];
		String selectedYear = getSelectedMonthArray[1];
		String selectedDay = days.getSelectedItem().toString();

		int adoptedMinutes = 0;
		if (curMinutes > 0 && curMinutes <= 15) {
			curMinutes = 15;
		}
		if (curMinutes > 15 && curMinutes <= 30) {
			curMinutes = 30;
		}
		if (curMinutes > 30 && curMinutes <= 45) {
			curMinutes = 45;
		}
		if (curMinutes > 45) {
			if (curHours < 23) {
				curHours = curHours + 1;
				curMinutes = 0;
			} else {
				curHours = 0;
				curMinutes = 0;
			}
		}
		if (!monthString.equals(selectedMonth)
				&& time.getYear() != Integer.parseInt(selectedYear)) {
			if (Calendar.getInstance().get(Calendar.DATE) != Integer
					.parseInt(selectedDay)) {
				addTimeToComboBox(0, 0);
				System.out.println("selectedDay: " + selectedDay);
				System.out.println("time.getDay(): " + time.getDay());
			}
		} else {
			addTimeToComboBox(curHours, curMinutes);
		}
	}

	public String convertNumberMontInToName(int month) {
		String[] monthss = new DateFormatSymbols().getMonths();
		String nameOfMonth = monthss[month];
		return nameOfMonth;
	}


}
