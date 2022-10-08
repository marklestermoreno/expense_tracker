package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Choice;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

import javax.swing.SwingConstants;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

public class income {

	JFrame incomeFrame;
	private JTable table;
	static String username, name;

	public void run() {
		try {
			income window = new income(username, name);
			window.incomeFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public income(String username, String name) {
		this.username = username;
		this.name = name;
		initialize();
		table_load();
	}

	Connection con;
	PreparedStatement insert;

	private void table_load() {

		PreparedStatement view;

		int c;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/expense_tracker", "root", "");
			view = con.prepareStatement("select * from income order by income_id desc");

			ResultSet rs = view.executeQuery();
			java.sql.ResultSetMetaData rss = rs.getMetaData();
			c = rss.getColumnCount();

			DefaultTableModel df = (DefaultTableModel) table.getModel();
			df.setRowCount(0);

			while (rs.next()) {

				Vector<String> v2 = new Vector<String>();

				for (int a = 1; a <= c; a++) {

					v2.add(rs.getString("income_name"));
					v2.add(rs.getString("details"));
					v2.add(rs.getString("income_amount"));
					v2.add(rs.getString("month_selection"));
					v2.add(rs.getString("year_selection"));
					v2.add(rs.getString("income_id"));
					v2.add(rs.getString("username"));
				}

				try {

					if (rs.getString("username").contains(username)) {

						df.addRow(v2);
					}

				} catch (NullPointerException ex) {
					ex.getStackTrace();
				}

			}

		} catch (ClassNotFoundException ex) {

			ex.getStackTrace();
			ex.getMessage();
		}

		catch (SQLException ex) {

			ex.getStackTrace();
			ex.getMessage();
		}

	}

	@SuppressWarnings("serial")
	private void initialize() {

		java.util.Date date = new java.util.Date();

		incomeFrame = new JFrame();
		incomeFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\ML Moreno Files\\Pictures\\ico.png"));
		incomeFrame.setTitle("Java Rice");
		incomeFrame.setBounds(100, 100, 920, 566);
		incomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		incomeFrame.getContentPane().setLayout(null);
		incomeFrame.setResizable(false);

		// Form

		JTextPane txtpnWelcome = new JTextPane();
		txtpnWelcome.setEditable(false);
		txtpnWelcome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnWelcome.setBackground(UIManager.getColor("CheckBox.interiorBackground"));
		txtpnWelcome.setText("Monthly Income Tracker");
		txtpnWelcome.setBounds(85, 35, 229, 31);
		incomeFrame.getContentPane().add(txtpnWelcome);

		// Graph

		// Date
		Choice month = new Choice();
		month.setBounds(490, 35, 100, 31);
		month.add("January");
		month.add("February");
		month.add("March");
		month.add("April");
		month.add("May");
		month.add("June");
		month.add("July");
		month.add("August");
		month.add("September");
		month.add("October");
		month.add("November");
		month.add("December");
		incomeFrame.getContentPane().add(month);

		String currentMonth = date.toString().substring(4, 7);

		int indexOfMonth = 0;

		switch (currentMonth) {
		case "Jan":
			indexOfMonth = 0;
			break;
		case "Feb":
			indexOfMonth = 1;
			break;
		case "Mar":
			indexOfMonth = 2;
			break;
		case "Apr":
			indexOfMonth = 3;
			break;
		case "May":
			indexOfMonth = 4;
			break;
		case "Jun":
			indexOfMonth = 5;
			break;
		case "Jul":
			indexOfMonth = 6;
			break;
		case "Aug":
			indexOfMonth = 7;
			break;
		case "Sep":
			indexOfMonth = 8;
			break;
		case "Oct":
			indexOfMonth = 9;
			break;
		case "Nov":
			indexOfMonth = 10;
			break;
		case "Dec":
			indexOfMonth = 11;
			break;
		}

		month.select(indexOfMonth);

		Choice year = new Choice();
		year.setBounds(600, 35, 60, 20);
		year.add("2022");
		year.add("2023");
		year.add("2024");
		year.add("2025");
		year.add("2026");
		year.add("2027");
		year.add("2028");
		year.add("2029");
		year.add("2030");
		year.add("2031");
		incomeFrame.getContentPane().add(year);

		String currentYear = date.toString().substring(24, 28);
		int indexOfYear = 0;

		switch (currentYear) {
		case "2022":
			indexOfYear = 0;
			break;
		case "2023":
			indexOfYear = 1;
			break;
		case "2024":
			indexOfMonth = 2;
			break;
		case "2025":
			indexOfMonth = 3;
			break;
		case "2026":
			indexOfMonth = 4;
			break;
		case "2027":
			indexOfMonth = 5;
			break;
		case "2028":
			indexOfMonth = 6;
			break;
		case "2029":
			indexOfMonth = 7;
			break;
		case "2030":
			indexOfMonth = 8;
			break;
		case "2031":
			indexOfMonth = 9;
			break;
		case "2032":
			indexOfMonth = 10;
			break;
		}

		year.select(indexOfYear);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(57, 57, 57));
		panel.setBounds(0, 425, 904, 102);
		incomeFrame.getContentPane().add(panel);
		panel.setLayout(null);

		// Graph

		JButton btnGraphSummary = new JButton("Profile");

		btnGraphSummary.setBounds(706, 38, 124, 37);
		panel.add(btnGraphSummary);
		btnGraphSummary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				graph grp = new graph(username, name);
				grp.graph.setVisible(true);
				incomeFrame.dispose();

			}
		});
		btnGraphSummary.setBackground(new Color(255, 255, 255));
		btnGraphSummary.setFocusPainted(false);
		btnGraphSummary.setToolTipText("Summary ");
		btnGraphSummary.setFont(new Font("Tahoma", Font.PLAIN, 15));

		// Home Button

		JButton btnNewButton = new JButton("Home");
		btnNewButton.setBounds(54, 38, 124, 37);
		panel.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				dashboard db = new dashboard(username, name);
				db.frmExpenseTracker.setVisible(true);
				incomeFrame.dispose();

			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setToolTipText("Home");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));

		// Expense

		JButton btnExpense = new JButton("Expense");
		btnExpense.setBounds(378, 38, 124, 37);
		panel.add(btnExpense);
		btnExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnExpense.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				expense exp = new expense(username, name);
				exp.expenseFrame.setVisible(true);
				incomeFrame.dispose();

			}
		});

		btnExpense.setBackground(new Color(255, 255, 255));
		btnExpense.setFocusPainted(false);
		btnExpense.setToolTipText("See your Monthly Expense");
		btnExpense.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton btnSavings = new JButton("Savings");
		btnSavings.setBounds(539, 38, 124, 37);
		panel.add(btnSavings);

		btnSavings.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (btnSavings.isEnabled()) {

					savings sv = new savings(username, name);
					sv.savings.setVisible(true);
					incomeFrame.dispose();
				}

			}
		});

		btnSavings.setToolTipText("See your Monthly Savings");
		btnSavings.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSavings.setFocusPainted(false);
		btnSavings.setBackground(Color.WHITE);

		// Income Button

		JButton btnIncome = new JButton("Income");
		btnIncome.setBounds(214, 38, 124, 37);
		panel.add(btnIncome);
		btnIncome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIncome.setBackground(new Color(255, 255, 255));
		btnIncome.setFocusPainted(false);
		btnIncome.setToolTipText("See your Monthly Income\r\n");
		btnIncome.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setEnabled(false);
		scrollPane.setBounds(378, 86, 516, 279);
		incomeFrame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);

		scrollPane.setViewportView(table);

		class CenterCellRenderer extends DefaultTableCellRenderer {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel renderedLabel = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
						row, column);
				renderedLabel.setHorizontalAlignment(JLabel.CENTER);
				return renderedLabel;
			}
		}

		TableCellRenderer renderer = new CenterCellRenderer();
		table.setDefaultRenderer(Object.class, renderer);

		table.setModel(new DefaultTableModel(null,
				new String[] { "Income Name", "Details", "Amount", "Year", "Month", "Income Id", "username" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(186);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(56);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(0);
		table.getColumnModel().getColumn(3).setMinWidth(0);
		table.getColumnModel().getColumn(3).setMaxWidth(0);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(0);
		table.getColumnModel().getColumn(4).setMinWidth(0);
		table.getColumnModel().getColumn(4).setMaxWidth(0);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(0);
		table.getColumnModel().getColumn(5).setMinWidth(0);
		table.getColumnModel().getColumn(5).setMaxWidth(0);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(6).setPreferredWidth(0);
		table.getColumnModel().getColumn(6).setMinWidth(0);
		table.getColumnModel().getColumn(6).setMaxWidth(0);

		String currentDate = date.toString().substring(0, 10) + date.toString().substring(23, 28);
		JLabel lblNewLabel = new JLabel(currentDate);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(UIManager.getColor("CheckBox.interiorBackground"));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(77, 65, 219, 24);
		incomeFrame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Select Date ");
		lblNewLabel_1.setBounds(410, 38, 75, 14);
		incomeFrame.getContentPane().add(lblNewLabel_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(UIManager.getBorder("Button.border"));
		panel_1.setBounds(0, 117, 345, 173);
		incomeFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		/*----------------------------------------------------------------- Form -------------------------------------------------------------- */

		JLabel lblNewLabel_2 = new JLabel("Income Name");
		lblNewLabel_2.setBounds(30, 31, 81, 14);
		panel_1.add(lblNewLabel_2);

		TextField textField = new TextField();

		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (textField.getText().length() >= 25)
					e.consume();
			}
		});

		textField.setBounds(122, 29, 205, 22);
		panel_1.add(textField);
		TextField amountTf = new TextField();

		amountTf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (amountTf.getText().length() >= 10)
					e.consume();
			}
		});

		amountTf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == 8) {
					amountTf.setEditable(true);
				} else {
					amountTf.setEditable(false);
				}

			}

		});

		amountTf.setBounds(122, 70, 205, 22);
		panel_1.add(amountTf);

		TextField detailsTf = new TextField();
		detailsTf.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (detailsTf.getText().length() >= 50)
					e.consume();
			}
		});

		detailsTf.setBounds(122, 108, 205, 22);
		panel_1.add(detailsTf);

		JLabel amountLabel = new JLabel("Amount");
		amountLabel.setBounds(58, 73, 55, 14);
		panel_1.add(amountLabel);

		JLabel detailsLabel = new JLabel("Details");
		detailsLabel.setBounds(62, 112, 55, 14);
		panel_1.add(detailsLabel);

		Choice month_selection = new Choice();
		month_selection.setBounds(122, 150, 100, 20);
		panel_1.add(month_selection);

		Choice year_selection = new Choice();
		year_selection.setBounds(240, 150, 60, 20);
		panel_1.add(year_selection);

		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(70, 155, 55, 14);
		panel_1.add(lblDate);
		year_selection.add("2022");
		year_selection.add("2023");
		year_selection.add("2024");
		year_selection.add("2025");
		year_selection.add("2026");
		year_selection.add("2027");
		year_selection.add("2028");
		year_selection.add("2029");
		year_selection.add("2030");
		year_selection.add("2031");

		String currentYearForm = date.toString().substring(24, 28);
		int indexOfYearForm = 0;

		switch (currentYearForm) {
		case "2022":
			indexOfYearForm = 0;
			break;
		case "2023":
			indexOfYearForm = 1;
			break;
		case "2024":
			indexOfYearForm = 2;
			break;
		case "2025":
			indexOfYearForm = 3;
			break;
		case "2026":
			indexOfYearForm = 4;
			break;
		case "2027":
			indexOfYearForm = 5;
			break;
		case "2028":
			indexOfYearForm = 6;
			break;
		case "2029":
			indexOfYearForm = 7;
			break;
		case "2030":
			indexOfYearForm = 8;
			break;
		case "2031":
			indexOfYearForm = 9;
			break;
		case "2032":
			indexOfYearForm = 10;
			break;
		}

		year_selection.select(indexOfYearForm);
		month_selection.add("January");
		month_selection.add("February");
		month_selection.add("March");
		month_selection.add("April");
		month_selection.add("May");
		month_selection.add("June");
		month_selection.add("July");
		month_selection.add("August");
		month_selection.add("September");
		month_selection.add("October");
		month_selection.add("November");
		month_selection.add("December");

		String currentMonthForm = date.toString().substring(4, 7);
		int indexOfMonthForm = 0;

		switch (currentMonthForm) {
		case "Jan":
			indexOfMonthForm = 0;
			break;
		case "Feb":
			indexOfMonthForm = 1;
			break;
		case "Mar":
			indexOfMonthForm = 2;
			break;
		case "Apr":
			indexOfMonthForm = 3;
			break;
		case "May":
			indexOfMonthForm = 4;
			break;
		case "Jun":
			indexOfMonthForm = 5;
			break;
		case "Jul":
			indexOfMonthForm = 6;
			break;
		case "Aug":
			indexOfMonthForm = 7;
			break;
		case "Sep":
			indexOfMonthForm = 8;
			break;
		case "Oct":
			indexOfMonthForm = 9;
			break;
		case "Nov":
			indexOfMonthForm = 10;
			break;
		case "Dec":
			indexOfMonthForm = 11;
			break;
		}

		month_selection.select(indexOfMonthForm);

		/*----------------------------------------------------------------- CRUD -------------------------------------------------------------- */

		/* ------- SQL CONNECTION --------- */

		/*-------------------------------------------------------------- Add Income -----------------------------------------------------------	 */

		JButton btnAdd = new JButton("Add Income");
		btnAdd.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (username != null) {

					try {

						String incomeNameField = textField.getText();
						String incomeAmountField = amountTf.getText();
						String incomeDetailsField = detailsTf.getText();
						String incomeMonthSelection = month_selection.getSelectedItem();
						String incomeYearSelection = year_selection.getSelectedItem();
						String usernameField = username;

						Class.forName("com.mysql.cj.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://localhost/expense_tracker", "root", "");
						insert = con.prepareStatement(
								"insert into income(income_name, income_amount, details, year_selection, month_selection, username) values(?, ?, ?, ?, ?, ?)");

						insert.setString(1, incomeNameField);
						insert.setString(2, incomeAmountField);
						insert.setString(3, incomeDetailsField);
						insert.setString(4, incomeYearSelection);
						insert.setString(5, incomeMonthSelection);
						insert.setString(6, usernameField);
						insert.executeUpdate();

						textField.setText("");
						amountTf.setText("");
						detailsTf.setText("");

						String currentMonthForm = date.toString().substring(4, 7);
						int indexOfMonthForm = 0;

						switch (currentMonthForm) {
						case "Jan":
							indexOfMonthForm = 0;
							break;
						case "Feb":
							indexOfMonthForm = 1;
							break;
						case "Mar":
							indexOfMonthForm = 2;
							break;
						case "Apr":
							indexOfMonthForm = 3;
							break;
						case "May":
							indexOfMonthForm = 4;
							break;
						case "Jun":
							indexOfMonthForm = 5;
							break;
						case "Jul":
							indexOfMonthForm = 6;
							break;
						case "Aug":
							indexOfMonthForm = 7;
							break;
						case "Sep":
							indexOfMonthForm = 8;
							break;
						case "Oct":
							indexOfMonthForm = 9;
							break;
						case "Nov":
							indexOfMonthForm = 10;
							break;
						case "Dec":
							indexOfMonthForm = 11;
							break;
						}

						month_selection.select(indexOfMonthForm);

						String currentYearForm = date.toString().substring(24, 28);
						int indexOfYearForm = 0;

						switch (currentYearForm) {
						case "2022":
							indexOfYearForm = 0;
							break;
						case "2023":
							indexOfYearForm = 1;
							break;
						case "2024":
							indexOfYearForm = 2;
							break;
						case "2025":
							indexOfYearForm = 3;
							break;
						case "2026":
							indexOfYearForm = 4;
							break;
						case "2027":
							indexOfYearForm = 5;
							break;
						case "2028":
							indexOfYearForm = 6;
							break;
						case "2029":
							indexOfYearForm = 7;
							break;
						case "2030":
							indexOfYearForm = 8;
							break;
						case "2031":
							indexOfYearForm = 9;
							break;
						case "2032":
							indexOfYearForm = 10;
							break;
						}

						year_selection.select(indexOfYearForm);

					} catch (ClassNotFoundException ex) {

						JOptionPane.showMessageDialog(incomeFrame, "Contact the administrator",
								"Something went wrong! ", JOptionPane.ERROR_MESSAGE);
					}

					catch (SQLException ex) {

						JOptionPane.showMessageDialog(incomeFrame, "Make sure to fill all the fields",
								"Something went wrong! ", JOptionPane.WARNING_MESSAGE);
					}

					table_load();

				}

				else {
					JOptionPane.showMessageDialog(incomeFrame, "Login first to create income tracker",
							"Something went wrong! ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnAdd.setBackground(SystemColor.text);
		btnAdd.setFocusPainted(false);
		btnAdd.setBounds(51, 322, 126, 23);
		incomeFrame.getContentPane().add(btnAdd);

		/*
		 * -------------------------------------------------------------- Edit Income
		 * -----------------------------------------------------------
		 */

		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				for (int row = 0; row < table.getRowCount(); row++) {

					textField.setText((String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 0));
					detailsTf.setText((String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 1));
					amountTf.setText((String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 2));
					month_selection.select((String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 3));
					year_selection.select((String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 4));

				}

				int selectedIndex = table.getSelectedRow();

				textField.setText(table.getValueAt(selectedIndex, 0).toString());
				detailsTf.setText(table.getValueAt(selectedIndex, 1).toString());
				amountTf.setText(table.getValueAt(selectedIndex, 2).toString());
				month_selection.select(table.getValueAt(selectedIndex, 3).toString());
				year_selection.select(table.getValueAt(selectedIndex, 4).toString());

			}
		});

		JButton viewGraph = new JButton("Filter Selected Date");
		viewGraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DefaultTableModel model = (DefaultTableModel) table.getModel();

				final TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);

				table.setRowSorter(sorter);

				String yearFilter = year.getSelectedItem();
				String monthFilter = month.getSelectedItem();

				List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(2);

				filters.add(RowFilter.regexFilter(yearFilter));
				filters.add(RowFilter.regexFilter(monthFilter));

				RowFilter<Object, Object> rf = RowFilter.andFilter(filters);
				sorter.setRowFilter(rf);

				table_load();

			}
		});

		viewGraph.setFocusPainted(false);
		viewGraph.setBackground(Color.WHITE);
		viewGraph.setBounds(680, 34, 175, 23);
		incomeFrame.getContentPane().add(viewGraph);

		/*
		 * ----------------------------------------------- --------------- Delete Income
		 * -----------------------------------------------------------
		 */

		JButton btnDelete = new JButton("Delete Income");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PreparedStatement delete;

				DefaultTableModel df = (DefaultTableModel) table.getModel();

				int selectedIndex = table.getSelectedRow();

				try {

					int income_id = Integer.parseInt(df.getValueAt(selectedIndex, 5).toString());

					int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Delete this record",
							"Warning", JOptionPane.YES_NO_OPTION);

					if (dialogResult == JOptionPane.YES_OPTION) {

						Class.forName("com.mysql.cj.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://localhost/expense_tracker", "root", "");
						delete = con.prepareStatement("delete from income where income_id = ? ");

						delete.setInt(1, income_id);

						delete.executeUpdate();

						JOptionPane.showMessageDialog(incomeFrame, "Income Deleted", "Delete Income",
								JOptionPane.INFORMATION_MESSAGE);

					}

				} catch (ClassNotFoundException ex) {

					ex.getStackTrace();
					ex.getMessage();
				}

				catch (SQLException ex) {

					ex.getStackTrace();
					ex.getMessage();
				}

				catch (ArrayIndexOutOfBoundsException ex) {

					JOptionPane.showMessageDialog(incomeFrame,
							"Make sure to double click rows to select before clicking delete", "Something went wrong! ",
							JOptionPane.ERROR_MESSAGE);
				}

				table_load();

			}

		});
		btnDelete.setFocusPainted(false);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(678, 380, 126, 23);
		incomeFrame.getContentPane().add(btnDelete);

		JButton btnEdit = new JButton("Update Income");
		btnEdit.setBounds(483, 380, 126, 23);
		incomeFrame.getContentPane().add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				PreparedStatement update;

				DefaultTableModel df = (DefaultTableModel) table.getModel();

				int selectedIndex = table.getSelectedRow();

				try {

					int income_id = Integer.parseInt(df.getValueAt(selectedIndex, 5).toString());

					String income_name = textField.getText();
					String details = detailsTf.getText();
					String income_amount = amountTf.getText();
					String year = year_selection.getSelectedItem();
					String month = month_selection.getSelectedItem();

					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost/expense_tracker", "root", "");
					update = con.prepareStatement("update income set income_name = ?,"
							+ "details = ?, income_amount = ?, year_selection = ?, month_selection = ? where income_id = ? ");

					update.setString(1, income_name);
					update.setString(2, details);
					update.setString(3, income_amount);
					update.setString(4, year);
					update.setString(5, month);
					update.setInt(6, income_id);

					update.executeUpdate();

					JOptionPane.showMessageDialog(incomeFrame, "Record Updated", "Update Data",
							JOptionPane.INFORMATION_MESSAGE);

					textField.setText("");
					amountTf.setText("");
					detailsTf.setText("");

					String currentMonthForm = date.toString().substring(4, 7);
					int indexOfMonthForm = 0;

					switch (currentMonthForm) {
					case "Jan":
						indexOfMonthForm = 0;
						break;
					case "Feb":
						indexOfMonthForm = 1;
						break;
					case "Mar":
						indexOfMonthForm = 2;
						break;
					case "Apr":
						indexOfMonthForm = 3;
						break;
					case "May":
						indexOfMonthForm = 4;
						break;
					case "Jun":
						indexOfMonthForm = 5;
						break;
					case "Jul":
						indexOfMonthForm = 6;
						break;
					case "Aug":
						indexOfMonthForm = 7;
						break;
					case "Sep":
						indexOfMonthForm = 8;
						break;
					case "Oct":
						indexOfMonthForm = 9;
						break;
					case "Nov":
						indexOfMonthForm = 10;
						break;
					case "Dec":
						indexOfMonthForm = 11;
						break;
					}

					month_selection.select(indexOfMonthForm);

					String currentYearForm = date.toString().substring(24, 28);
					int indexOfYearForm = 0;

					switch (currentYearForm) {
					case "2022":
						indexOfYearForm = 0;
						break;
					case "2023":
						indexOfYearForm = 1;
						break;
					case "2024":
						indexOfYearForm = 2;
						break;
					case "2025":
						indexOfYearForm = 3;
						break;
					case "2026":
						indexOfYearForm = 4;
						break;
					case "2027":
						indexOfYearForm = 5;
						break;
					case "2028":
						indexOfYearForm = 6;
						break;
					case "2029":
						indexOfYearForm = 7;
						break;
					case "2030":
						indexOfYearForm = 8;
						break;
					case "2031":
						indexOfYearForm = 9;
						break;
					case "2032":
						indexOfYearForm = 10;
						break;
					}

					year_selection.select(indexOfYearForm);

//		

				} catch (ClassNotFoundException ex) {

					ex.getStackTrace();
					ex.getMessage();
				}

				catch (SQLException ex) {

					ex.getStackTrace();
					ex.getMessage();
				}

				catch (ArrayIndexOutOfBoundsException ex) {

					JOptionPane.showMessageDialog(incomeFrame,
							"Make sure to double click row to select before clicking update", "Something went wrong! ",
							JOptionPane.ERROR_MESSAGE);
				}

				table_load();

			}
		});

		btnEdit.setFocusPainted(false);
		btnEdit.setBackground(Color.WHITE);

		JLabel icon = new JLabel("");

		Image img = new ImageIcon(this.getClass().getResource("/clubs.png")).getImage();
		Image newimg = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);

		icon.setIcon(new ImageIcon(newimg));
		icon.setBounds(33, 35, 46, 54);
		incomeFrame.getContentPane().add(icon);

		JLabel calculatorLabel = new JLabel("Calculator ");
		calculatorLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		calculatorLabel.setBackground(UIManager.getColor("Button.highlight"));
		calculatorLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				calculator clc = new calculator();
				clc.setVisible(true);

			}
		});
		calculatorLabel.setBounds(10, 383, 93, 31);
		incomeFrame.getContentPane().add(calculatorLabel);

		Image calc = new ImageIcon(this.getClass().getResource("/calculator.png")).getImage();
		Image newCacl = calc.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);

		calculatorLabel.setIcon(new ImageIcon(newCacl));

		JButton btnResetFields = new JButton("Reset Fields");
		btnResetFields.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
				detailsTf.setText("");
				amountTf.setText("");
				String currentMonthForm = date.toString().substring(4, 7);
				int indexOfMonthForm = 0;

				switch (currentMonthForm) {
				case "Jan":
					indexOfMonthForm = 0;
					break;
				case "Feb":
					indexOfMonthForm = 1;
					break;
				case "Mar":
					indexOfMonthForm = 2;
					break;
				case "Apr":
					indexOfMonthForm = 3;
					break;
				case "May":
					indexOfMonthForm = 4;
					break;
				case "Jun":
					indexOfMonthForm = 5;
					break;
				case "Jul":
					indexOfMonthForm = 6;
					break;
				case "Aug":
					indexOfMonthForm = 7;
					break;
				case "Sep":
					indexOfMonthForm = 8;
					break;
				case "Oct":
					indexOfMonthForm = 9;
					break;
				case "Nov":
					indexOfMonthForm = 10;
					break;
				case "Dec":
					indexOfMonthForm = 11;
					break;
				}

				month_selection.select(indexOfMonthForm);

				String currentYearForm = date.toString().substring(24, 28);
				int indexOfYearForm = 0;

				switch (currentYearForm) {
				case "2022":
					indexOfYearForm = 0;
					break;
				case "2023":
					indexOfYearForm = 1;
					break;
				case "2024":
					indexOfYearForm = 2;
					break;
				case "2025":
					indexOfYearForm = 3;
					break;
				case "2026":
					indexOfYearForm = 4;
					break;
				case "2027":
					indexOfYearForm = 5;
					break;
				case "2028":
					indexOfYearForm = 6;
					break;
				case "2029":
					indexOfYearForm = 7;
					break;
				case "2030":
					indexOfYearForm = 8;
					break;
				case "2031":
					indexOfYearForm = 9;
					break;
				case "2032":
					indexOfYearForm = 10;
					break;
				}

				year_selection.select(indexOfYearForm);

			}
		});
		btnResetFields.setFocusPainted(false);
		btnResetFields.setBackground(Color.WHITE);
		btnResetFields.setBounds(188, 322, 126, 23);
		incomeFrame.getContentPane().add(btnResetFields);

	}
}
