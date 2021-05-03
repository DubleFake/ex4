package front;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import back.Tools;
import back.VaultManager;

public class Main extends JFrame {

	private JPanel contentPane;
	private static boolean isPasswordWindowOpen = false;
	private static boolean isEditPasswordWindowOpen  = false;
	private static boolean isDeletePasswordWindowOpen  = false;
	private static List<String> data = new ArrayList<>();
	private JTextField passwordSearchField;
	private static JList<String> list = new JList<>();
	private static DefaultListModel<String> model= new DefaultListModel<>();
	private static List<String> updatedData = new ArrayList<>();
	private static List<String> showableData = new ArrayList<>();
	private static boolean isFiltered = false;
	
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
		
		JButton deletePasswordButton = new JButton("Delete password");
		deletePasswordButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!isDeletePasswordWindowOpen) {
					
					PasswordDeletion f = new PasswordDeletion(data);
					f.addWindowListener(new WindowAdapter() {
						
						public void windowClosing(WindowEvent e) {
							
							Main.toogleDeletePasswordWindow(false);
							
						}
						
					});
					f.setTitle("Edit record");
					f.setResizable(false);
					f.setLocationRelativeTo(null);
					f.setVisible(true);
					isEditPasswordWindowOpen = true;
					
				}
				
			}
			
		});
		deletePasswordButton.setBounds(482, 321, 138, 23);
		deletePasswordButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		contentPane.add(deletePasswordButton);
		
		JButton editPasswordButton = new JButton("Edit password");
		editPasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!isEditPasswordWindowOpen) {
					
					EditPassword f = new EditPassword(data);
					f.addWindowListener(new WindowAdapter() {
						
						public void windowClosing(WindowEvent e) {
							
							Main.toogleEditPasswordWindow(false);
							
						}
						
					});
					f.setTitle("Edit record");
					f.setResizable(false);
					f.setLocationRelativeTo(null);
					f.setVisible(true);
					isEditPasswordWindowOpen = true;
				}
				
			}
		});
		editPasswordButton.setBounds(630, 321, 144, 23);
		editPasswordButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		contentPane.add(editPasswordButton);
		
		JButton addPasswordButton = new JButton("Add password");
		addPasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!isPasswordWindowOpen) {
					
					AddPassword f = new AddPassword();
					f.addWindowListener(new WindowAdapter() {
						
						public void windowClosing(WindowEvent e) {
							
							Main.toogleAddPasswordWindow(false);
							
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
					updateTable();
				else {
					showableData.clear();
					updatedData.clear();
				for(String s: data) {
					
					String []temp = s.split("\\s+");
					
					if(Pattern.compile(Pattern.quote(passwordSearchField.getText()), Pattern.CASE_INSENSITIVE).matcher(temp[0]).find()) {
						updatedData.add(s);
						result.add(temp[0] + "                    " + temp[1] + "                    " + Tools.hidePassword(temp[2]) + "                    " + temp[3]);
						isFiltered = true;
					}
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
		
		list.getSelectionModel().addListSelectionListener(e->{
			
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			
			if(!lsm.isSelectionEmpty()) {
			
			String p = list.getSelectedValue();
			String[] args = p.split("\\s+");
			List<String> result = new ArrayList<>();
			
			if(!isFiltered)
			for(String s: data) {
				
				String[] details = s.split("\\s+");
				
				if(details[0].equals(args[0])) 
					if(!args[2].equals(details[2])) 
						result.add(args[0] + "                    " + args[1]+"                    "+details[2]+"                    "+args[3]);
					else
						result.add(args[0] + "                    "+args[1]+"                    "+Tools.hidePassword(args[2])+"                    "+args[3]);
				else 
					result.add(details[0] + "                    " + details[1]+"                    "+Tools.hidePassword(details[2])+"                    "+details[3]);
			}
			else
				for(String s: updatedData) {
					
					String []details = s.split("\\s+");
					
					if(details[0].equals(args[0]))
						if(!args[2].equals(details[2]))
							result.add(args[0] + "                    " + args[1]+"                    "+details[2]+"                    "+args[3]);
						else
							result.add(args[0] + "                    "+args[1]+"                    "+Tools.hidePassword(args[2])+"                    "+args[3]);
					else
						result.add(details[0] + "                    "+details[1]+"                    "+Tools.hidePassword(details[2])+"                    "+details[3]);
					
				}
			setCustomTable(result);
			}
			
		});

		list.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				
				if(SwingUtilities.isRightMouseButton(e)) {
					
		            JList<String> list = (JList<String>)e.getSource();
		            int row = list.locationToIndex(e.getPoint());
		            
		            String []s = model.get(row).split("\\s+");
		            String target = s[0] + " " +s[1] + " " + s[2] + " " + s[3];
		            
		            for(String d: data) {
		            	
		            	s = d.split("\\s+");
		            	String record = s[0] + " " + s[1] + " " + Tools.hidePassword(s[2]) + " " + s[3];
		            	if(record.equals(target)) {
		            		
				            StringSelection stringSelection = new StringSelection(s[2]);
				            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				            clipboard.setContents(stringSelection, null);
							JOptionPane.showMessageDialog(null, "Password copied.");
							break;
		            		
		            	}
		            	
		            }
					
				}
				
			}
			
		});
		
	}
	
	public static void toogleAddPasswordWindow(boolean b) {
		
			isPasswordWindowOpen = b;
		
	}
	
	public static void toogleEditPasswordWindow(boolean b) {
		
		isEditPasswordWindowOpen = b;
	
}
	
	public static void toogleDeletePasswordWindow(boolean b) {
		
		isDeletePasswordWindowOpen = b;
		
}
	
	
	
	public static void updateTable() {
		
		try {
			
			showableData.clear();
			updatedData.clear();
			isFiltered = false;
			Thread t = new Thread(()->{data=VaultManager.loadProfile();});
			t.start();
			model.removeAllElements();
			t.join();
			
			for(String s: data) {
				
				String []temp = s.split("\\s+");
				model.addElement(temp[0] + "                    " + temp[1] +"                    " + Tools.hidePassword(temp[2]) + "                    " + temp[3]);
				
			}
			
			list.setModel(model);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	private static void setCustomTable(List<String> s) {
		
		showableData = s;
		model.removeAllElements();
		model.addAll(s);
		list.setModel(model);
		
	}
}
