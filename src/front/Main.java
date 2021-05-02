package front;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import back.Tools;
import back.VaultManager;

public class Main extends JFrame {

	private JPanel contentPane;
	private static boolean isPasswordWindowOpen = false;
	private static List<String> data = new ArrayList<>();
	private JTextField passwordSearchField;
	private static JList<String> list = new JList<>();
	private static DefaultListModel<String> model= new DefaultListModel<>();

	/**
	 * Launch the application.
	 */

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
			
			Thread t1 =new Thread(()->{VaultManager.saveProfile();});
			t1.start();
			Login f = new Login();
			f.setTitle("Log in");
			f.setResizable(false);
			f.setLocationRelativeTo(null);
			f.setVisible(true);
			this.setVisible(false);
			
		});
		logoutButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		logoutButton.setBounds(670, 10, 103, 23);
		contentPane.add(logoutButton);
		
		JLabel lblNewLabel = new JLabel("Password'er");
		lblNewLabel.setBounds(10, 11, 764, 43);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 148, 764, 162);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(list);
		updateTable();
		
		JButton btnNewButton = new JButton("Delete password");
		btnNewButton.setBounds(482, 321, 138, 23);
		btnNewButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		contentPane.add(btnNewButton);
		
		JButton btnClearPasswords = new JButton("Clear password list");
		btnClearPasswords.setBounds(630, 321, 144, 23);
		btnClearPasswords.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		contentPane.add(btnClearPasswords);
		
		JButton addPasswordButton = new JButton("Add password");
		addPasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!isPasswordWindowOpen) {
					
					AddPassword f = new AddPassword();
					f.addWindowListener(new WindowAdapter() {
						
						public void windowClosing(WindowEvent e) {
							
							Main.toogleWindowOpenStatus(false);
							
						}
						
					});
					f.setTitle("Add password");
					f.setResizable(false);
					f.setLocationRelativeTo(null);
					f.setVisible(true);
					isPasswordWindowOpen = true;
				}
				
			}
		});
		addPasswordButton.setBounds(10, 321, 138, 23);
		addPasswordButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		contentPane.add(addPasswordButton);
		
		addPasswordButton.requestFocus();
		
		passwordSearchField = new JTextField();
		passwordSearchField.setForeground(Color.GRAY);
		passwordSearchField.setFont(new Font("Trebuchet MS", Font.ITALIC, 16));
		passwordSearchField.setText("Search for a password...");
		passwordSearchField.addFocusListener(new FocusAdapter() {
			
			public void focusGained(FocusEvent e) {
				
				if(passwordSearchField.getForeground().equals(Color.GRAY)) {
				
				passwordSearchField.setText("");
				passwordSearchField.setForeground(Color.BLACK);
				passwordSearchField.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
				
				}
				
			}
			
			public void focusLost(FocusEvent e) {
				
				if(passwordSearchField.getText().isEmpty()) {
				
				passwordSearchField.setText("Search for a password...");
				passwordSearchField.setForeground(Color.GRAY);
				passwordSearchField.setFont(new Font("Trebuchet MS", Font.ITALIC, 16));
				
				}
				
			}
			
		});
		
		passwordSearchField.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		passwordSearchField.setBounds(10, 102, 631, 31);
		contentPane.add(passwordSearchField);
		passwordSearchField.setColumns(10);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				List<String> result = new ArrayList<>();
				
				if(passwordSearchField.getText().isEmpty() || passwordSearchField.getForeground().equals(Color.GRAY))
					resetTable();
				else {
				for(String s: data) {
					
					String []temp = s.split("\\s+");
					
					if(Pattern.compile(Pattern.quote(passwordSearchField.getText()), Pattern.CASE_INSENSITIVE).matcher(temp[0]).find())
						result.add(temp[0] + "                    " + temp[1] + "                    " + Tools.hidePassword(temp[2]) + "                    " + temp[3]);
					else
						continue;
					
				}
				
				setCustomTable(result);
				}
				
			}
			
		});
		searchButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		searchButton.setBounds(646, 101, 123, 31);
		contentPane.add(searchButton);
		

	}
	
	public static void toogleWindowOpenStatus(boolean b) {
		
			isPasswordWindowOpen = b;
		
	}
	
	public static void updateTable() {
		
		try {
			
			Thread t = new Thread(()->{data=VaultManager.loadProfile();});
			t.start();
			model.removeAllElements();
			t.join();
			
			for(String s: data) {
				
				String []temp = s.split("\\s+");
				model.addElement(temp[0] + "                    " + temp[1] +"                    " + Tools.hidePassword(temp[2]) + "                   " + temp[3]);
				
			}
			
			list.setModel(model);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	private static void setCustomTable(List<String> s) {
		
		model.removeAllElements();
		model.addAll(s);
		list.setModel(model);
		
	}
	
	private static void resetTable() {
		
		model.removeAllElements();
		for(String s: data) {
			
			String []temp = s.split("\\s+");
			model.addElement(temp[0] + "                    " + temp[1] +"                    " + Tools.hidePassword(temp[2]) + "                   " + temp[3]);
			
		}
		list.setModel(model);
		
	}
}
