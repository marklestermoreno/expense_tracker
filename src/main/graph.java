package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

public class graph {

	JFrame graph;
	private final ButtonGroup groupRadio = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	static String username, name;

	/**
	 * Launch the application.
	 */

	public void run() {
		try {
			graph window = new graph(username, name);
			window.graph.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 * 
	 * @param name2
	 * @param username2
	 */
	@SuppressWarnings("static-access")
	public graph(String username, String name) {
		this.username = username;
		this.name = name;
		initialize();
	}

	// Retrieve Data

	private Connection con;
	private PreparedStatement ps;

	static String email, address, mobilenumber, gender, password;
	static int userId;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;
	@SuppressWarnings("unused")
	private JRadioButton rbFemale, rbMale, rdbtnPreferNotTo;

	// Hash Password

	private static String generateStorngPasswordHash(String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		int iterations = 1000;
		char[] chars = password.toCharArray();
		byte[] salt = getSalt();

		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

		byte[] hash = skf.generateSecret(spec).getEncoded();
		return iterations + ":" + toHex(salt) + ":" + toHex(hash);
	}

	private static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	private static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);

		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 */

	@SuppressWarnings("unlikely-arg-type")
	private void initialize() {

		java.util.Date date = new java.util.Date();

		graph = new JFrame();
		graph.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\ML Moreno Files\\Pictures\\ico.png"));
		graph.setTitle("Java Rice");
		graph.setBounds(100, 100, 920, 566);
		graph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graph.getContentPane().setLayout(null);
		graph.setResizable(false);

		String currentDate = date.toString().substring(0, 10) + date.toString().substring(23, 28);
		JLabel lblNewLabel = new JLabel(currentDate);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(UIManager.getColor("CheckBox.interiorBackground"));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(75, 65, 219, 24);
		graph.getContentPane().add(lblNewLabel);

		JTextPane txtpnWelcome = new JTextPane();
		txtpnWelcome.setEditable(false);
		txtpnWelcome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnWelcome.setBackground(UIManager.getColor("CheckBox.interiorBackground"));
		txtpnWelcome.setText("Your Personal Profile");
		txtpnWelcome.setBounds(85, 35, 240, 31);
		graph.getContentPane().add(txtpnWelcome);

		// Graph

		JPanel panel = new JPanel();
		panel.setBackground(new Color(57, 57, 57));
		panel.setBounds(0, 425, 904, 102);
		graph.getContentPane().add(panel);
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
				graph.dispose();

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
				graph.dispose();

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

		btnExpense.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				expense exp = new expense(username, name);
				exp.expenseFrame.setVisible(true);
				graph.dispose();

			}
		});

		btnExpense.setBackground(new Color(255, 255, 255));
		btnExpense.setFocusPainted(false);
		btnExpense.setToolTipText("See your Monthly Expense");
		btnExpense.setFont(new Font("Tahoma", Font.PLAIN, 15));

		// Savings

		JButton btnSavings = new JButton("Savings");
		btnSavings.setBounds(539, 38, 124, 37);
		panel.add(btnSavings);

		btnSavings.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				savings sv = new savings(username, name);
				sv.savings.setVisible(true);
				graph.dispose();

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

				income sv = new income(username, name);
				sv.incomeFrame.setVisible(true);
				graph.dispose();
			}

		});
		btnIncome.setBackground(new Color(255, 255, 255));
		btnIncome.setFocusPainted(false);
		btnIncome.setToolTipText("See your Monthly Income\r\n");
		btnIncome.setFont(new Font("Tahoma", Font.PLAIN, 15));

		/*----------------------------------------------------------------- CRUD -------------------------------------------------------------- */

