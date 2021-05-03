package front;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import back.Tools;

public class EditPassword extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public EditPassword(List<String> data) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 670, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Click on the record you want to edit");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 634, 37);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 634, 203);
		contentPane.add(scrollPane);
		
		JList<String> list = new JList<>();
		DefaultListModel<String> model = new DefaultListModel<>();
		for(String s: data) {
			
			String []temp = s.split("\\s+");
			model.addElement(temp[0] + "                    " + temp[1] +"                    " + Tools.hidePassword(temp[2]) + "                    " + temp[3]);
			
		}

		list.setModel(model);
		scrollPane.setViewportView(list);
		list.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Main.toogleEditPasswordWindow(false);
				dispose();
				
			}
			
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cancelButton.setBounds(555, 283, 89, 23);
		contentPane.add(cancelButton);
		
		list.getSelectionModel().addListSelectionListener(e->{
			
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			
			if(!lsm.isSelectionEmpty()) {
			
			String p = list.getSelectedValue();
			
				RecordEditor f = new RecordEditor(p);
				f.setTitle("Edit");
				f.setResizable(false);
				f.setLocationRelativeTo(null);
				f.setVisible(true);
				dispose();
			
			}
		});
	}
}
