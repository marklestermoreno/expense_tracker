package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class calculator extends JFrame {

	private JPanel contentPane;
	private JTextField textField, textgHistory;

	double first, second, result;
	String operation, ans, getHistory, secondValue;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	/**
	 * Launch the application.
	 */

	public void run() {
		try {
			calculator frame = new calculator();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public calculator() {
		setResizable(false);
		setBackground(new Color(57, 57, 57));
		setType(Type.UTILITY);
		setTitle("Java Rice");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 307, 398);
		contentPane = new JPanel();
		contentPane.setForeground(Color.GRAY);
		contentPane.setBackground(new Color(239, 237, 237));
		contentPane.setBorder(new EmptyBorder(2, 1, 1, 2));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setForeground(new Color(57, 57, 57));
		textField.setBackground(new Color(242, 246, 250));
		textField.setFont(new Font("Tahoma", Font.BOLD, 22));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setEditable(false);
		textField.setBounds(10, 11, 267, 50);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnDel = new JButton("\uF0E7");
		btnDel.setEnabled(false);
		btnDel.setFocusPainted(false);
		btnDel.setForeground(new Color(0, 0, 0));
		btnDel.setBackground(new Color(239, 237, 237));
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String btnDel = null;
				if (textField.getText().length() > 0) {
					StringBuilder sr = new StringBuilder(textField.getText());
					sr.deleteCharAt(textField.getText().length() - 1);
					btnDel = sr.toString();
					textField.setText(btnDel);
				}
			}
		});
		btnDel.setFont(new Font("Dialog", Font.BOLD, 15));
		btnDel.setBounds(12, 139, 69, 35);
		contentPane.add(btnDel);

		JButton btnClear = new JButton("C");
		btnClear.setFocusPainted(false);
		btnClear.setEnabled(false);
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClear.setForeground(new Color(0, 0, 0));
		btnClear.setBackground(new Color(239, 237, 237));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
			}
		});
		btnClear.setBounds(80, 139, 69, 35);
		contentPane.add(btnClear);

		JButton btnAddSubs = new JButton("+/-");
		btnAddSubs.setFocusPainted(false);
		btnAddSubs.setEnabled(false);
		btnAddSubs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double a = Double.parseDouble(String.valueOf(textField.getText()));
					a = a * (-1);
					textField.setText(String.valueOf(a));
				} catch (NumberFormatException nfe) {
					textField.setText(" ");
				}
			}
		});
		btnAddSubs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddSubs.setBackground(new Color(57, 57, 57));
		btnAddSubs.setForeground(new Color(239, 237, 237));
		btnAddSubs.setBounds(148, 173, 69, 35);
		contentPane.add(btnAddSubs);

		JButton btn7 = new JButton("7");
		btn7.setFocusPainted(false);
		btn7.setEnabled(false);
		btn7.setBackground(new Color(242, 246, 250));
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText() + btn7.getText();
				textField.setText(number);
			}
		});
		btn7.setBounds(12, 208, 69, 35);
		contentPane.add(btn7);

		JButton btn8 = new JButton("8");
		btn8.setFocusPainted(false);
		btn8.setEnabled(false);
		btn8.setBackground(new Color(242, 246, 250));
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText() + btn8.getText();
				textField.setText(number);
			}
		});
		btn8.setBounds(80, 208, 69, 35);
		contentPane.add(btn8);

		JButton btn9 = new JButton("9");
		btn9.setEnabled(false);
		btn9.setFocusPainted(false);
		btn9.setBackground(new Color(242, 246, 250));
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText() + btn9.getText();
				textField.setText(number);
			}
		});
		btn9.setBounds(148, 208, 69, 35);
		contentPane.add(btn9);

		JButton btnAdd = new JButton("+");
		btnAdd.setFocusPainted(false);
		btnAdd.setEnabled(false);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setForeground(new Color(239, 237, 237));
		btnAdd.setBackground(new Color(57, 57, 57));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					first = Double.parseDouble(textField.getText());
					textField.setText(" ");
					operation = "+";
				} catch (NumberFormatException nfe) {
					textField.setText(" ");
				}
			}
		});
		btnAdd.setBounds(216, 173, 61, 35);
		contentPane.add(btnAdd);

		JButton btn4 = new JButton("4");
		btn4.setEnabled(false);
		btn4.setFocusPainted(false);
		btn4.setBackground(new Color(242, 246, 250));
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText() + btn4.getText();
				textField.setText(number);
			}
		});
		btn4.setBounds(12, 243, 69, 35);
		contentPane.add(btn4);

		JButton btn5 = new JButton("5");
		btn5.setFocusPainted(false);
		btn5.setEnabled(false);
		btn5.setBackground(new Color(242, 246, 250));
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText() + btn5.getText();
				textField.setText(number);
			}
		});
		btn5.setBounds(80, 243, 69, 35);
		contentPane.add(btn5);

		JButton btn6 = new JButton("6");
		btn6.setEnabled(false);
		btn6.setFocusPainted(false);
		btn6.setBackground(new Color(242, 246, 250));
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText() + btn6.getText();
				textField.setText(number);
			}
		});
		btn6.setBounds(148, 243, 69, 35);
		contentPane.add(btn6);

		JButton btnSubstract = new JButton("-");
		btnSubstract.setFocusPainted(false);
		btnSubstract.setEnabled(false);
		btnSubstract.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubstract.setForeground(new Color(239, 237, 237));
		btnSubstract.setBackground(new Color(57, 57, 57));
		btnSubstract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					first = Double.parseDouble(textField.getText());
					textField.setText(" ");
					operation = "-";
				} catch (NumberFormatException nfe) {
					textField.setText(" ");
				}
			}
		});
		btnSubstract.setBounds(216, 208, 61, 35);
		contentPane.add(btnSubstract);

		JButton btn1 = new JButton("1");
		btn1.setEnabled(false);
		btn1.setFocusPainted(false);
		btn1.setBackground(new Color(242, 246, 250));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText() + btn1.getText();
				textField.setText(number);
			}
		});
		btn1.setBounds(12, 278, 69, 35);
		contentPane.add(btn1);

		JButton btn2 = new JButton("2");
		btn2.setEnabled(false);
		btn2.setFocusPainted(false);
		btn2.setBackground(new Color(242, 246, 250));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText() + btn2.getText();
				textField.setText(number);
			}
		});
		btn2.setBounds(80, 278, 69, 35);
		contentPane.add(btn2);

		JButton btn3 = new JButton("3");
		btn3.setEnabled(false);
		btn3.setFocusPainted(false);
		btn3.setBackground(new Color(242, 246, 250));
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText() + btn3.getText();
				textField.setText(number);
			}
		});
		btn3.setBounds(148, 278, 69, 35);
		contentPane.add(btn3);

		JButton btnMultiply = new JButton("*");
		btnMultiply.setEnabled(false);
		btnMultiply.setFocusPainted(false);
		btnMultiply.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnMultiply.setForeground(new Color(239, 237, 237));
		btnMultiply.setBackground(new Color(57, 57, 57));
		btnMultiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					first = Double.parseDouble(textField.getText());
					textField.setText(" ");
					operation = "*";
				} catch (NumberFormatException nfe) {
					textField.setText(" ");
				}
			}
		});
		btnMultiply.setBounds(216, 243, 61, 35);
		contentPane.add(btnMultiply);

		JButton btn0 = new JButton("0");
		btn0.setFocusPainted(false);
		btn0.setEnabled(false);
		btn0.setBackground(new Color(242, 246, 250));
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText() + btn0.getText();
				textField.setText(number);
			}
		});
		btn0.setBounds(12, 313, 69, 35);
		contentPane.add(btn0);

		JButton btnDot = new JButton(".");
		btnDot.setFocusPainted(false);
		btnDot.setEnabled(false);
		btnDot.setBackground(new Color(242, 246, 250));
		btnDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText() + btnDot.getText();
				textField.setText(number);
			}
		});
		btnDot.setBounds(80, 313, 69, 35);
		contentPane.add(btnDot);

		JButton btnTotal = new JButton("=");
		btnTotal.setFocusPainted(false);
		btnTotal.setEnabled(false);
		btnTotal.setBackground(new Color(242, 246, 250));
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String ans;

					second = Double.parseDouble(textField.getText());
					if (operation == "+") {
						result = first + second;
						ans = String.format("%.2f", result);
						secondValue = textField.getText();
						textField.setText(ans);

					} else if (operation == "-") {
						result = first - second;
						ans = String.format("%.2f", result);
						textField.setText(ans);

					} else if (operation == "*") {
						result = first * second;
						ans = String.format("%.2f", result);
						textField.setText(ans);

					} else if (operation == "/") {
						result = first / second;
						ans = String.format("%.2f", result);
						textField.setText(ans);

					} else if (operation == "%") {
						result = first % second;
						ans = String.format("%.2f", result);
						textField.setText(ans);

					} else if (operation == "x^y") {
						double result = 1;
						for (int i = 0; i < second; i++) {
							result = first * result;
						}
						ans = String.format("%.2f", result);
						textField.setText(ans);
					}
					getHistory = first + " " + operation + " " + second + " = " + result;
					textgHistory.setText(getHistory);

				} catch (NumberFormatException nfe) {
					textField.setText(" ");
				} catch (ClassCastException cce) {

				}
			}
		});
		btnTotal.setBounds(148, 313, 129, 35);
		contentPane.add(btnTotal);

		JButton btnDivide = new JButton("/");
		btnDivide.setEnabled(false);
		btnDivide.setFocusPainted(false);
		btnDivide.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDivide.setForeground(new Color(239, 237, 237));
		btnDivide.setBackground(new Color(57, 57, 57));
		btnDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					first = Double.parseDouble(textField.getText());
					textField.setText(" ");
					operation = "/";
				} catch (NumberFormatException nfe) {
					textField.setText(" ");
				}
			}
		});
		btnDivide.setBounds(216, 278, 61, 35);
		contentPane.add(btnDivide);

		JButton btnClearAll = new JButton("CA");
		btnClearAll.setFocusPainted(false);
		btnClearAll.setEnabled(false);
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				textgHistory.setText(null);
			}
		});
		btnClearAll.setForeground(Color.BLACK);
		btnClearAll.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClearAll.setBackground(new Color(239, 237, 237));
		btnClearAll.setBounds(148, 139, 129, 35);
		contentPane.add(btnClearAll);

		JButton btnPerc = new JButton("%");
		btnPerc.setFocusPainted(false);
		btnPerc.setEnabled(false);
		btnPerc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					first = Double.parseDouble(textField.getText());
					textField.setText(" ");
					operation = "%";
				} catch (NumberFormatException nfe) {
					textField.setText(" ");
				}
			}
		});
		btnPerc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPerc.setForeground(new Color(239, 237, 237));
		btnPerc.setBackground(new Color(57, 57, 57));
		btnPerc.setBounds(80, 173, 69, 35);
		contentPane.add(btnPerc);

		JButton btnXy = new JButton("x^y");
		btnXy.setFocusPainted(false);
		btnXy.setEnabled(false);
		btnXy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					first = Double.parseDouble(textField.getText());
					textField.setText(" ");
					operation = "x^y";
				} catch (NumberFormatException nfe) {
					textField.setText(" ");
				}
			}
		});
		btnXy.setForeground(new Color(239, 237, 237));
		btnXy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXy.setBackground(new Color(57, 57, 57));
		btnXy.setBounds(12, 173, 69, 35);
		contentPane.add(btnXy);

		JRadioButton rdbtnNo = new JRadioButton("On");
		rdbtnNo.setFocusPainted(false);
		rdbtnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn1.setEnabled(true);
				btn2.setEnabled(true);
				btn3.setEnabled(true);
				btn4.setEnabled(true);
				btn5.setEnabled(true);
				btn6.setEnabled(true);
				btn7.setEnabled(true);
				btn8.setEnabled(true);
				btn9.setEnabled(true);
				btn0.setEnabled(true);
				btnDot.setEnabled(true);
				btnTotal.setEnabled(true);
				btnAdd.setEnabled(true);
				btnSubstract.setEnabled(true);
				btnMultiply.setEnabled(true);
				btnDivide.setEnabled(true);
				btnAddSubs.setEnabled(true);
				btnDel.setEnabled(true);
				btnClear.setEnabled(true);
				btnXy.setEnabled(true);
				btnClearAll.setEnabled(true);
				btnPerc.setEnabled(true);
				textField.setEnabled(true);
			}
		});
		buttonGroup_1.add(rdbtnNo);
		rdbtnNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnNo.setBounds(10, 65, 49, 23);
		contentPane.add(rdbtnNo);

		JRadioButton rdbtnOff = new JRadioButton("Off");
		rdbtnOff.setFocusPainted(false);
		rdbtnOff.setSelected(true);
		buttonGroup_1.add(rdbtnOff);
		rdbtnOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn1.setEnabled(false);
				btn2.setEnabled(false);
				btn3.setEnabled(false);
				btn4.setEnabled(false);
				btn5.setEnabled(false);
				btn6.setEnabled(false);
				btn7.setEnabled(false);
				btn8.setEnabled(false);
				btn9.setEnabled(false);
				btn0.setEnabled(false);
				btnDot.setEnabled(false);
				btnTotal.setEnabled(false);
				btnAdd.setEnabled(false);
				btnSubstract.setEnabled(false);
				btnMultiply.setEnabled(false);
				btnDivide.setEnabled(false);
				btnAddSubs.setEnabled(false);
				btnDel.setEnabled(false);
				btnClear.setEnabled(false);
				btnXy.setEnabled(false);
				btnClearAll.setEnabled(false);
				btnPerc.setEnabled(false);
				textField.setEnabled(false);
			}
		});
		rdbtnOff.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnOff.setBounds(10, 91, 49, 23);
		contentPane.add(rdbtnOff);

		JPanel panel = new JPanel();
		panel.setBounds(65, 65, 210, 70);
		contentPane.add(panel);
		panel.setForeground(new Color(64, 0, 64));
		panel.setBackground(new Color(228, 228, 228));
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "History",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(null);

		textgHistory = new JTextField();
		textgHistory.setEditable(false);
		textgHistory.setHorizontalAlignment(SwingConstants.CENTER);
		textgHistory.setFont(new Font("Tahoma", Font.BOLD, 14));
		textgHistory.setColumns(10);
		textgHistory.setBounds(10, 15, 190, 45);
		panel.add(textgHistory);
	}
}
