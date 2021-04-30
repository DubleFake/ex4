package front;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setTitle("Passworder");
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Password'er");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 11, 764, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password list");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 65, 764, 24);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 764, 154);
		contentPane.add(scrollPane);
		
		JList<String> list = new JList<>();
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("Delete password");
		btnNewButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnNewButton.setBounds(482, 264, 138, 23);
		contentPane.add(btnNewButton);
		
		JButton btnClearPasswords = new JButton("Clear password list");
		btnClearPasswords.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnClearPasswords.setBounds(630, 264, 144, 23);
		contentPane.add(btnClearPasswords);
		
		JButton btnAddPassword = new JButton("Add password");
		btnAddPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnAddPassword.setBounds(10, 264, 138, 23);
		contentPane.add(btnAddPassword);
	}
}