//
//

		JLabel icon = new JLabel("");

		Image img = new ImageIcon(this.getClass().getResource("/clubs.png")).getImage();
		Image newimg = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);

		icon.setIcon(new ImageIcon(newimg));
		icon.setBounds(33, 35, 46, 54);
		graph.getContentPane().add(icon);

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/expense_tracker", "root", "");
			ps = con.prepareStatement("select * from registration where username = ? ");

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				JLabel lblNewLabel_1 = new JLabel("Full Name");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblNewLabel_1.setBounds(75, 126, 75, 14);
				graph.getContentPane().add(lblNewLabel_1);

				textField = new JTextField();
				textField.setColumns(10);
				textField.setBounds(160, 122, 316, 27);
				textField.setText(rs.getString("name"));
				textField.setEnabled(false);
				graph.getContentPane().add(textField);

				JLabel lblMobileNumber = new JLabel("Mobile Number");
				lblMobileNumber.setHorizontalAlignment(SwingConstants.RIGHT);
				lblMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblMobileNumber.setBounds(10, 174, 127, 14);
				graph.getContentPane().add(lblMobileNumber);

				textField_1 = new JTextField();
				textField_1.setColumns(10);
				textField_1.setBounds(160, 170, 254, 27);
				textField_1.setText(rs.getString("mobilenumber"));
				graph.getContentPane().add(textField_1);

				JLabel lblEmailAddress = new JLabel("Email Address");
				lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblEmailAddress.setBounds(460, 176, 109, 14);
				graph.getContentPane().add(lblEmailAddress);

				textField_2 = new JTextField();
				textField_2.setColumns(10);
				textField_2.setBounds(562, 170, 299, 27);
				textField_2.setText(rs.getString("email"));
				graph.getContentPane().add(textField_2);

				JLabel lblAddress = new JLabel("Address");
				lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
				lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblAddress.setBounds(61, 217, 75, 14);
				graph.getContentPane().add(lblAddress);

				textField_3 = new JTextField();
				textField_3.setColumns(10);
				textField_3.setBounds(160, 213, 703, 27);
				textField_3.setText(rs.getString("address"));
				graph.getContentPane().add(textField_3);

				JLabel lblGender = new JLabel("Gender");
				lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
				lblGender.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblGender.setBounds(33, 264, 104, 14);
				graph.getContentPane().add(lblGender);

				JRadioButton rbMale = new JRadioButton("Male");
				rbMale.setFont(new Font("Tahoma", Font.PLAIN, 15));
				rbMale.setFocusable(false);
				rbMale.setBounds(164, 260, 75, 23);
				graph.getContentPane().add(rbMale);

				JRadioButton rbFemale = new JRadioButton("Female");
				rbFemale.setFont(new Font("Tahoma", Font.PLAIN, 15));
				rbFemale.setFocusable(false);
				rbFemale.setBounds(241, 260, 89, 23);
				graph.getContentPane().add(rbFemale);

				JRadioButton rdbtnPreferNotTo = new JRadioButton("Prefer not to say");
				rdbtnPreferNotTo.setFont(new Font("Tahoma", Font.PLAIN, 15));
				rdbtnPreferNotTo.setFocusPainted(false);
				rdbtnPreferNotTo.setBounds(332, 260, 152, 23);
				graph.getContentPane().add(rdbtnPreferNotTo);

				if (rbMale.getText().equals(rs.getString("gender"))) {

					rbMale.setSelected(true);

				}

				else if (rbFemale.getText().equals(rs.getString("gender"))) {

					rbFemale.setSelected(true);

				}

				else if (rdbtnPreferNotTo.getText().equals(rs.getString("gender"))) {

					rdbtnPreferNotTo.setSelected(true);

				}

				groupRadio.add(rdbtnPreferNotTo);
				groupRadio.add(rbFemale);
				groupRadio.add(rbMale);

				rbMale.setActionCommand(rbMale.getText());
				rbFemale.setActionCommand(rbFemale.getText());
				rdbtnPreferNotTo.setActionCommand(rdbtnPreferNotTo.getText());

				JLabel lblUsername = new JLabel("Username");
				lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
				lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblUsername.setBounds(50, 311, 89, 14);
				graph.getContentPane().add(lblUsername);

				textField_4 = new JTextField();
				textField_4.setColumns(10);
				textField_4.setBounds(159, 307, 704, 27);
				textField_4.setText(rs.getString("username"));
				textField_4.setEnabled(false);
				graph.getContentPane().add(textField_4);

				JLabel lblPassword = new JLabel("Password");
				lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblPassword.setBounds(75, 362, 75, 14);
				graph.getContentPane().add(lblPassword);

				passwordField = new JPasswordField();
				passwordField.setEnabled(false);
				passwordField.setBounds(160, 354, 703, 27);
				graph.getContentPane().add(passwordField);

				userId = rs.getInt("user_id");
				password = rs.getString("password");

			}

		} catch (

		ClassNotFoundException ex) {

			ex.getStackTrace();
			ex.getMessage();
		}

		catch (SQLException ex) {

			ex.getStackTrace();
			ex.getMessage();
		}

		if (username != null) {
			JButton btnNewButton_1_1 = new JButton("Update");

			// On and Off

			JRadioButton rdbtnNo = new JRadioButton("On");
			rdbtnNo.setFocusPainted(false);
			rdbtnNo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					passwordField.setEnabled(true);
				}
			});
			buttonGroup_1.add(rdbtnNo);
			rdbtnNo.setFont(new Font("Tahoma", Font.BOLD, 12));
			rdbtnNo.setBounds(154, 390, 49, 23);
			graph.getContentPane().add(rdbtnNo);

			JRadioButton rdbtnOff = new JRadioButton("Off");
			rdbtnOff.setFocusPainted(false);
			rdbtnOff.setSelected(true);
			buttonGroup_1.add(rdbtnOff);
			rdbtnOff.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					passwordField.setEnabled(false);
				}
			});
			
			rdbtnOff.setFont(new Font("Tahoma", Font.BOLD, 12));
			rdbtnOff.setBounds(205, 390, 49, 23);
			graph.getContentPane().add(rdbtnOff);

			btnNewButton_1_1.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {

					PreparedStatement update;

					if (textField_2.getText().length() >= 5 && textField_3.getText().length() >= 5
							&& textField_1.getText().length() >= 5) {

						try {

							Class.forName("com.mysql.cj.jdbc.Driver");
							con = DriverManager.getConnection("jdbc:mysql://localhost/expense_tracker", "root", "");
							ps = con.prepareStatement("select * from registration where username = ? ");

							ps.setString(1, username);
							ResultSet rs = ps.executeQuery();

							while (rs.next()) {

								if (rs.getInt("user_id") == userId) {

									update = con.prepareStatement(
											"update registration set email = ?, address = ?, mobilenumber = ?, gender = ?, password = ? where username = ?");

									update.setString(1, textField_2.getText());
									update.setString(2, textField_3.getText());
									update.setString(3, textField_1.getText());
									update.setString(4, groupRadio.getSelection().getActionCommand());

									if (rdbtnOff.isSelected()) {
										update.setString(5, password);
									}

									else if (rdbtnNo.isSelected()) {

										if (passwordField.getText().length() < 6) {

											JOptionPane.showMessageDialog(graph,
													"Password Must be atleast 6 characters", "Invalid Form",
													JOptionPane.ERROR_MESSAGE);

										}

										else {

											String originalPassword = passwordField.getText();
											String generatedSecuredPasswordHash;

											try {

												generatedSecuredPasswordHash = generateStorngPasswordHash(
														originalPassword);
												update.setString(5, generatedSecuredPasswordHash);

											} catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {

												// TODO Auto-generated catch block
												e1.printStackTrace();

											}

										}
									}

									update.setString(6, username);

									update.executeUpdate();
									con.setAutoCommit(true);

									JOptionPane.showMessageDialog(graph, "Profile Updated", "Update Data",
											+JOptionPane.INFORMATION_MESSAGE);

								}

								dashboard dc = new dashboard(username, name);
								dc.frmExpenseTracker.setVisible(true);
								graph.dispose();

							}

						}

						catch (NullPointerException ex) {

							JOptionPane.showMessageDialog(graph, "Select Gender", "Invalid Form",
									JOptionPane.ERROR_MESSAGE);

						}

						catch (ClassNotFoundException ex) {

							ex.getStackTrace(); // Taga print ng error

						}

						catch (SQLException ex) {

							ex.getStackTrace();

						}

					} else {
						JOptionPane.showMessageDialog(graph, "Tanginamo Eljhay");
					}

				}
			});
			btnNewButton_1_1.setForeground(new Color(255, 255, 255));
			btnNewButton_1_1.setToolTipText("Update Account");
			btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnNewButton_1_1.setFocusPainted(false);
			btnNewButton_1_1.setBackground(SystemColor.textHighlight);
			btnNewButton_1_1.setBounds(610, 35, 109, 31);
			graph.getContentPane().add(btnNewButton_1_1);

			JButton btnNewButton_1_1_1 = new JButton("Delete");
			btnNewButton_1_1_1.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {

					int input = JOptionPane.showConfirmDialog(null, "Do you want to delete your account?",
							"Select an Option...", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

					// 0=yes, 1=no, 2=cancel

					if (input == 0) {

						PreparedStatement delete, selectIncome, selectExpense, selectSavings;

						try {

							Class.forName("com.mysql.cj.jdbc.Driver");
							con = DriverManager.getConnection("jdbc:mysql://localhost/expense_tracker", "root", "");

							ps = con.prepareStatement("select * from registration where username = ? ");

							selectIncome = con.prepareStatement("select * from income where username = ? ");
							selectExpense = con.prepareStatement("select * from expense where username = ?");
							selectSavings = con.prepareStatement("select * from savings where username = ?");

							ps.setString(1, username);
							selectIncome.setString(1, username);
							selectExpense.setString(1, username);
							selectSavings.setString(1, username);

							ResultSet rs = ps.executeQuery();

							int dialogResult = JOptionPane.showConfirmDialog(null,
									"It will also delete every information including your income, expense and savings",
									"Warning", JOptionPane.YES_NO_OPTION);

							while (rs.next()) {

								int user_id = rs.getInt("user_id");

								if (dialogResult == JOptionPane.YES_OPTION) {

									Class.forName("com.mysql.cj.jdbc.Driver");
									con = DriverManager.getConnection("jdbc:mysql://localhost/expense_tracker", "root",
											"");

									// Delete Income

									if (username != null) {
										selectIncome = con.prepareStatement("delete from income where username = ? ");
										selectIncome.setString(1, username);
										selectIncome.executeUpdate();

									}

									// Delete Expense

									if (username != null) {
										selectExpense = con.prepareStatement("delete from expense where username = ?");
										selectExpense.setString(1, username);
										selectExpense.executeUpdate();

									}

									// Delete Savings
									if (username != null) {
										selectSavings = con.prepareStatement("delete from savings where username = ?");
										selectSavings.setString(1, username);
										selectSavings.executeUpdate();
									}

									// Delete User
									delete = con.prepareStatement("delete from registration where user_id = ? ");
									delete.setInt(1, user_id);
									delete.executeUpdate();

									JOptionPane.showMessageDialog(graph, "Account Deleted", "Delete User",
											JOptionPane.INFORMATION_MESSAGE);
								}

								Login login = new Login();
								login.setVisible(true);
								graph.dispose();
							}

						}

						catch (ClassNotFoundException ex) {

							ex.getStackTrace();
							ex.getMessage();
						}

						catch (SQLException ex) {

							ex.getStackTrace();
							ex.getMessage();
						}

					}

				}
			});
			btnNewButton_1_1_1.setToolTipText("Delete Account");
			btnNewButton_1_1_1.setForeground(Color.WHITE);
			btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnNewButton_1_1_1.setFocusPainted(false);
			btnNewButton_1_1_1.setBackground(Color.RED);
			btnNewButton_1_1_1.setBounds(735, 35, 109, 31);
			graph.getContentPane().add(btnNewButton_1_1_1);

		} else {
			JOptionPane.showMessageDialog(graph, "Login first", "Something went wrong! ", JOptionPane.ERROR_MESSAGE);
			Registration_Form reg = new Registration_Form();
			reg.setVisible(true);
		}

	}
}
