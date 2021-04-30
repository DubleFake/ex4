package front;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JTextField emailTextField;
	private JPasswordField repeatPasswordField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the panel.
	 */
	public Register() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 354, 261);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(10, 11, 80, 22);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 44, 80, 22);
		lblPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		contentPane.add(lblPassword);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(148, 11, 180, 22);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(148, 44, 180, 22);
		contentPane.add(passwordField);
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(e -> {
			
			JOptionPane.showMessageDialog(null, "Registered successfuly!");
			passwordField.setText("");
			repeatPasswordField.setText("");
			emailTextField.setText("");
			usernameTextField.setText("");
			Login f = new Login();
			f.setResizable(false);
			f.setTitle("Log in");
			f.setLocationRelativeTo(null);
			f.setVisible(true);
			this.setVisible(false);
			
		});
		registerButton.setBounds(10, 188, 101, 23);
		registerButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		contentPane.add(registerButton);
		
		JButton logInButton = new JButton("Log in instead");
		logInButton.addActionListener(e -> {
			
			passwordField.setText("");
			repeatPasswordField.setText("");
			emailTextField.setText("");
			usernameTextField.setText("");
			Login f = new Login();
			f.setResizable(false);
			f.setTitle("Log in");
			f.setLocationRelativeTo(null);
			f.setVisible(true);
			this.setVisible(false);
			
		});
		logInButton.setBounds(185, 188, 143, 23);
		logInButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		contentPane.add(logInButton);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(148, 110, 180, 22);
		emailTextField.setColumns(10);
		contentPane.add(emailTextField);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("E-mail:");
		lblNewLabel_1_1_1.setBounds(10, 110, 80, 22);
		lblNewLabel_1_1_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblRepeatPassword = new JLabel("Repeat password:");
		lblRepeatPassword.setBounds(10, 77, 151, 22);
		lblRepeatPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		contentPane.add(lblRepeatPassword);
		
		repeatPasswordField = new JPasswordField();
		repeatPasswordField.setBounds(148, 77, 180, 22);
		contentPane.add(repeatPasswordField);
		
	}
}
