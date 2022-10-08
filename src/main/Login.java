package main;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Login extends JFrame {

	JPanel LoginPane;
	private JTextField txtLFUsername;
	private JPasswordField txtLFPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static boolean validatePassword(String originalPassword, String storedPassword)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String[] parts = storedPassword.split(":");
		int iterations = Integer.parseInt(parts[0]);

		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);

		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] testHash = skf.generateSecret(spec).getEncoded();

		int diff = hash.length ^ testHash.length;
		for (int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}

	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
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
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\ML Moreno Files\\Downloads\\clubs.png"));
		setType(Type.UTILITY);
		setTitle("Expense Tracker Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 473);
		LoginPane = new JPanel();
		LoginPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(LoginPane);
		LoginPane.setLayout(null);

		txtLFUsername = new JTextField();
		txtLFUsername.setBounds(62, 178, 330, 32);
		LoginPane.add(txtLFUsername);
		txtLFUsername.setColumns(10);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(38, 135, 157, 32);
		LoginPane.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(38, 221, 98, 32);
		LoginPane.add(lblPassword);

		JButton btnLFLogin = new JButton("Login");
		btnLFLogin.setFocusPainted(false);
		btnLFLogin.setBackground(new Color(255, 255, 255));
		btnLFLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				try {

					PreparedStatement view;

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/expense_tracker", "root", "");
					view = con.prepareStatement("Select * from registration where username = ? ");

					view.setString(1, txtLFUsername.getText());

					ResultSet rs = view.executeQuery();

					if (rs.next()) {

						String originalPassword = rs.getString("password");
				

						try {

							boolean matched = validatePassword(txtLFPassword.getText().toString(), originalPassword);

							if (matched) {

								new dashboard(rs.getString("username"), rs.getString("name")).frmExpenseTracker
										.setVisible(true);

								dispose();

							}
							
							else {
								
								JOptionPane.showMessageDialog(null, "Incorrect Password");
							}

						} catch (NoSuchAlgorithmException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (InvalidKeySpecException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

					} else {
						JOptionPane.showMessageDialog(null, "Incorrect Credentials");
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});

		btnLFLogin.setBounds(104, 345, 106, 32);
		LoginPane.add(btnLFLogin);

		JButton btnRegister = new JButton("Register");

		btnRegister.setFocusPainted(false);
		btnRegister.setBackground(new Color(255, 255, 255));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Registration_Form d = new Registration_Form();
				d.setVisible(true);
			}
		});
		btnRegister.setBounds(236, 345, 106, 32);
		LoginPane.add(btnRegister);

		txtLFPassword = new JPasswordField();
		txtLFPassword.setBounds(62, 264, 330, 32);
		LoginPane.add(txtLFPassword);

		JLabel icon = new JLabel("");

		Image img = new ImageIcon(this.getClass().getResource("/clubs.png")).getImage();
		Image newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);

		icon.setIcon(new ImageIcon(newimg));
		icon.setBounds(340, 34, 52, 71);
		LoginPane.add(icon);

		JLabel lblExpenseTracker = new JLabel("Expense Tracker");
		lblExpenseTracker.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpenseTracker.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblExpenseTracker.setBounds(104, 34, 234, 71);
		LoginPane.add(lblExpenseTracker);

		JLabel icon2 = new JLabel("");
		icon2.setBounds(38, 34, 76, 71);
		LoginPane.add(icon2);

		Image img2 = new ImageIcon(this.getClass().getResource("/clubs.png")).getImage();
		Image newimg2 = img2.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);

		icon2.setIcon(new ImageIcon(newimg2));
		icon2.setBounds(62, 34, 52, 71);
		LoginPane.add(icon);

	}
}
