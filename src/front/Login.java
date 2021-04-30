package front;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private static Login frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
					frame.setTitle("Log in");
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 355, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel logInlabel = new JLabel("Log in");
		logInlabel.setHorizontalAlignment(SwingConstants.CENTER);
		logInlabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		logInlabel.setBounds(10, 11, 319, 30);
		contentPane.add(logInlabel);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		usernameLabel.setBounds(10, 52, 82, 19);
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		passwordLabel.setBounds(10, 82, 82, 19);
		contentPane.add(passwordLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		usernameTextField.setBounds(102, 52, 189, 19);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		passwordField.setColumns(10);
		passwordField.setBounds(102, 82, 189, 19);
		contentPane.add(passwordField);
		
		JLabel message = new JLabel("Don't have an account?");
		message.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		message.setBounds(53, 223, 172, 30);
		contentPane.add(message);
		
		JLabel registerLabel = new JLabel("Register.");
		registerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				registerLabel.setForeground(Color.CYAN);
				
			}
			public void mouseExited(MouseEvent e) {
				
				registerLabel.setForeground(Color.BLACK);
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Register f = new Register();
				f.setTitle("Register");
				f.setResizable(false);
				f.setLocationRelativeTo(null);
				frame.setVisible(false);
				f.setVisible(true);
				
			}
		});
		registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		registerLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		registerLabel.setBounds(223, 229, 70, 19);
		contentPane.add(registerLabel);
		
		JButton logInButton = new JButton("Log in");
		logInButton.addActionListener(e -> {
			
			Main f = new Main();
			f.setTitle("Passworder");
			f.setLocationRelativeTo(null);
			f.setResizable(false);
			frame.setVisible(false);
			f.setVisible(true);
			
		});
		logInButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		logInButton.setBounds(102, 116, 99, 23);
		contentPane.add(logInButton);
	}
}