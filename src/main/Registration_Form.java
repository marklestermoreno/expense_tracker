package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class Registration_Form extends JFrame {

	private JPanel RegisterFrame;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JTextField txtMobile;
	private JRadioButton rbFemale;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */

	public void run() {
		try {
			Registration_Form frame = new Registration_Form();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isValidEmail(String email) {
		String emailFormate = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\."
				+ "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

		Pattern p = Pattern.compile(emailFormate);
		if (email == null) {
			return false;
		}
		return p.matcher(email).matches();
	}

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
	 * Create the frame.
	 */

	public Registration_Form() {
		setTitle("Expense Tracker Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 656);
		RegisterFrame = new JPanel();
		RegisterFrame.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(RegisterFrame);
		RegisterFrame.setLayout(null);

		txtName = new JTextField();
		txtName.setBounds(173, 143, 352, 27);
		RegisterFrame.add(txtName);
		txtName.setColumns(10);
		txtName.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (txtName.getText().length() >= 50)
					e.consume();
			}
		});

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(173, 248, 269, 27);
		txtEmail.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (txtEmail.getText().length() >= 50)
					e.consume();
			}
		});

		RegisterFrame.add(txtEmail);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(173, 300, 352, 27);
		txtAddress.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (txtEmail.getText().length() >= 150)
					e.consume();
			}
		});
		RegisterFrame.add(txtAddress);

		txtMobile = new JTextField();
		txtMobile.setColumns(10);
		txtMobile.setBounds(173, 193, 352, 27);
		txtMobile.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == 8) {
					txtMobile.setEditable(true);
				} else {
					txtMobile.setEditable(false);
				}

			}

			public void keyTyped(KeyEvent e) {
				if (txtMobile.getText().length() >= 11)
					e.consume();
			}

		});
		RegisterFrame.add(txtMobile);

		ButtonGroup g1 = new ButtonGroup();

		JRadioButton rbMale = new JRadioButton("Male");
		rbMale.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rbMale.setBounds(181, 354, 75, 23);
		rbMale.setFocusable(false);
		RegisterFrame.add(rbMale);

		rbFemale = new JRadioButton("Female");
		rbFemale.setFocusable(false);
		rbFemale.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rbFemale.setBounds(258, 354, 89, 23);
		RegisterFrame.add(rbFemale);

		JRadioButton rdbtnPreferNotTo = new JRadioButton("Prefer not to say");
		rdbtnPreferNotTo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnPreferNotTo.setBounds(363, 354, 152, 23);
		rdbtnPreferNotTo.setFocusPainted(false);
		RegisterFrame.add(rdbtnPreferNotTo);

		g1.add(rdbtnPreferNotTo);
		g1.add(rbFemale);
		g1.add(rbMale);

		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(173, 408, 352, 27);
		txtUsername.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (txtUsername.getText().length() >= 50)
					e.consume();
			}
		});

		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() >= '0' || e.getKeyCode() == 8) {
					txtUsername.setEditable(true);
				} else {
					txtUsername.setEditable(false);
				}

			}

		});
		RegisterFrame.add(txtUsername);
		txtPassword = new JPasswordField();
		txtPassword.setBounds(173, 456, 352, 27);
		txtPassword.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			public void keyTyped(KeyEvent e) {
				if (txtPassword.getText().length() >= 50)
					e.consume();
			}
		});
		RegisterFrame.add(txtPassword);

		JButton btnValidate = new JButton("Register");
		btnValidate.setFocusPainted(false);
		btnValidate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnValidate.setBackground(new Color(255, 255, 255));
		btnValidate.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				String emailFormate = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\."
						+ "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

				Pattern p = Pattern.compile(emailFormate);

				if (txtName.getText().length() >= 6 && txtAddress.getText().length() >= 6
						&& txtMobile.getText().length() >= 6) {

					if (txtPassword.getText().length() >= 6 && txtUsername.getText().length() >= 6) {

						if (p.matcher(txtEmail.getText()).matches()) {

							try {
								Class.forName("com.mysql.cj.jdbc.Driver");
								Connection con = DriverManager.getConnection("jdbc:mysql://localhost/expense_tracker",
										"root", "");
								String query = "insert into registration(name, email, address, mobilenumber, gender, username, password) values(?, ?, ?, ?, ?, ?, ?)";
								PreparedStatement ps = con.prepareStatement(query);

								ps.setString(1, txtName.getText());

								ps.setString(2, txtEmail.getText());
								ps.setString(3, txtAddress.getText());
								ps.setString(4, txtMobile.getText());

								if (rbMale.isSelected())

									ps.setString(5, rbMale.getText());

								else if (rbFemale.isSelected())

									ps.setString(5, rbFemale.getText());

								else if (rdbtnPreferNotTo.isSelected())

									ps.setString(5, rdbtnPreferNotTo.getText());

								else

									JOptionPane.showMessageDialog(null, "Select Gender", "Username error",
											JOptionPane.ERROR_MESSAGE);

								ps.setString(6, txtUsername.getText());
								
								String originalPassword = txtPassword.getText();
								String generatedSecuredPasswordHash;
								
								try {
									
									generatedSecuredPasswordHash = generateStorngPasswordHash(originalPassword);
									ps.setString(7, generatedSecuredPasswordHash);
									
								} catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
									
									// TODO Auto-generated catch block
									e1.printStackTrace();
									
								}
								
								

								ps.executeUpdate();
								JOptionPane.showMessageDialog(btnValidate, "Record Added");
								ps.close();
								con.close();

								txtName.setText("");
								txtMobile.setText("");
								txtEmail.setText("");
								txtAddress.setText("");
								txtUsername.setText("");
								txtPassword.setText("");
								g1.clearSelection();

								Login login = new Login();
								login.setVisible(true);
								dispose();

							} catch (SQLIntegrityConstraintViolationException e1) {

								JOptionPane.showMessageDialog(null, " Username is already registered", "Username error",
										JOptionPane.ERROR_MESSAGE);

							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(RegisterFrame, "Make sure to fill all the fields",
										"Something went wrong! ", JOptionPane.WARNING_MESSAGE);
							}

							catch (Exception e1) {
								e1.printStackTrace();
							}
						}

						else {
							JOptionPane.showMessageDialog(null, "Invalid Email", "Something went wrong! ",
									JOptionPane.ERROR_MESSAGE);
						}

					} else {

						JOptionPane.showMessageDialog(null, "Password and Username must be atleast 6 characters above",
								"Something went wrong! ", JOptionPane.ERROR_MESSAGE);
					}
				}

				else {

					JOptionPane.showMessageDialog(null, "All fields must be atleast 6 characters above",
							"Something went wrong! ", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnValidate.setBounds(114, 520, 99, 35);
		RegisterFrame.add(btnValidate);

		JButton btnReset = new JButton("Reset");
		btnReset.setFocusable(false);
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnReset.setBackground(new Color(255, 255, 255));
		btnReset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				txtName.setText("");
				txtEmail.setText("");
				txtMobile.setText("");
				txtAddress.setText("");
				txtPassword.setText("");
				txtUsername.setText("");
				g1.clearSelection();
			}
		});
		btnReset.setBounds(258, 520, 99, 35);
		RegisterFrame.add(btnReset);

		JLabel lblNewLabel = new JLabel("Full Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(88, 148, 75, 14);
		RegisterFrame.add(lblNewLabel);

		JLabel lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmailAddress.setBounds(60, 251, 109, 14);
		RegisterFrame.add(lblEmailAddress);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress.setBounds(97, 305, 75, 14);
		RegisterFrame.add(lblAddress);

		JLabel lblMobileNumber = new JLabel("Mobile Number");
		lblMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMobileNumber.setBounds(56, 198, 127, 14);
		RegisterFrame.add(lblMobileNumber);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGender.setBounds(103, 358, 75, 14);
		RegisterFrame.add(lblGender);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(82, 411, 89, 14);
		RegisterFrame.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(86, 460, 75, 14);
		RegisterFrame.add(lblPassword);

		JLabel lblExpenseTracker = new JLabel("Expense Tracker Registration");
		lblExpenseTracker.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpenseTracker.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblExpenseTracker.setBounds(0, 38, 616, 71);
		RegisterFrame.add(lblExpenseTracker);

		JButton btnLogin = new JButton("Login");
		btnLogin.setFocusPainted(false);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

				Login d = new Login();
				d.setVisible(true);

			}
		});
		btnLogin.setBounds(401, 520, 89, 35);
		RegisterFrame.add(btnLogin);

	}
}
