package front;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import back.Tools;
import back.VaultManager;

public class AddPassword extends JFrame {

	private JPanel contentPane;
	private JTextField titleTextField;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JTextField urlTextField;
	private boolean charsShown = false;

	/**
	 * Create the frame.
	 * @return 
	 */
	
	public AddPassword() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 689, 710);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel showHidePassword = new JLabel("v-v");
		showHidePassword.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				if(charsShown) {
					passwordField.setEchoChar('*');
					showHidePassword.setText("v-v");

				}
				else {
					passwordField.setEchoChar((char)0);
					showHidePassword.setText("O-O");
					
				}
				charsShown = !charsShown;
			}
			
		});
		showHidePassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
		showHidePassword.setBackground(Color.WHITE);
		showHidePassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		showHidePassword.setHorizontalAlignment(SwingConstants.CENTER);
		showHidePassword.setBounds(424, 390, 33, 36);
		contentPane.add(showHidePassword);
		
		JLabel addPasswordLabel = new JLabel("Adding a password");
		addPasswordLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		addPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addPasswordLabel.setBounds(10, 11, 601, 46);
		contentPane.add(addPasswordLabel);
		
		JLabel titleLabel = new JLabel("Title:");
		titleLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		titleLabel.setBounds(10, 93, 91, 31);
		contentPane.add(titleLabel);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		usernameLabel.setBounds(10, 248, 91, 31);
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		passwordLabel.setBounds(10, 348, 91, 31);
		contentPane.add(passwordLabel);
		
		JLabel urlLabel = new JLabel("URL:");
		urlLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		urlLabel.setBounds(10, 523, 91, 31);
		contentPane.add(urlLabel);
		
		titleTextField = new JTextField();
		titleTextField.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		titleTextField.setBounds(10, 135, 447, 31);
		contentPane.add(titleTextField);
		titleTextField.setColumns(10);
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		usernameTextField.setColumns(10);
		usernameTextField.setBounds(10, 290, 447, 31);
		contentPane.add(usernameTextField);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		passwordField.setBounds(10, 390, 416, 36);
		passwordField.setEchoChar('*');
		contentPane.add(passwordField);
		
		urlTextField = new JTextField();
		urlTextField.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		urlTextField.setColumns(10);
		urlTextField.setBounds(10, 565, 447, 31);
		contentPane.add(urlTextField);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!titleTextField.getText().isEmpty() && !usernameTextField.getText().isEmpty() && !(passwordField.getPassword().length == 0) && !urlTextField.getText().isEmpty())
					VaultManager.addRecord(titleTextField.getText(), usernameTextField.getText(), Tools.stringBuilder(passwordField.getPassword()), urlTextField.getText());
				else
					JOptionPane.showMessageDialog(null, "Please fill out all fields!");
				
			}
		});
		addButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		addButton.setBounds(10, 629, 116, 31);
		contentPane.add(addButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Main.toogleWindowOpenStatus(false);
				close();
				
				
			}
		});
		cancelButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		cancelButton.setBounds(547, 629, 116, 31);
		contentPane.add(cancelButton);
		
		JButton btnGenerateRandom = new JButton("Generate random");
		btnGenerateRandom.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		btnGenerateRandom.setBounds(467, 392, 196, 31);
		contentPane.add(btnGenerateRandom);
	}
	
	private void close() {
		
		this.dispose();
		
	}
}
