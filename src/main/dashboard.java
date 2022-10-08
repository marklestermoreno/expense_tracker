package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JTextPane;
import java.awt.Font;

//import javax.swing.ImageIcon;
//import java.awt.Image;

import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class dashboard {

	JFrame frmExpenseTracker;
	JLabel lblUserName;

	static String username;
	static String name;

	/**
	 * Launch the application.
	 */

	public void run() {
		try {
			dashboard window = new dashboard(username, name);
			window.frmExpenseTracker.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 * 
	 * @param string
	 */

	@SuppressWarnings("static-access")
	public dashboard(String username, String name) {
		this.name = name;
		this.username = username;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {

		java.util.Date date = new java.util.Date();

		frmExpenseTracker = new JFrame();
		frmExpenseTracker.getContentPane().setBackground(UIManager.getColor("CheckBox.interiorBackground"));
		frmExpenseTracker.setTitle("Java Rice");
		frmExpenseTracker.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\ML Moreno Files\\Pictures\\ico.png"));
		frmExpenseTracker.setBounds(100, 100, 866, 498);
		frmExpenseTracker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExpenseTracker.getContentPane().setLayout(null);
		frmExpenseTracker.setResizable(false);

		JTextPane txtpnWelcome = new JTextPane();
		txtpnWelcome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnWelcome.setBackground(UIManager.getColor("CheckBox.interiorBackground"));

		if (username != null) {
			txtpnWelcome.setText("Welcome " + name);
		}

		else {
			txtpnWelcome.setText("Please Login");
		}
		txtpnWelcome.setBounds(42, 45, 544, 33);
		frmExpenseTracker.getContentPane().add(txtpnWelcome);

		JTextPane txtpnExpenseTracker = new JTextPane();
		txtpnExpenseTracker.setBackground(UIManager.getColor("CheckBox.interiorBackground"));
		txtpnExpenseTracker.setFont(new Font("Tahoma", Font.BOLD, 30));
		txtpnExpenseTracker.setText("EXPENSE TRACKER");
		txtpnExpenseTracker.setBounds(42, 140, 332, 55);
		frmExpenseTracker.getContentPane().add(txtpnExpenseTracker);

		JTextPane txtpnDesigning = new JTextPane();
		txtpnDesigning.setBackground(UIManager.getColor("CheckBox.interiorBackground"));
		txtpnDesigning.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnDesigning.setText("Designing an Expense Tracker with Java Programming.");
		txtpnDesigning.setBounds(42, 210, 412, 33);
		frmExpenseTracker.getContentPane().add(txtpnDesigning);

		JTextPane txtpnThisWillAllow = new JTextPane();
		txtpnThisWillAllow.setBackground(UIManager.getColor("CheckBox.interiorBackground"));
		txtpnThisWillAllow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnThisWillAllow.setText(
				"This will allow end-users to track their expenses through their day-to-day basic and allowing them to computer their savings, incomes or other forms of money.");
		txtpnThisWillAllow.setBounds(42, 250, 412, 70);
		frmExpenseTracker.getContentPane().add(txtpnThisWillAllow);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(57, 57, 57));
		panel.setBounds(616, 0, 234, 459);
		frmExpenseTracker.getContentPane().add(panel);
		panel.setLayout(null);

		// Home Button

//		Image home = new ImageIcon(this.getClass().getResource("/home.png")).getImage();
//		Image newimg = home.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);

		JButton btnNewButton = new JButton("Home");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setToolTipText("Home");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(57, 45, 124, 37);
		panel.add(btnNewButton);

		// Income Button

//		Image income = new ImageIcon(this.getClass().getResource("/income.png")).getImage();
//		Image newIncomeImg = income.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);

		JButton btnIncome = new JButton("Income");

		btnIncome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				income inc = new income(username, name);
				inc.incomeFrame.setVisible(true);
				frmExpenseTracker.dispose();

			}
		});
		btnIncome.setBackground(new Color(255, 255, 255));
		btnIncome.setFocusPainted(false);
		btnIncome.setToolTipText("See your Monthly Income\r\n");
		btnIncome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnIncome.setBounds(57, 125, 124, 37);
		panel.add(btnIncome);

		// Expense

//		Image expense = new ImageIcon(this.getClass().getResource("/expense.png")).getImage();
//		Image newExpenseImg = expense.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);

		JButton btnExpense = new JButton("Expense");

		btnExpense.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				expense exp = new expense(username, name);
				exp.expenseFrame.setVisible(true);
				frmExpenseTracker.dispose();

			}
		});
		btnExpense.setBackground(new Color(255, 255, 255));
		btnExpense.setFocusPainted(false);
		btnExpense.setToolTipText("See your Monthly Expense");
		btnExpense.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExpense.setBounds(57, 205, 124, 37);
		panel.add(btnExpense);

		// Graph

		JButton btnGraphSummary = new JButton("Profile");

		btnGraphSummary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				graph grp = new graph(username, name);
				grp.graph.setVisible(true);
				frmExpenseTracker.dispose();

			}
		});
		btnGraphSummary.setBackground(new Color(255, 255, 255));
		btnGraphSummary.setFocusPainted(false);
		btnGraphSummary.setToolTipText("Summary ");
		btnGraphSummary.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGraphSummary.setBounds(57, 365, 124, 37);
		panel.add(btnGraphSummary);

		JButton btnSavings = new JButton("Savings");

		btnSavings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				savings sv = new savings(username, name);
				sv.savings.setVisible(true);
				frmExpenseTracker.dispose();

			}
		});

		btnSavings.setToolTipText("See your Monthly Savings");
		btnSavings.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSavings.setFocusPainted(false);
		btnSavings.setBackground(Color.WHITE);
		btnSavings.setBounds(57, 285, 124, 37);
		panel.add(btnSavings);

		String currentDate = date.toString().substring(0, 10) + date.toString().substring(23, 28);
		JLabel lblNewLabel = new JLabel(currentDate);
		lblNewLabel.setBounds(47, 75, 124, 24);
		frmExpenseTracker.getContentPane().add(lblNewLabel);
		lblNewLabel.setBackground(UIManager.getColor("CheckBox.interiorBackground"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setForeground(UIManager.getColor("CheckBox.interiorBackground"));

		if (username != null) {

			JButton signout = new JButton("Logout");
			signout.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					Login login = new Login();
					login.setVisible(true);
					frmExpenseTracker.dispose();
				}
			});
			signout.setFont(new Font("Tahoma", Font.PLAIN, 13));
			signout.setBackground(SystemColor.window);
			signout.setFocusPainted(false);
			signout.setBounds(46, 340, 101, 33);
			frmExpenseTracker.getContentPane().add(signout);

		}

		else {
			JButton signout = new JButton("Login");
			signout.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					Login login = new Login();
					login.setVisible(true);
					frmExpenseTracker.dispose();
				}
			});
			signout.setFont(new Font("Tahoma", Font.PLAIN, 13));
			signout.setBackground(SystemColor.window);
			signout.setFocusPainted(false);
			signout.setBounds(46, 340, 101, 33);
			frmExpenseTracker.getContentPane().add(signout);
		}

	}
}
