package front;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import back.ProfileManager;
import back.Tools;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JPasswordField repeatPasswordField;
	private boolean charsShown = false;
	private boolean charsShown1 = false;
	/**
	 * Create the panel.
	 */
	public Register() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel showHidePassword = new JLabel("v-v");
		showHidePassword.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				if(charsShown) {
					repeatPasswordField.setEchoChar('*');
					showHidePassword.setText("v-v");

				}
				else {
					repeatPasswordField.setEchoChar((char)0);
					showHidePassword.setText("O-O");
					
				}
				charsShown = !charsShown;
			}
			
		});
		showHidePassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
		showHidePassword.setBackground(Color.WHITE);
		showHidePassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		showHidePassword.setHorizontalAlignment(SwingConstants.CENTER);
		showHidePassword.setBounds(329, 79, 33, 19);
		contentPane.add(showHidePassword);
		
		JLabel showHidePassword_1 = new JLabel("v-v");
		showHidePassword_1.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				if(charsShown1) {
					passwordField.setEchoChar('*');
					showHidePassword_1.setText("v-v");

				}
				else {
					passwordField.setEchoChar((char)0);
					showHidePassword_1.setText("O-O");
					
				}
				charsShown1 = !charsShown1;
			}
			
		});
		showHidePassword_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		showHidePassword_1.setBackground(Color.WHITE);
		showHidePassword_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		showHidePassword_1.setHorizontalAlignment(SwingConstants.CENTER);
		showHidePassword_1.setBounds(329, 46, 33, 19);
		contentPane.add(showHidePassword_1);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(10, 11, 80, 22);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 44, 80, 22);
		lblPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		contentPane.add(lblPassword);
		
		usernameTextField = new JTextField();
		usernameTextField.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				if(c == KeyEvent.VK_SPACE)
					e.consume();
				
			}
			
		});
		usernameTextField.setBounds(148, 11, 180, 22);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				if(c == KeyEvent.VK_SPACE)
					e.consume();
				
			}
			
		});
		passwordField.setBounds(148, 44, 180, 22);
		passwordField.setEchoChar('*');
		contentPane.add(passwordField);
		
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(e -> {
			
			if(!usernameTextField.getText().isEmpty() && !(passwordField.getPassword().length==0) && !(repeatPasswordField.getPassword().length==0))
				
				if(Tools.stringBuilder(passwordField.getPassword()).equals(Tools.stringBuilder(repeatPasswordField.getPassword()))) {
				
				ProfileManager.addUser(usernameTextField.getText(), Tools.stringBuilder(passwordField.getPassword()));
				JOptionPane.showMessageDialog(null, "Registered successfuly!");
				passwordField.setText("");
				repeatPasswordField.setText("");
				usernameTextField.setText("");
				Login f = new Login();
				f.setResizable(false);
				f.setTitle("Log in");
				f.setLocationRelativeTo(null);
				f.setVisible(true);
				this.setVisible(false);
				
				}
				else 
					JOptionPane.showMessageDialog(null, "Password missmatch!");
				else 
					JOptionPane.showMessageDialog(null, "Please fill out all fields!");

			
		});
		registerButton.setBounds(10, 165, 101, 23);
		registerButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		contentPane.add(registerButton);
		
		JButton logInButton = new JButton("Log in instead");
		logInButton.addActionListener(e -> {
			
			passwordField.setText("");
			repeatPasswordField.setText("");
			usernameTextField.setText("");
			Login f = new Login();
			f.setResizable(false);
			f.setTitle("Log in");
			f.setLocationRelativeTo(null);
			f.setVisible(true);
			this.setVisible(false);
			
		});
		logInButton.setBounds(219, 165, 143, 23);
		logInButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		contentPane.add(logInButton);
		
		JLabel lblRepeatPassword = new JLabel("Repeat password:");
		lblRepeatPassword.setBounds(10, 77, 151, 22);
		lblRepeatPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		contentPane.add(lblRepeatPassword);
		
		repeatPasswordField = new JPasswordField();
		repeatPasswordField.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				if(c == KeyEvent.VK_SPACE)
					e.consume();
				
			}
			
		});
		repeatPasswordField.setEchoChar('*');
		repeatPasswordField.setBounds(148, 77, 180, 22);
		contentPane.add(repeatPasswordField);
		
	}
}
