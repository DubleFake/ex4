package front;

import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import back.Security;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Security.encrypt(new FileInputStream("test.txt"), new FileOutputStream("test.encrypted"));
					new File("test.txt").delete();
					Security.decrypt(new FileInputStream("test.encrypted"), new FileOutputStream("test2.txt"));
					new File("test.encrypted").delete();
					Main frame = new Main();
					frame.setTitle("Passworder");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton logoutButton = new JButton("Log out");
		logoutButton.addActionListener(e -> {
			
			Login f = new Login();
			f.setTitle("Log in");
			f.setResizable(false);
			f.setLocationRelativeTo(null);
			this.setVisible(false);
			f.setVisible(true);
			
		});
		logoutButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		logoutButton.setBounds(670, 10, 103, 23);
		contentPane.add(logoutButton);
		
		JLabel lblNewLabel = new JLabel("Password'er");
		lblNewLabel.setBounds(10, 11, 764, 43);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password list");
		lblNewLabel_1.setBounds(10, 65, 764, 24);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 764, 154);
		contentPane.add(scrollPane);
		
		JList<String> list = new JList<>();
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("Delete password");
		btnNewButton.setBounds(482, 264, 138, 23);
		btnNewButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		contentPane.add(btnNewButton);
		
		JButton btnClearPasswords = new JButton("Clear password list");
		btnClearPasswords.setBounds(630, 264, 144, 23);
		btnClearPasswords.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		contentPane.add(btnClearPasswords);
		
		JButton btnAddPassword = new JButton("Add password");
		btnAddPassword.setBounds(10, 264, 138, 23);
		btnAddPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		contentPane.add(btnAddPassword);
	}
}
