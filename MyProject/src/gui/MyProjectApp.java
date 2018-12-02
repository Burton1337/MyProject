package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import java.util.List;

import typ.*;

public class MyProjectApp extends JFrame {

	private JPanel contentPane;
	private JTextField modellbezTextField;
	private JTable table;

	private TypDO typDO;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyProjectApp frame = new MyProjectApp();
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
	public MyProjectApp() {
		
		try {
			typDO = new TypDO(); 
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(this, "Error: " + exception, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		setTitle("My Project App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 433, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblEnterModellbezeichnung = new JLabel("Modellbezeichnung eingeben");
		panel.add(lblEnterModellbezeichnung);
		
		modellbezTextField = new JTextField();
		panel.add(modellbezTextField);
		modellbezTextField.setColumns(10);
		
		JButton btnSuchen = new JButton("Suchen");
		btnSuchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String modellbez = modellbezTextField.getText();
					List<TypBE> list = null;  
					if (modellbez != null && modellbez.trim().length() > 0) {
						list = typDO.FindTypByModellbez(modellbez);
					} 
					else {
						list = typDO.GetAllTyp();
					} 
					
					TypTableModel typTableModel = new TypTableModel(list);
					table.setModel(typTableModel);
					
					//for (TypBE temp : list) {
					//	System.out.println(temp);
					//} 
					
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(MyProjectApp.this, "Error: " + exception, "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		panel.add(btnSuchen);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
