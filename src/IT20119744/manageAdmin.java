package IT20119744;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class manageAdmin extends JFrame {

	private JPanel contentPane;
	private JTable table_admin;
	private JTextField searchadmin;
	private JButton btnDelete;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageAdmin frame = new manageAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void displayadmindata() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Email");
		model.addColumn("Phone");
		model.addColumn("Password");
		model.addColumn("Recovery Email");
		
		//model.setColumnIdentifiers();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/suwasetha_vaccine","root","");
			Statement stmt_retrive=conn.createStatement();
			String query= "select admin_id,admin_name, admin_email, admin_phone, admin_password, admin_recovery_email from tbl_admin";
			ResultSet rs_retrive= stmt_retrive.executeQuery(query);
			
			while(rs_retrive.next()) {
				model.addRow(new Object[] {
						rs_retrive.getString("admin_id"),
						rs_retrive.getString("admin_name"),
						rs_retrive.getString("admin_email"),
						rs_retrive.getString("admin_phone"),
						rs_retrive.getString("admin_password"),
						rs_retrive.getString("admin_recovery_email")
				});
			}
			rs_retrive.close();
			stmt_retrive.close();
			conn.close();
			
			table_admin.setModel(model);
			table_admin.setAutoResizeMode(0);
			table_admin.getColumnModel().getColumn(0).setPreferredWidth(40);
			table_admin.getColumnModel().getColumn(1).setPreferredWidth(100);
			table_admin.getColumnModel().getColumn(2).setPreferredWidth(200);
			table_admin.getColumnModel().getColumn(3).setPreferredWidth(80);
			table_admin.getColumnModel().getColumn(4).setPreferredWidth(150);
			table_admin.getColumnModel().getColumn(5).setPreferredWidth(200);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	
	
	

	/**
	 * Create the frame.
	 */
	public manageAdmin() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				displayadmindata();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setUndecorated(true);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(95, 158, 160));
		panel.setBounds(0, 0, 580, 33);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("-");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				label.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label.setForeground(Color.white);
			}
		});
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("Segoe UI", Font.BOLD, 40));
		label.setBounds(495, 2, 30, 20);
		panel.add(label);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//setVisible(false);
				if(JOptionPane.showConfirmDialog(null,"Do you want to Exit?","Confirmation",JOptionPane.YES_NO_OPTION)==0) {
					manageAdmin.this.dispose();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(Color.white);
			}
		});
		lblX.setForeground(new Color(255, 255, 255));
		lblX.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblX.setBounds(540, 2, 42, 33);
		panel.add(lblX);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addAdmin aa= new addAdmin ();
				aa.setVisible(true);				
				setVisible(false);
			}
		});
		btnNewButton.setBackground(new Color(95, 158, 160));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(12, 96, 120, 33);
		contentPane.add(btnNewButton);
		
		JButton btnUpdateAdmin = new JButton("UPDATE ");
		btnUpdateAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				updateAdmin UA= new updateAdmin();
				UA.setVisible(true);				
				setVisible(false);
			}
		});
		btnUpdateAdmin.setForeground(Color.WHITE);
		btnUpdateAdmin.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnUpdateAdmin.setBackground(new Color(95, 158, 160));
		btnUpdateAdmin.setBounds(222, 96, 120, 33);
		contentPane.add(btnUpdateAdmin);
		
		btnDelete = new JButton("DELETE");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateAdmin UA= new updateAdmin();
				UA.setVisible(true);				
				setVisible(false);
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnDelete.setBackground(new Color(95, 158, 160));
		btnDelete.setBounds(430, 96, 120, 33);
		contentPane.add(btnDelete);
		
		searchadmin = new JTextField();
		searchadmin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
					DefaultTableModel tableadmin =(DefaultTableModel)table_admin.getModel();
					String search = searchadmin.getText().trim();
					
					TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tableadmin);
					table_admin.setRowSorter(tr);
					tr.setRowFilter(RowFilter.regexFilter(search));
					
			}
		});
		searchadmin.setBounds(110, 46, 200, 33);
		contentPane.add(searchadmin);
		searchadmin.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblSearch.setForeground(new Color(95, 158, 160));
		lblSearch.setBounds(10, 52, 100, 20);
		contentPane.add(lblSearch);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setForeground(new Color(95, 158, 160));
		lblAdmin.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblAdmin.setBounds(450, 46, 100, 33);
		contentPane.add(lblAdmin);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 142, 550, 200);
		contentPane.add(scrollPane);
		
		JLabel label_1 = new JLabel("<<<");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				profile P= new profile ();
				P.setVisible(true);				
				setVisible(false);
			}
		});
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		label_1.setBounds(23, 0, 74, 33);
		panel.add(label_1);
		
		table_admin = new JTable();
		scrollPane.setViewportView(table_admin);
		
		JButton btnPrintReport = new JButton("PRINT LIST");
		btnPrintReport.setForeground(new Color(255, 255, 255));
		btnPrintReport.setBackground(new Color(95, 158, 160));
		btnPrintReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MessageFormat header = new MessageFormat("List of Admins");
				MessageFormat footer = new MessageFormat("Suwasetha Vaccine Management");
				
				try {
					table_admin.print(JTable.PrintMode.FIT_WIDTH, header, footer);
					
				}catch(PrinterException e){
					JOptionPane.showMessageDialog(null,"Cannot be Print"+e.getMessage());
					
				}
			}
		});
		btnPrintReport.setBounds(208, 350, 150, 40);
		contentPane.add(btnPrintReport);
		
		JLabel lblNewLabel_1 =new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 580, 420);
		lblNewLabel_1.setIcon(new ImageIcon(manageAdmin.class.getResource("/IT20119744/assets/login.jpeg")));
		contentPane.add(lblNewLabel_1);
	}
}
